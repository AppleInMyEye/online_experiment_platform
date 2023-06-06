package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.mapper.MenuMapper;
import com.ustc.oep.mapper.UserInfoMapper;
import com.ustc.oep.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getEmailAddress, s);
        UserInfo user = userInfoMapper.selectOne(queryWrapper);
        //如果用户不存在，抛出异常
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //查询对应的权限信息
        List<String> list = menuMapper.selectMenuPermsByUserId(user.getUuid());
        return new LoginUser(user,list);
    }
}
