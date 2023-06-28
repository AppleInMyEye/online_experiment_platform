package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.entity.Menu;

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
