package com.kang.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @author kang
 * @description TODO
 * @date 2023/3/23 23:03
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级安全验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义用户认证逻辑
     */
    @Resource
    private UserDetailServiceImpl userDetailService;

    /**
     * 认证失败处理类
     */
    @Resource
    private MyAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 授权失败处理类
     */
    @Resource
    private MyAccessDeniedHandler accessDeniedHandler;

    @Resource
    private ExceptionHandlerFilter exceptionHandlerFilter;

    /**
     * token认证过滤器
     */
    @Bean
    MyJwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        MyJwtAuthenticationFilter jwtAuthenticationFilter = new MyJwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    private static final String[] URL_WHITELIST = {
            "/login", "/imgCode", "/emailCode", "/admin/login", "/resource/**", "/category/list/**",
            "/video/list/**", "/article/list/**", "/question/list", "/resource/list",
            "/comment/list", "/common/**"

    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //禁用session

                // 配置拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .antMatchers("/admin/**").hasAnyRole("admin","super")
                .antMatchers("/super/**").hasRole("super")
                .anyRequest().authenticated()

                // 异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)

                // 配置自定义的过滤器
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                //.addFilterBefore(exceptionHandlerFilter, LogoutFilter.class);   // 把异常处理的filter放到其他filter前面来全局异常处理
    }


    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
