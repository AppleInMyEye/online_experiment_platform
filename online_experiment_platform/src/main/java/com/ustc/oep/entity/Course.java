package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:41
 */
@Data
public class CourseEntity {
    private Integer courseId ;
    /** 姓名 */
    private String courseName ;
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime ;
    /** 课程开始时间 */
    private LocalDateTime startTime ;
    /** 课程结束时间 */
    private LocalDateTime endTime ;
    /** 上次修改时间 */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime ;
    /** 教师id */
    private Integer teacherId1 ;
    /** 教师id */
    private Integer teacherId2 ;
}
