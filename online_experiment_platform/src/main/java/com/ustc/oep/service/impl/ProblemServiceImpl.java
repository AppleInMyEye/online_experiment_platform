package com.ustc.oep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ustc.oep.entity.Problem;
import com.ustc.oep.mapper.ProblemMapper;
import com.ustc.oep.service.ProblemService;
import org.springframework.stereotype.Service;

/**
 * @author YuJianhua
 * @create 2023-06-01 16:10
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
    @Override
    public void updateTestPointNumById(int problemId, int testPointNum) {
        baseMapper.updateTestPointNumById(testPointNum,problemId);
    }

    @Override
    public void addTestPointNumById(int problemId) {
        baseMapper.addTestPointNumById(problemId);
    }

    @Override
    public int getTestPointNumById(int problemId) {
        return baseMapper.getTestPointNumById(problemId);
    }
}
