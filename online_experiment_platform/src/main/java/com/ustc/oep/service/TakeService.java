package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.Take;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 10:22
 */
public interface TakeService extends IService<Take> {
    List<LoginUser> getUserInfosByCourseId(Long courseId);
}
