package com.ustc.oep.dto;

import lombok.Data;

@Data
public class JudgeResultDTO {
    private Integer compileRetVal;

    private String compileResult;

    private Integer timeConsume;

    private Integer memConsume;

    private String finalResult;

    private Integer sampleTotal;

    private Integer acceptNum;

    private String output;

    private String input;

    private String expectResult;
}
