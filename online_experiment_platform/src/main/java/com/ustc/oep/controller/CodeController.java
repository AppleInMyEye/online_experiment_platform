package com.ustc.oep.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.dto.CodeRequest;
import com.ustc.oep.entity.CodeSubmission;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.Problem;
import com.ustc.oep.service.CodeSubmissionService;
import com.ustc.oep.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author YuJianhua
 * @create 2023-06-01 14:49
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    ProblemService problemService;
    @Autowired
    CodeSubmissionService codeSubmissionService;

    /**
     * 提交代码
     * @param codeSubmission
     * @return
     */
    @PostMapping("/codeSubmit")
    public R<String> codeSubmit(@RequestBody CodeSubmission codeSubmission){
        //保存这次代码提交
        codeSubmission.setCodeLength(codeSubmission.getSourceCode().length());
        //从token中获取用户id,设置到codeSubmission中
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        codeSubmission.setUserId(loginUser.getUserInfo().getUuid());
        //获取这个用户这道题的提交次数，设置到codeSubmission中
        int submitNum = codeSubmissionService.getSubmitNum(codeSubmission.getProblemId(), codeSubmission.getUserId());
        codeSubmission.setSubmitNum(submitNum + 1);
        //保存codeSubmission
        codeSubmissionService.save(codeSubmission);
        //获取这次提交的submissionId
        QueryWrapper<CodeSubmission> qw = new QueryWrapper<>();
        qw.eq("problem_id", codeSubmission.getProblemId());
        qw.eq("user_id", codeSubmission.getUserId());
        qw.eq("submit_num", codeSubmission.getSubmitNum());
        CodeSubmission one = codeSubmissionService.getOne(qw);
        Long submissionId = one.getSubmissionId();

        // 将codeSubmission转换为CodeRequest
        CodeRequest codeRequest = new CodeRequest();
        codeRequest.setSubmitId(submissionId);
        codeRequest.setCodeLength(codeSubmission.getCodeLength());
        codeRequest.setLanguage(codeSubmission.getLanguage());
        codeRequest.setProblemId(codeSubmission.getProblemId());
        codeRequest.setSourcecode(codeSubmission.getSourceCode());
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("problem_id", codeSubmission.getProblemId());
        Problem problem = problemService.getOne(queryWrapper);
        codeRequest.setMemLimit(problem.getMemoryLimit());
        codeRequest.setTimeLimit(problem.getTimeLimit());
        codeRequest.setTestpointCount(problem.getTestPointNum());

        // 发送判题请求
        codeSubmissionService.codeSubmit(codeRequest);


        return R.success("提交成功");
    }
}
