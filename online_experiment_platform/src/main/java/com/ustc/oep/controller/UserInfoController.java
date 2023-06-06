package com.ustc.oep.controller;

/**
 * @author YuJianhua
 * @create 2023-03-16 21:11
 */
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ustc.oep.common.R;
import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.entity.UserInfo;
import com.ustc.oep.service.LoginService;
import com.ustc.oep.service.UserInfoService;
import com.ustc.oep.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    LoginService LoginService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RedisCache redisCache;

    @PostMapping("/login")
    public R<Map<String,String>> login(@RequestBody UserInfo userInfo) {
        return LoginService.login(userInfo);
    }

    @PostMapping("/logout")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public R<String> logout(){
        //获取SecurityContextHolder中的用户信息
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uuid = loginUser.getUserInfo().getUuid();
        redisCache.deleteObject("login:"+uuid);
        return R.success("退出成功");
    }

    @PostMapping("/register")
    public R<String> save(HttpServletRequest request, @RequestBody UserInfo userInfo){
        log.info(userInfo.toString());
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        userInfoService.save(userInfo);

        return R.success("新增用户成功");
    }

    @GetMapping("/info")
    public R<UserInfo> info(){
        //获取SecurityContextHolder中的用户信息
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long uuid = loginUser.getUserInfo().getUuid();
        UserInfo userInfo = loginUser.getUserInfo();
        return R.success(userInfo);
    }
}
