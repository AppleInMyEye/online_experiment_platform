package com.ustc.oep.handler;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.ustc.oep.common.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YuJianhua
 * @create 2023-05-28 16:06
 * @Description 认证失败处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常
        R result = new R();
        result.setCode(HttpStatus.UNAUTHORIZED.value());
        result.setMsg("用户认证失败请查询登录");
        String json = JSON.toJSONString(result);

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().print(json);
    }
}
