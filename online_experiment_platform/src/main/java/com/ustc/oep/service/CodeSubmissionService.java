package com.ustc.oep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ustc.oep.dto.CodeRequest;
import com.ustc.oep.entity.CodeSubmission;

/**
 * @author YuJianhua
 * @create 2023-06-04 9:38
 */
public interface CodeSubmissionService extends IService<CodeSubmission> {
    public void codeSubmit(CodeRequest codeRequest);
//    public void getCodeSubmissionId(CodeSubmission codeSubmission);
    public int getSubmitNum(int problemId, Long userId);
}
