package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.Take;
import com.ustc.oep.mapper.TakeMapper;
import com.ustc.oep.service.TakeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 10:23
 */
@Service
public class TakeServiceImpl extends ServiceImpl<TakeMapper, Take> implements TakeService{
    @Override
    public List<LoginUser> getUserInfosByCourseId(Long courseId) {

        return baseMapper.getUserInfosByCourseId(courseId);
    }
}
