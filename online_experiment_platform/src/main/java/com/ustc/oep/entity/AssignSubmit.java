package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-03-18 12:01
 */
@Data
public class AssignSubmit {
    @TableId(type = IdType.AUTO)
    private Long assignSubmitId;

    private Long assignId;

    private Long uuid;

    private String filename;

    private float score;

    // 0:有效
    private String status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime;

    private String md5;
    private String url;
    private String type;
    private Long size;
}
