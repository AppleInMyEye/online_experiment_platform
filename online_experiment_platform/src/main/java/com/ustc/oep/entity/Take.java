package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YuJianhua
 * @create 2023-03-17 10:19
 */
@Data
public class Take implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long takeId ;
    private Long uid;
    private Long courseId ;
}
