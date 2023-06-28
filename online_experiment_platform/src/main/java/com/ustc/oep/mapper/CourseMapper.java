package com.ustc.oep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ustc.oep.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 9:52
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("select * from course natrual join take where take.uid = #{uid}")
    List<Course> selectCourseByUid(@Param("uid") Long uid);

    List<Course> search(String keyword);
}
