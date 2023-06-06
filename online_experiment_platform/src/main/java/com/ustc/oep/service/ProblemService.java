package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.entity.Problem;

/**
 * @author YuJianhua
 * @create 2023-06-01 16:10
 */
public interface ProblemService extends IService<Problem> {
    void updateTestPointNumById(int problemId,int testPointNum);
    void addTestPointNumById(int problemId);
    int getTestPointNumById(int problemId);
}
