package com.ustc.oep.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserInfo implements Serializable{
    /** 用户id */
    @TableId(type = IdType.AUTO)
    private Long id ;
    /** 用户名 */
    private String username ;
    /** 角色 */
    private String role;
    /** 姓名 */
    private String name ;
    /** 性别 */
    private String gender ;
    /** 手机号 */
    private String phone ;
    /** 出生日期 */
    private Date birthday ;
    /** 电子邮箱地址 */
    private String emailAddress ;
    /** 登录密码 */
    private String password ;
    /** 学校 */
    private String school ;
    /** 专业 */
    private String major ;
    /** 学号/职工号 */
    private String schoolId ;
    /** 头像图片地址 */
    private String avatarUrl ;
    /** 个性签名 */
    private String signature ;
    /** 用户昵称 */
    private String nickname ;
    /** 用户地址 */
    private String address ;
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime ;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastModifiedTime ;
    /**  */
    private String rid ;
//    /** 权限星系 */
//    private List<String> permissions;
//
    /** 0代表删除 1代表有效 */
    private Integer status;
}
