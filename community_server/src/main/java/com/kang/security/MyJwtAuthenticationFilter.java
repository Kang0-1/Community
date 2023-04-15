package com.kang.security;

import cn.hutool.core.util.StrUtil;
import com.kang.entity.User;
import com.kang.service.UserService;
import com.kang.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kang
 * @description token认证过滤器
 * @date 2023/3/24 17:34
 */
public class MyJwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private JwtUtil JwtUtil;

    @Resource
    private UserService userService;

    public MyJwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(JwtUtil.getHeader());
        if(StrUtil.isBlankOrUndefined(jwt)){
            chain.doFilter(request,response);
            return;
        }
        Claims claim = JwtUtil.getClaimByToken(jwt);
        if( null == claim ){
            throw new JwtException("token 异常");
        }
        if(JwtUtil.isTokenExpired(claim)){
            throw new JwtException("token已过期");
        }

        String userId = claim.getSubject();
        User user = new User();
        user.setUserId(Long.valueOf(userId));
        user=userService.selectUserByParam(user);
        String username=user.getUserName();
        String userRole = user.getUserRole();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(userRole));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }
}
