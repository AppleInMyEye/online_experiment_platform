package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:41
 */
@Data
public class Course {
    @TableId(type = IdType.AUTO)
    private Long courseId ;
    /** 姓名 */
    private String courseName ;
    /** 课程描述 */
    private String courseDesc ;
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime ;
    /** 课程开始时间 */
    private LocalDateTime startTime ;
    /** 课程结束时间 */
    private LocalDateTime endTime ;
    /** 上次修改时间 */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime ;
    /** 教师id */
    private Integer teacherId ;
    /** 教师姓名 */
    private String teacherName ;
    /** 教师id */
    private Integer teacherId2 ;
    // 课程状态 0 存在 1 删除
    private Integer status ;
}
