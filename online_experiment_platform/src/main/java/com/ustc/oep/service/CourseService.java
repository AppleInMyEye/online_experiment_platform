package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:50
 */
public interface CourseService extends IService<Course> {
    List<Course> selectCourseByUid(Long uid);
    List<Course> getCourseByPage(int pageNum, int pageSize, String name);

    List<Course> search(String keyword);
}
