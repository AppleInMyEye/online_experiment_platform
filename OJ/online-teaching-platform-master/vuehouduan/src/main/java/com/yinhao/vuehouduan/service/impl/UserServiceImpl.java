package com.yinhao.vuehouduan.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yinhao.vuehouduan.common.Constants;
import com.yinhao.vuehouduan.controller.dto.UserDTO;
import com.yinhao.vuehouduan.controller.dto.UserPasswordDTO;
import com.yinhao.vuehouduan.entity.Menu;
import com.yinhao.vuehouduan.entity.RoleMenu;
import com.yinhao.vuehouduan.entity.User;
import com.yinhao.vuehouduan.exception.ServiceException;
import com.yinhao.vuehouduan.mapper.RoleMapper;
import com.yinhao.vuehouduan.mapper.RoleMenuMapper;
import com.yinhao.vuehouduan.mapper.UserMapper;
import com.yinhao.vuehouduan.service.IMenuService;
import com.yinhao.vuehouduan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinhao.vuehouduan.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yinhao
 * @since 2023-03-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log log = Log.get();

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;
    @Override
    public UserDTO login(UserDTO userDto) {
        User one = getUserInfo(userDto);
        if (one != null) {
            BeanUtils.copyProperties(one, userDto);
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDto.setToken(token);

            String role = one.getRole();

            List<Menu> roleMenus = getRoleMenus(role);
            userDto.setMenus(roleMenus);
            return userDto;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDto) {
        User one = getUserInfo(userDto);
        if(one == null){
            one = new User();
            BeanUtils.copyProperties(userDto,one);
            save(one);
        }else{
            throw new ServiceException(Constants.CODE_600, "用户名已存在");
        }
        return one;
    }


    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    private User getUserInfo(UserDTO userDto){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        queryWrapper.eq("password", userDto.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            log.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
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
