package com.yinhao.vuehouduan.service;

import com.yinhao.vuehouduan.controller.dto.UserDTO;
import com.yinhao.vuehouduan.controller.dto.UserPasswordDTO;
import com.yinhao.vuehouduan.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinhao
 * @since 2023-03-24
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);
}
