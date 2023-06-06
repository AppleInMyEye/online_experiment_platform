package com.ustc.oep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ustc.oep.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectMenuPermsByUserId(Long userId);
}
