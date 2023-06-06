//package com.ustc.oep.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.ustc.oep.common.BaseContext;
//import com.ustc.oep.common.R;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author YuJianhua
// * @create 2023-03-17 8:57
// */
//@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
//@Slf4j
//public class LoginCheckFilter implements Filter {
//    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String uri = request.getRequestURI();
//
//        log.info("拦截到请求：{}", uri);
//
//        String[] urls = new String[]{
//                "/userInfo/login",
//                "/userInfo/register",
//                "/ws/**",
//                "/save",
//                "/screen-sharing/**",
//
//                "/userInfo/",
//                "/backend/**",
//                "/front/**",
//                "/common/**",
//                "/user/sendMsg",
//                "/user/login"
//        };
//
//        boolean check = check(urls, uri);
//
//        if (check) {
//            log.info("本次请求{}不需要处理", uri);
//            filterChain.doFilter(request, response);
//
//            return;
//        }
//
//        //判断用户登录状态,已登录则放行
//        if (request.getSession().getAttribute("uuid") != null) {
//            log.info("用户{}已登录", request.getSession().getAttribute("uuid"));
//            long uuid = (long) request.getSession().getAttribute("uuid");
//            BaseContext.setCurrentId(uuid);
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//        log.info("用户未登录");
//        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
//
//        return;
//    }
//
//    public boolean check(String[] urls, String requestURI) {
//        for (String url : urls) {
//            boolean match = PATH_MATCHER.match(url, requestURI);
//            if (match)
//                return true;
//        }
//        return false;
//    }
//}
