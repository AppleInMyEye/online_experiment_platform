package com.ustc.oep.service;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.UserInfo;

import java.util.Map;

/**
 * @author YuJianhua
 * @create 2023-05-27 16:50
 */
public interface LoginService {
    R<Map<String,String>> login(UserInfo userInfo);
}
