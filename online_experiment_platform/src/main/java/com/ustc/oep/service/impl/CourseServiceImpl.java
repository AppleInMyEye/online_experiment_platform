package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.entity.Course;
import com.ustc.oep.mapper.CourseMapper;
import com.ustc.oep.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:55
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    public List<Course> selectCourseByUid(Long uid) {
        return baseMapper.selectCourseByUid(uid);
    }
}
