package com.ustc.oep.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.Assignment;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 11:50
 */
@RestController
@RequestMapping("/assignment")
@Slf4j
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Assignment assignment){
        log.info(assignment.getAssignName());

        assignmentService.save(assignment);

        return R.success("新增用户成功");
    }

    @GetMapping("/list")
    public R<List<Assignment>> list(HttpServletRequest request, @Param("courseId") Long courseId){
        Long uid = (Long) request.getSession().getAttribute("uuid");
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<Assignment> list = assignmentService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping("/upload")
    public R<String> upload(HttpServletRequest request,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("assignId") Long assignId){
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("assign_id", assignId);
        Assignment assignment = assignmentService.getOne(queryWrapper);
        if(assignment == null){
            return R.error("作业不存在");
        }
        try {
            if(file.isEmpty()){
                return R.error("文件为空");
            }
            byte[] content = file.getBytes();
            String fileName = file.getOriginalFilename();
            assignment.setContent(content);
            UpdateWrapper<Assignment> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("assign_id", assignId);
            updateWrapper.set("content", content).set("filename", fileName);
            assignmentService.update(updateWrapper);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.success(assignment.getAssignName());
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("assignId") Long assignId) {
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("assign_id", assignId);
        Assignment assignment = assignmentService.getOne(queryWrapper);
        if (assignment == null) {
            log.info("作业不存在");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            try {
                byte[] content = assignment.getContent();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", assignment.getFilename());
                headers.setContentLength(content.length);
                return new ResponseEntity<>(content, headers, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
