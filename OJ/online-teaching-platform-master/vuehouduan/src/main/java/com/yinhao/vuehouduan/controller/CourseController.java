package com.yinhao.vuehouduan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinhao.vuehouduan.common.Result;
import com.yinhao.vuehouduan.entity.Role;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yinhao.vuehouduan.service.ICourseService;
import com.yinhao.vuehouduan.entity.Course;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yinhao
 * @since 2023-06-08
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Course course) {
        return Result.success(courseService.saveOrUpdate(course));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(courseService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(courseService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(courseService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

