package com.ustc.oep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.Take;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YuJianhua
 * @create 2023-03-17 10:22
 */
@Mapper
public interface TakeMapper extends BaseMapper<Take> {
    List<LoginUser> getUserInfosByCourseId(Long courseId);
}
