package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author YuJianhua
 * @create 2023-06-01 18:28
 */
@Data
public class TestPoint {
    @TableId(type = IdType.AUTO)
    private Integer testPointId;

    private Integer problemId;

    private String testPointInput;

    private String testPointOutput;
}
