package com.ustc.oep.controller;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.Course;
import com.ustc.oep.entity.Take;
import com.ustc.oep.service.CourseService;
import com.ustc.oep.service.TakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Take take){
        take.setUid((Long) request.getSession().getAttribute("uuid"));
        log.info(take.toString());
        takeService.save(take);
        return R.success("选课成功");
    }

    @GetMapping("/list")
    public R<List<Course>> list(HttpServletRequest request){
        Long uid = (Long) request.getSession().getAttribute("uuid");
        List<Course> list = CourseService.selectCourseByUid(uid);
        return R.success(list);
    }
}
