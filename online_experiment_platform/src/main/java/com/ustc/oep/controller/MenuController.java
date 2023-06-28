package com.ustc.oep.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.Menu;
import com.ustc.oep.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-06-21 20:41
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @PostMapping
    public R<Boolean> save(@RequestBody Menu menu) {
        return R.success(menuService.saveOrUpdate(menu));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        return R.success(menuService.removeById(id));
    }

    @PostMapping("/del/batch")
    public R<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return R.success(menuService.removeByIds(ids));
    }

    @GetMapping
    public R<List<Menu>> findAll(@RequestParam(defaultValue = "") String name) {

        List<Menu> menus = menuService.findMenus(name);
        return R.success(menus);
    }

    @GetMapping("/{id}")
    public R<Menu> findOne(@PathVariable Integer id) {
        return R.success(menuService.getById(id));
    }

    @GetMapping("/page")
    public R<Page<Menu>> findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return R.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
