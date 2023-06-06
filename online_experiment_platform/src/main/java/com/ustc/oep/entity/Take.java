package com.ustc.oep.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YuJianhua
 * @create 2023-03-17 10:19
 */
@Data
public class Take implements Serializable {
    private Long takeId ;
    private Long uid;
    private Long courseId ;
}
