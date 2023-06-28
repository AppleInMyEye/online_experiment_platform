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
  @TableName("sys_assignment")
@ApiModel(value = "Assignment对象", description = "")
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("作业id")
        private Integer id;

      @ApiModelProperty("作业名称")
      private String name;

      @ApiModelProperty("课程id")
      private Integer courseId;

      @ApiModelProperty("课程名称")
      private String course;

      @ApiModelProperty("描述")
      private String description;


}
