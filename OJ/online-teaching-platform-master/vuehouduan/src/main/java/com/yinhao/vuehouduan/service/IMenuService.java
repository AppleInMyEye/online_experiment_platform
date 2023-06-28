package com.yinhao.vuehouduan.service;

import com.yinhao.vuehouduan.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinhao
 * @since 2023-06-03
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
