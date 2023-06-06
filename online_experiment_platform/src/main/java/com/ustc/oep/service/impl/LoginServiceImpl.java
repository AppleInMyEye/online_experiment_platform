package com.ustc.oep.service.impl;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.service.LoginService;
import com.ustc.oep.utils.JwtUtil;
import com.ustc.oep.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public R<Map<String,String>> login(UserInfo userInfo){
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getEmailAddress(), userInfo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = Long.toString(loginUser.getUserInfo().getUuid());
        String jwt = JwtUtil.createJWT(userid);

        //把完整的用户信息存入redis  userid作为key
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login:"+userid,loginUser);
        return R.success(map);
    }
}
