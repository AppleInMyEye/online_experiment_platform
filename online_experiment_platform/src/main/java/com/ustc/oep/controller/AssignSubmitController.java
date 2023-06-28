package com.ustc.oep.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.AssignSubmit;
import com.ustc.oep.entity.Assignment;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.service.AssignSubmitService;
import com.ustc.oep.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

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
    @Value("${online-experiment-platform.assignment-submit-path}")
    private String assignmentSubmitPath;
    //上传文件
    @PostMapping("/upload")
    public R<String> upload(@RequestParam("file") MultipartFile file,
                            @RequestParam("assignId") Long assignId){
        Assignment assignment = assignmentService.getById(assignId);
        if(assignment == null){
            return R.error("任务不存在");
        }
        try {
            if(file.isEmpty()){
                return R.error("文件为空");
            }
            //获取当前登录用户的ID
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long uuid = loginUser.getUserInfo().getId();

            //获得文件名
            String OriginalFilename = file.getOriginalFilename();
            String type = FileUtil.extName(OriginalFilename);
            Long size = file.getSize();

            // 生成唯一的文件名
            String fastSimpleUUID = IdUtil.fastSimpleUUID();
            String fileUUID = uuid + StrUtil.DOT + type;
            // 保存文件
            File uploadFile = new File(assignmentSubmitPath + fileUUID);
            // 判断文件夹是否存在
            File parentFile = uploadFile.getParentFile();
            if(!parentFile.exists()){
                // 创建文件夹
                boolean mkdirs = parentFile.mkdirs();
                if(!mkdirs){
                    return R.error("文件夹创建失败");
                }
            }

            String url;

            // 判断是否是第一次提交
            QueryWrapper<AssignSubmit> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uuid", uuid).eq("assign_id", assignId);
            AssignSubmit assignSubmit = assignSubmitService.getOne(queryWrapper);
            if(assignSubmit == null){
                // 第一次提交
                // 保存文件
                file.transferTo(uploadFile);
                // 保存文件路径
                url = uploadFile.getAbsolutePath();
                // 保存提交记录
                assignSubmit = new AssignSubmit();
                assignSubmit.setUuid(uuid);
                assignSubmit.setAssignId(assignId);
                assignSubmit.setFilename(OriginalFilename);
                assignSubmit.setUrl(url);
                assignSubmit.setSize(size);
                assignSubmit.setType(type);
                assignSubmit.setStatus("已提交");
                assignSubmitService.save(assignSubmit);
            }else{
                // 非第一次提交
                // 删除原有文件
                if(StrUtil.isNotBlank(assignSubmit.getUrl())){
                    File oldFile = new File(assignmentSubmitPath + assignSubmit.getUrl());
                    if(oldFile.exists()){
                        boolean delete = oldFile.delete();
                        if(!delete){
                            log.info("原有文件删除失败");
                        }
                    }
                }
                // 保存文件
                file.transferTo(uploadFile);
                // 保存文件路径
                url = uploadFile.getAbsolutePath();
                // 更新提交记录
                UpdateWrapper<AssignSubmit> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("uuid", uuid).eq("assign_id", assignId);
                assignSubmit.setFilename(OriginalFilename);
                assignSubmit.setUrl(url);
                assignSubmit.setSize(size);
                assignSubmit.setType(type);
                assignSubmit.setStatus("已提交");
                assignSubmitService.update(assignSubmit, updateWrapper);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.success(assignment.getAssignName());
    }

    //下载文件
    @GetMapping("/download/{assignId}")
    public ResponseEntity<byte[]> download(@PathVariable("assignId") Long assignId) {
        // 获取当前登录用户的ID
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long uuid = loginUser.getUserInfo().getId();
        // 获取提交的作业
        QueryWrapper<AssignSubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid).eq("assign_id", assignId);
        AssignSubmit assignSubmit = assignSubmitService.getOne(queryWrapper);
        if(assignSubmit == null){
            log.info("提交不存在");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            // 获取文件
            String url = assignSubmit.getUrl();
            File file = new File(url);
            if(!file.exists()){
                log.info("文件不存在");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                try {
                    byte[] content = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", assignSubmit.getFilename());
                    headers.setContentLength(content.length);
                    return new ResponseEntity<>(content, headers, HttpStatus.OK);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //查看作业提交情况
    @GetMapping("/getAssignSubmitByAssignId")
    public R<List<AssignSubmit>> getAssignSubmit(@RequestParam("assignId") Long assignId){
        QueryWrapper<AssignSubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("assign_id", assignId);
        List<AssignSubmit> list = assignSubmitService.list(queryWrapper);
        return R.success(list);
    }

    @GetMapping("/scored")
    public R<String> scored(@RequestParam("assignSubmitId") Long assignSubmitId,
                            @RequestParam("score") float score){
        UpdateWrapper<AssignSubmit> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("assign_submitId", assignSubmitId);
        updateWrapper.set("score", score);
        assignSubmitService.update(updateWrapper);
        return R.success("打分成功");
    }

}
