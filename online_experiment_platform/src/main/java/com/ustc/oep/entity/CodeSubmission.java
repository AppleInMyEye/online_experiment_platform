package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-06-04 9:29
 * 提交的代码
 */
@Data
public class CodeSubmission {

    private Long submissionId;

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
