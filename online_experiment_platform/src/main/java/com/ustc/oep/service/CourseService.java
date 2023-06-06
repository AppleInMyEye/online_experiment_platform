package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.entity.Course;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:50
 */
public interface CourseService extends IService<Course> {
    List<Course> selectCourseByUid(Long uid);
}
