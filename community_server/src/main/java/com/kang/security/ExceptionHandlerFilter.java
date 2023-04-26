package com.kang.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kang
 * @description 处理 filter 中抛出的异常 ( 通过把 异常处理filter 放在 业务filter 前面 )
 * @date 2023/4/4 20:01
 */
// 目前不用，会引起org.springframework.http.converter.HttpMessageNotWritableException
// 和 java.io.IOException: 你的主机中的软件中止了一个已建立的连接。
//@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Resource(name = "handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        }catch (Exception e){
            handlerExceptionResolver.resolveException(request,response,null,e);
        }
    }
}
