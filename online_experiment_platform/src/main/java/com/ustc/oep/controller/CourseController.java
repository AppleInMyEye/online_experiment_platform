package com.ustc.oep.controller;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.Course;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:58
 */
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 新增课程
     */
    @PostMapping
    public R<String> save(@RequestBody Course course){
        log.info(course.toString());

        courseService.saveOrUpdate(course);

        return R.success("新增课程成功");
    }

    /**
     * 查询所有课程列表
     */
    @GetMapping("/list")
    public R<List<Course>> list(){
        List<Course> list = courseService.list();
        return R.success(list);
    }

    /**
     * 分页查询课程列表
     */
    @GetMapping("/page")
    public R<List<Course>> listByPage(@RequestParam String name,
                                      @RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize){
        List<Course> list = courseService.getCourseByPage(pageNum, pageSize, name);
        return R.success(list);
    }

    /**
     * 根据用户获得所选课程列表
     */
    @GetMapping("/mycourse")
    public R<List<Course>> listByUid(){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Course> list = courseService.selectCourseByUid(loginUser.getUserInfo().getId());
        return R.success(list);
    }

    /**
     * 更新课程
     */
    @PostMapping("/update")
    public R<String> update(@RequestBody Course course){
        log.info(course.toString());

        courseService.updateById(course);

        return R.success("更新课程成功");
    }

    /**
     * 删除课程
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        courseService.removeById(id);
        return R.success("删除课程成功");
    }

    /**
     * 批量删除课程
     */
    @PostMapping("/del/batch")
    public R<String> deleteBatch(@RequestBody List<Long> ids){
        courseService.removeByIds(ids);
        return R.success("批量删除课程成功");
    }
    /**
     * 根据id获得课程
     */
    @GetMapping("/{id}")
public R<Course> get(@PathVariable Long id){
        Course course = courseService.getById(id);
        return R.success(course);
    }

    /**
     * 根据关键字搜索课程
     */
    @GetMapping("/search/{keyword}")
    public R<List<Course>> search(@PathVariable String keyword){
        List<Course> list = courseService.search(keyword);
        return R.success(list);
    }
}
