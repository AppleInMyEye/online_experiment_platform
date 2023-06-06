package com.ustc.oep.handler;

import com.ustc.oep.common.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YuJianhua
 * @create 2023-05-28 16:23
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        R result = new R();
        result.setCode(HttpStatus.FORBIDDEN.value());
        result.setMsg("权限不足");
        String json = result.toString();

        //处理异常
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().print(json);
    }
}
