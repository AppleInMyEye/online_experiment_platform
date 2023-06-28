package com.ustc.oep.controller;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.Course;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.Take;
import com.ustc.oep.service.CourseService;
import com.ustc.oep.service.TakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 10:25
 */
@RestController
@RequestMapping("/take")
@Slf4j
public class TakeController {
    @Autowired
    private TakeService takeService;
    @Autowired
    private CourseService CourseService;

    /**
     * 选课
     */
    @PostMapping
    public R<String> save(@RequestBody Take take){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uuid = loginUser.getUserInfo().getId();
        take.setUid(uuid);
        log.info(take.toString());
        takeService.save(take);
        return R.success("选课成功");
    }

    /**
     * 根据用户获得所选课程列表
     */
    @GetMapping("/list")
    public R<List<Course>> getCousesByUuid(){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uuid = loginUser.getUserInfo().getId();
        List<Course> list = CourseService.selectCourseByUid(uuid);
        return R.success(list);
    }

    /**
     * 根据课程id获得选课学生列表
     */
    @GetMapping("/getUserInfosByCourseId")
    public R<List<LoginUser>> getUserInfosByCourseId(@RequestParam("courseId") Long courseId){
        List<LoginUser> list = takeService.getUserInfosByCourseId(courseId);
        return R.success(list);
    }

    /**
     * 退课
     * @param takeId 选课id
     */
   @GetMapping("/withdraw")
    public R<String> withdraw(@RequestParam("takeId") Long takeId){
        takeService.removeById(takeId);
        return R.success("退课成功");
    }
}
