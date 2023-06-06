package com.ustc.oep.filter;

import com.ustc.oep.entity.LoginUser;
import com.ustc.oep.utils.JwtUtil;
import com.ustc.oep.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = httpServletRequest.getHeader("token");

        //判断是否有token
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return ;
        }

        //解析token
        String uuid;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            uuid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token错误");
        }

        //从Redis中获取用户信息
        String redisKey = "login:" + uuid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        //把用户信息放入SecurityContextHolder中
        // 获取权限信息封装到AuthenticationToken中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
