package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.dto.UserDTO;
import com.ustc.oep.entity.UserInfo;

/**
 * @author YuJianhua
 * @create 2023-03-16 21:02
 */
public interface UserInfoService extends IService<UserInfo> {
    public UserInfo register(UserDTO userDTO);
    public UserInfo getByUsername(String username);

}
