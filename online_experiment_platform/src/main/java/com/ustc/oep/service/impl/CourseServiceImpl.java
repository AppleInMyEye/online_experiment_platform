package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.entity.Course;
import com.ustc.oep.mapper.CourseMapper;
import com.ustc.oep.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:55
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    public List<Course> selectCourseByUid(Long uid) {
        return baseMapper.selectCourseByUid(uid);
    }

    @Override
    public List<Course> getCourseByPage(int pageNum, int pageSize, String name) {
        Page<Course> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        queryWrapper.like("course_name", name);
        queryWrapper.orderByDesc("course_id");
        return baseMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public List<Course> search(String keyword) {
        return baseMapper.search(keyword);
    }
}
