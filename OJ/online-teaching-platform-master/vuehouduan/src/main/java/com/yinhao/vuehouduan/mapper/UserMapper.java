package com.yinhao.vuehouduan.mapper;

import com.yinhao.vuehouduan.controller.dto.UserPasswordDTO;
import com.yinhao.vuehouduan.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yinhao
 * @since 2023-03-24
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);
}
