package com.ustc.oep.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.Role;
import org.springframework.web.bind.annotation.*;
import com.ustc.oep.service.IRoleService;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yinhao
 * @since 2023-06-03
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    // 新增或者更新
    @PostMapping
    public R<Boolean> save(@RequestBody Role role) {
        return R.success(roleService.saveOrUpdate(role));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return R.success(roleService.removeById(id));
    }

    @PostMapping("/del/batch")
    public R<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return R.success(roleService.removeByIds(ids));
    }

    @GetMapping
    public R<List<Role>> findAll() {
        return R.success(roleService.list());
    }

    @GetMapping("/{id}")
    public R<Role> findOne(@PathVariable Integer id) {
        return R.success(roleService.getById(id));
    }

    @GetMapping("/page")
    public R<Page<Role>> findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return R.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @PostMapping("/roleMenu/{roleId}")
    public R<String> roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId,menuIds);
        return R.success("setRoleMenu success");
    }
    @GetMapping("/roleMenu/{roleId}")
    public R<List<Integer>> getroleMenu(@PathVariable Integer roleId) {
        return R.success(roleService.getRoleMenu(roleId));
    }
}

