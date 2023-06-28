package com.yinhao.vuehouduan.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinhao.vuehouduan.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yinhao.vuehouduan.service.IAssignmentService;
import com.yinhao.vuehouduan.entity.Assignment;

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
@RequestMapping("/assignment")
public class AssignmentController {

    @Resource
    private IAssignmentService assignmentService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Assignment assignment) {
        return Result.success(assignmentService.saveOrUpdate(assignment));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(assignmentService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(assignmentService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(assignmentService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(assignmentService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return Result.success(assignmentService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

