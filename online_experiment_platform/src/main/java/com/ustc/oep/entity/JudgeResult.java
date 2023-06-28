package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;
@Data
public class JudgeResult {
    //{"submitID": 0, "judgeTime":1687683443064, "compileRetVal":0, "compileResult":"", "timeConsume":1, "memConsume":2024, "finalResult":"Accepted", "message":"", "judgeDetails": [{"testPointID":1, "timeConsume":1, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":2, "timeConsume":1, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":3, "timeConsume":1, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":4, "timeConsume":0, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":5, "timeConsume":0, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":6, "timeConsume":0, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":7, "timeConsume":1, "memConsume":2024, "returnVal":0, "result":"Accepted"},{"testPointID":8, "timeConsume":1, "memConsume":2024, "returnVal":0, "result":"Accepted"}]}
    private Long judgeResultId;

    private Long submitID;

    private Long judgeTime;

    private Integer compileRetVal;

    private String compileResult;

    private Integer timeConsume;

    private Integer memConsume;

    private String finalResult;

    private String message;

    @TableField(exist = false)
    private List<JudgeDetail> judgeDetails;

    private String output;
}
