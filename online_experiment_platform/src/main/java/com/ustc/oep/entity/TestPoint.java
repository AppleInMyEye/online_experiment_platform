package com.ustc.oep.entity;

import lombok.Data;

/**
 * @author YuJianhua
 * @create 2023-06-01 18:28
 */
@Data
public class TestPoint {

    private Integer testPointId;

    private Integer problemId;

    private String testPointInput;

    private String testPointOutput;
}
