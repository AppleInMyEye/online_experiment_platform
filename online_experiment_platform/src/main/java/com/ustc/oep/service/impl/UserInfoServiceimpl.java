package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.mapper.UserInfoMapper;
import com.ustc.oep.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author YuJianhua
 * @create 2023-03-16 21:07
 */
@Service
public class UserInfoServiceimpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
