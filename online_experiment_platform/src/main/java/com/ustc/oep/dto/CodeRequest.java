package com.ustc.oep.dto;

import lombok.Data;

/**
 * @author YuJianhua
 * @create 2023-06-04 9:43
 * 用于封装向判题机发送的请求
 */

@Data
public class CodeRequest {

    private Long submitId;

    private int problemId;

    private int timeLimit;

    private int memLimit;

    private int codeLength;

    private String language;

    private int testpointCount;

    private String sourcecode;
}
