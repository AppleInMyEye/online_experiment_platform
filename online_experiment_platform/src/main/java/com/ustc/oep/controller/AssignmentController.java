package com.ustc.oep.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.Assignment;
import com.ustc.oep.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
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
    @Value("${online-experiment-platform.assignment-path}")
    private String assignmentPath;
    /**
     * 新增作业
     * @param assignment 作业
     * @return 保存结果
     */
    @PostMapping
    public R<String> save(@RequestBody Assignment assignment){
        log.info(assignment.getAssignName());

        assignmentService.saveOrUpdate(assignment);

        return R.success("新增用户成功");
    }

    /**
     * 根据课程ID获得其作业列表
     * @param courseId 课程ID
     * @return 作业列表
     */
    @GetMapping("/{courseId}")
    public R<List<Assignment>> list(@PathVariable Long courseId){
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<Assignment> list = assignmentService.list(queryWrapper);
        return R.success(list);
    }

    /**
     * 获得所有作业列表
     * @return 作业列表
     */
    @GetMapping
    public R<List<Assignment>> list(){
        List<Assignment> list = assignmentService.list();
        return R.success(list);
    }

    /**
     * 提交作业
     * @param assignId 作业ID
     * @param file 作业文件
     * @return 提交结果
     */
    @PostMapping("/upload")
    public R<String> upload(@RequestParam("file") MultipartFile file,
                            @RequestParam("assignId") Long assignId) throws IOException {
        Assignment assignment = assignmentService.getById(assignId);
        if(assignment == null){
            return R.error("作业不存在");
        }
        // 获得文件名
        String OriginalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(OriginalFilename);
        Long size = file.getSize();

        // 生成唯一的文件名
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;

        // 保存文件
        File uploadFile = new File(assignmentPath + fileUUID);
        // 判断文件夹是否存在
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            // 创建文件夹
            boolean mkdirs = parentFile.mkdirs();
            if(!mkdirs){
                return R.error("文件夹创建失败");
            }
        }
        // 判断文件是否存在
        String url;
        String md5 = SecureUtil.md5(file.getInputStream());
        if(md5.equals(assignment.getMd5())){
            url = assignment.getUrl();
            return R.success("文件未修改");
        }
        // 删除原有文件
        if(StrUtil.isNotBlank(assignment.getUrl())){
            File oldFile = new File(assignmentPath + assignment.getUrl());
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

        UpdateWrapper<Assignment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("assign_id", assignId);
        updateWrapper.set("url", url).
                set("md5", md5).
                set("filename", OriginalFilename).
                set("size", size).
                set("type", type);
        assignmentService.update(updateWrapper);
        return R.success("文件上传成功");
    }

    /**
     * 下载作业
     * @param assignId 作业ID
     * @return 作业文件
     */
    @GetMapping("/download/{assignId}")
    public ResponseEntity<byte[]> download(@PathVariable("assignId") Long assignId) {
        Assignment assignment = assignmentService.getById(assignId);
        if (assignment == null) {
            log.info("作业不存在");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            try {
                File file = new File(assignment.getUrl());
                byte[] content = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
//                byte[] content = Files.readAllBytes(file.toPath());
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

    /**
     * 删除作业
     * @param assignId 作业ID
     * @return 删除结果
     */
    @DeleteMapping("/{assignId}")
    public R<String> delete(@PathVariable("assignId") Long assignId){
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("assign_id", assignId);
        Assignment assignment = assignmentService.getOne(queryWrapper);
        if(assignment == null){
            return R.error("作业不存在");
        }
        assignmentService.remove(queryWrapper);
        return R.success("删除成功");
    }

    /**
     * 批量删除作业
     * @param assignIds 作业ID列表
     * @return 删除结果
     */
    @PostMapping("/del/batch")
    public R<String> deleteBatch(@RequestBody List<Long> assignIds){
        return R.success(assignmentService.removeByIds(assignIds) ? "删除成功" : "删除失败");
    }

    /**
     * 分页查询作业
     * @param assignName 作业名称
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return 作业列表
     */
    @GetMapping("/page")
    public R<Page<Assignment>> findPage(@RequestParam String assignName,
                                        @RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize) {
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("assign_name", assignName);
        queryWrapper.orderByDesc("assign_id");
        return R.success(assignmentService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
