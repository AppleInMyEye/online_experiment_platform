package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-06-04 9:29
 * 提交的代码
 */
@Data
public class CodeSubmission {
    @TableId(type = IdType.AUTO)
    @TableField("submit_id")
    private Long submitID;

    private Integer problemId;

    private String language;

    private String sourceCode;

    private Integer codeLength;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime;
    // 0为有效，1为无效, 2为已经判题
    private Integer status;

    private Long userId;

    private Integer submitNum;
}
