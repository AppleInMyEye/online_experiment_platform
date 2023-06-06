package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.entity.TestPoint;

/**
 * @author YuJianhua
 * @create 2023-06-01 18:33
 */
public interface TestPointService extends IService<com.ustc.oep.entity.TestPoint> {

    public void testPointSubmit(TestPoint testPoint, int TestPointNum);
}
