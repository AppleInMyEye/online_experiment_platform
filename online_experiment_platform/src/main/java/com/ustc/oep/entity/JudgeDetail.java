package com.ustc.oep.entity;

import lombok.Data;

/**
 * @author YuJianhua
 * @create 2023-06-20 16:59
 */
@Data
public class JudgeDetail {
    //{"testPointID":8, "timeConsume":1, "memConsume":2024, "returnVal":0, "result":"Accepted"}
    private Integer testPointID;
    private Integer timeConsume;
    private Integer memConsume;
    private Integer returnVal;
    private String result;
}
