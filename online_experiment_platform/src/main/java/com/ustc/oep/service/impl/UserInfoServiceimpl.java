package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.dto.UserDTO;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.mapper.UserInfoMapper;
import com.ustc.oep.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author YuJianhua
 * @create 2023-03-16 21:07
 */
@Service
public class UserInfoServiceimpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserInfo register(UserDTO userDTO) {
        UserInfo userInfo = getByUsername(userDTO.getUsername());
        if(userInfo==null){
            userInfo = new UserInfo();
            BeanUtils.copyProperties(userDTO,userInfo);
            userInfo.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            save(userInfo);
        }else{
            userInfo = null;
        }
        return userInfo;
    }

    @Override
    public UserInfo getByUsername(String username) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return this.getOne(queryWrapper);
    }
}
