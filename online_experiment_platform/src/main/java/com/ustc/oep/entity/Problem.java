package com.ustc.oep.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-06-01 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer problemId;

    private String title;

    private String description;

    private Integer timeLimit;

    private Integer memoryLimit;

    private String difficulty;

    private String sampleInput;

    private String sampleOutput;

    private Integer testPointNum;

    private Integer acceptNum;

    //热门程度
    private Integer hotDegree;

    //数据库中存储为Text类型，但是在Java中使用List<String>类型
    @JSONField(serialize = false)
    @TableField(typeHandler = com.ustc.oep.handler.ListToStringTypeHandler.class)
    private List<String> testPointInput;

    @JSONField(serialize = false)
    @TableField(typeHandler = com.ustc.oep.handler.ListToStringTypeHandler.class)
    private List<String> testPointOutput;

    private String tips;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime ;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime ;
    //0代表删除 1代表有效
    private Integer status;
}
