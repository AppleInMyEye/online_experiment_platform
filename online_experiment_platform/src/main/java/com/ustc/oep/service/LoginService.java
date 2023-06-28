package com.ustc.oep.service;

import com.ustc.oep.common.R;
import com.ustc.oep.dto.UserDTO;
import com.ustc.oep.entity.UserInfo;

/**
 * @author YuJianhua
 * @create 2023-05-27 16:50
 */
public interface LoginService {
    R<UserDTO> login(UserInfo userInfo);
}
