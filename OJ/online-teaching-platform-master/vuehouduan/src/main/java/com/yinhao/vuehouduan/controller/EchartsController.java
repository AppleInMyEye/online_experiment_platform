package com.yinhao.vuehouduan.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.yinhao.vuehouduan.common.Result;
import com.yinhao.vuehouduan.entity.User;
import com.yinhao.vuehouduan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private IUserService userService;
    @GetMapping("/example")
    public Result get() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(1500, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    @GetMapping("/members")
    public Result members(){
        List<User> list = userService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User user: list){
            Date createtime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createtime);
            switch (quarter) {
                case Q1:q1 += 1;break;
                case Q2:q2 += 1;break;
                case Q3:q3 += 1;break;
                case Q4:q4 += 1;break;
                default:break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1,q2,q3,q4));
    }
}
