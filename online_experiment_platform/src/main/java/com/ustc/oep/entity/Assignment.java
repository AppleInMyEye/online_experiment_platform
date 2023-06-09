package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-03-17 11:44
 */
@Data
public class Assignment {
    @TableId(type = IdType.AUTO)
    private Long assignId;

    private String assignName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime;

    private Long courseId;

    private String description;

    private byte[] content;

    private String filename;

    // 0:有效
    private Integer status;

    private String courseName;

    private String md5;
    private String url;
    private String type;
    private Long size;
}
