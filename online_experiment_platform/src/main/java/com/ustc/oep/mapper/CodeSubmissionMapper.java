package com.ustc.oep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ustc.oep.entity.CodeSubmission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YuJianhua
 * @create 2023-06-04 9:37
 */
@Mapper
public interface CodeSubmissionMapper extends BaseMapper<CodeSubmission> {
    public int getSubmitNum(int problemId, Long userId);
}
