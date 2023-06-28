package com.ustc.oep.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.dto.CodeRequest;
import com.ustc.oep.dto.JudgeResultDTO;
import com.ustc.oep.entity.*;
import com.ustc.oep.service.CodeSubmissionService;
import com.ustc.oep.service.ProblemService;
import com.ustc.oep.service.TestPointService;
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
    @Autowired
    TestPointService testPointService;

    /**
     * 提交代码
     *
     * @param codeSubmission
     * @return
     */
    @PostMapping("/submit")
    public R<JudgeResultDTO> codeSubmit(@RequestBody CodeSubmission codeSubmission){
        //保存这次代码提交
        codeSubmission.setCodeLength(codeSubmission.getSourceCode().length());
        //从token中获取用户id,设置到codeSubmission中
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        codeSubmission.setUserId(loginUser.getUserInfo().getId());
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
        Long submissionId = one.getSubmitID();

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

        // 更新题目热门程度
        problem.setHotDegree(problem.getHotDegree() + 1);
        problemService.updateById(problem);

        // 发送判题请求
        JudgeResult judgeResult = codeSubmissionService.codeSubmit(codeRequest);

        JudgeResultDTO judgeResultDTO = new JudgeResultDTO();
        judgeResultDTO.setCompileResult(judgeResult.getCompileResult());
        judgeResultDTO.setFinalResult(judgeResult.getFinalResult());
        judgeResultDTO.setMemConsume(judgeResult.getMemConsume());
        judgeResultDTO.setTimeConsume(judgeResult.getTimeConsume());
        judgeResultDTO.setCompileRetVal(judgeResult.getCompileRetVal());
        judgeResultDTO.setCompileRetVal(judgeResult.getCompileRetVal());
        judgeResultDTO.setSampleTotal(problem.getTestPointNum());
        int acceptNum = 0;
        for(JudgeDetail judgeDetail : judgeResult.getJudgeDetails()){
            if(judgeDetail.getResult().equals("Accepted")){
                acceptNum++;
            }
        }
        judgeResultDTO.setAcceptNum(acceptNum);

        if(judgeResult.getCompileRetVal() != 0){
            return R.error(judgeResultDTO);
        }

        if(acceptNum == problem.getTestPointNum()){
            //如果全部通过，更新这道题的通过人数
            problem.setAcceptNum(problem.getAcceptNum() + 1);
            problemService.updateById(problem);

            return R.success(judgeResultDTO);
        }else{
            return R.error(judgeResultDTO);
        }
    }

    /**
     * 测试代码
     * @param codeSubmission
     * @return 测试结果
     */
    @PostMapping("/test")
    public R<JudgeResultDTO> codeTest(@RequestBody CodeSubmission codeSubmission) {
        //保存这次代码提交
        codeSubmission.setCodeLength(codeSubmission.getSourceCode().length());
        //从token中获取用户id,设置到codeSubmission中
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        codeSubmission.setUserId(loginUser.getUserInfo().getId());

        // 将codeSubmission转换为CodeRequest
        CodeRequest codeRequest = new CodeRequest();
        codeRequest.setSubmitId(0L);
        codeRequest.setCodeLength(codeSubmission.getCodeLength());
        codeRequest.setLanguage(codeSubmission.getLanguage());
        codeRequest.setProblemId(codeSubmission.getProblemId());
        codeRequest.setSourcecode(codeSubmission.getSourceCode());
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("problem_id", codeSubmission.getProblemId());
        Problem problem = problemService.getOne(queryWrapper);
        codeRequest.setMemLimit(problem.getMemoryLimit());
        codeRequest.setTimeLimit(problem.getTimeLimit());
        codeRequest.setTestpointCount(1);

        // 发送判题请求
        JudgeResult judgeResult = codeSubmissionService.codeSubmit(codeRequest);

        JudgeResultDTO judgeResultDTO = new JudgeResultDTO();
        judgeResultDTO.setCompileResult(judgeResult.getCompileResult());
        judgeResultDTO.setFinalResult(judgeResult.getFinalResult());
        judgeResultDTO.setOutput(judgeResult.getOutput());
        judgeResultDTO.setMemConsume(judgeResult.getMemConsume());
        judgeResultDTO.setTimeConsume(judgeResult.getTimeConsume());
        judgeResultDTO.setCompileRetVal(judgeResult.getCompileRetVal());
        judgeResultDTO.setExpectResult(problem.getSampleOutput());
        judgeResultDTO.setInput(problem.getSampleInput());
        judgeResultDTO.setCompileRetVal(judgeResult.getCompileRetVal());

        if(judgeResult.getCompileRetVal() != 0){
            return R.error(judgeResultDTO);
        }

        if("Accepted".equals(judgeResult.getJudgeDetails().get(0).getResult())) {
            return R.success(judgeResultDTO);
        }else {
            return R.error(judgeResultDTO);
        }
    }
}
