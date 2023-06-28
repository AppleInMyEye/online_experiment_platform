package com.ustc.oep.service.impl;

import com.ustc.oep.common.R;
import com.ustc.oep.dto.UserDTO;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.Menu;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.mapper.RoleMapper;
import com.ustc.oep.mapper.RoleMenuMapper;
import com.ustc.oep.service.IMenuService;
import com.ustc.oep.service.LoginService;
import com.ustc.oep.utils.JwtUtil;
import com.ustc.oep.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author YuJianhua
 * @create 2023-05-27 16:50
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private IMenuService menuService;

    @Override
    public R<UserDTO> login(UserInfo userInfo){
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = Long.toString(loginUser.getUserInfo().getId());
        String jwt = JwtUtil.createJWT(userid);

        //把完整的用户信息存入redis  userid作为key
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login:"+userid,loginUser);

        //返回给前端的数据
        UserDTO userDTO = new UserDTO();
        userDTO.setToken(jwt);
        userDTO.setId(loginUser.getUserInfo().getId());
        userDTO.setAvatarUrl(loginUser.getUserInfo().getAvatarUrl());
        userDTO.setPassword(loginUser.getUserInfo().getPassword());
        userDTO.setNickname(loginUser.getUserInfo().getNickname());
        userDTO.setRole(loginUser.getUserInfo().getRole());
        userDTO.setUsername(loginUser.getUserInfo().getUsername());
        List<Menu> roleMenus = getRoleMenus(loginUser.getUserInfo().getRole());
        userDTO.setMenus(roleMenus);
        return R.success(userDTO);
    }

    private List<Menu> getRoleMenus(String roleFlag){
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        List<Menu> menus = menuService.findMenus("");

        List<Menu> roleMenus = new ArrayList<>();
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
