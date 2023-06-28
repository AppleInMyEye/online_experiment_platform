package com.yinhao.vuehouduan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yinhao
 * @since 2023-06-08
 */
@Getter
@Setter
  @TableName("sys_course")
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("课程Id")
        private Integer id;

      @ApiModelProperty("课程名称")
      private String name;

      @ApiModelProperty("授课老师Id")
      private Integer teacherid;

      @ApiModelProperty("授课老师")
      private String teacher;


}
