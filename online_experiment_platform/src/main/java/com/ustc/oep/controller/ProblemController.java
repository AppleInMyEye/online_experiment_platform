package com.ustc.oep.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.Problem;
import com.ustc.oep.entity.TestPoint;
import com.ustc.oep.service.ProblemService;
import com.ustc.oep.service.TestPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YuJianhua
 * @create 2023-06-01 16:08
 */
@RestController
@RequestMapping("/problem")
@Slf4j
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private TestPointService testPointService;

    @PostMapping("/problemSubmit")
    public R<String> problemSubmit(@RequestBody Problem problem){
        problemService.save(problem);
        return R.success("提交成功");
    }

    @GetMapping("/getProblemById/{id}")
    public R<Problem> getProblemById(@PathVariable("id") Long id){
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("problem_id",id);
        Problem problem = problemService.getOne(queryWrapper);
        return R.success(problem);
    }

    @PostMapping("/testPointSubmit")
    public R<String> testPointSubmit(@RequestBody TestPoint testPoint){
        testPointService.save(testPoint);
        //problem表中的 testPointNum += 1
        problemService.addTestPointNumById(testPoint.getProblemId());
        int testPointNum = problemService.getTestPointNumById(testPoint.getProblemId());
        testPointService.testPointSubmit(testPoint,testPointNum);
        return R.success("提交成功");
    }


}
