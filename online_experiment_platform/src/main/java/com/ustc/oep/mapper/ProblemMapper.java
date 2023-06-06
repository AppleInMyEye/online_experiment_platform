package com.ustc.oep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ustc.oep.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YuJianhua
 * @create 2023-06-01 16:12
 */
@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {
    void updateTestPointNumById(int testPointNum, int problemId);
    void addTestPointNumById(int problemId);
    int getTestPointNumById(int problemId);
}
