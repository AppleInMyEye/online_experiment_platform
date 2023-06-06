package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-03-18 12:01
 */
@Data
public class AssignSubmit {
    private Long assignSubmitId;

    private Long assignId;

    private Long uuid;

    private byte[] content;

    private String filename;

    private float score;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime;
}
