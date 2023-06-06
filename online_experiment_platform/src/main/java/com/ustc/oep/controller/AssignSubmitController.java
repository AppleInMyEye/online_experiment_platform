package com.ustc.oep.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.AssignSubmit;
import com.ustc.oep.entity.Assignment;
import com.ustc.oep.service.AssignSubmitService;
import com.ustc.oep.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YuJianhua
 * @create 2023-03-18 12:37
 * 作业提交
 */
@RestController
@RequestMapping("/assignSubmit")
@Slf4j
public class AssignSubmitController {
    @Autowired
    private AssignSubmitService assignSubmitService;
    @Autowired
    private AssignmentService assignmentService;

    //上传文件
    @PostMapping("/upload")
    public R<String> upload(HttpServletRequest request,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("assignId") Long assignId){
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("assign_id", assignId);
        Assignment assignment = assignmentService.getOne(queryWrapper);
        if(assignment == null){
            return R.error("任务不存在");
        }
        try {
            if(file.isEmpty()){
                return R.error("文件为空");
            }

            Long uuid = (Long) request.getSession().getAttribute("uuid");
            QueryWrapper<AssignSubmit> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("assign_id", assignId).eq("uuid", uuid);
            AssignSubmit assignSubmit = assignSubmitService.getOne(queryWrapper1);
            if(assignSubmit == null){
                assignSubmit = new AssignSubmit();
                assignSubmit.setAssignId(assignId);
                assignSubmit.setUuid(uuid);
                byte[] content = file.getBytes();
                String fileName = file.getOriginalFilename();
                assignSubmit.setContent(content);
                assignSubmit.setFilename(fileName);
                assignSubmitService.save(assignSubmit);
            }else{
                byte[] content = file.getBytes();
                String fileName = file.getOriginalFilename();
                assignSubmit.setContent(content);
                assignSubmit.setFilename(fileName);
                UpdateWrapper<AssignSubmit> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("assign_id", assignId).eq("uuid", uuid);
                updateWrapper.set("content", content).set("filename", fileName);
                assignSubmitService.update(updateWrapper);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.success(assignment.getAssignName());
    }

    //下载文件
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestBody AssignSubmit assignSubmit) {
        QueryWrapper<AssignSubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", assignSubmit.getUuid()).eq("assign_id", assignSubmit.getAssignId());
        AssignSubmit as = assignSubmitService.getOne(queryWrapper);
        if (as == null) {
            log.info("所提交作业不存在");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            try {
                byte[] content = as.getContent();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", as.getFilename());
                headers.setContentLength(content.length);
                return new ResponseEntity<>(content, headers, HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
