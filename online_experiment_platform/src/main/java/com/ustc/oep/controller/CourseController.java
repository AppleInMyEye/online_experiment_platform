package com.ustc.oep.controller;

import com.ustc.oep.common.R;
import com.ustc.oep.entity.Course;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Course course){
        log.info(course.toString());

        courseService.save(course);

        return R.success("新增课程成功");
    }
}
