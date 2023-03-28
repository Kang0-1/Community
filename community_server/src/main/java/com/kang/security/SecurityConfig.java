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
    public PasswordEncoder passwordEncoder(){
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
    private CorsFilter corsFilter;

    /**
     * token认证过滤器
     */
    @Bean
    MyJwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        MyJwtAuthenticationFilter jwtAuthenticationFilter=new MyJwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    public static final String[] URL_WhiteList={
            "/login","/imgCode", "/emailCode","/admin/login", "/resource/**","/category/list/**",
            "/video/list/**", "/article/list/**", "/question/list", "/resource/list",
            "/comment/list","/common/**"
    } ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //禁用session

                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()                         //配置Spring Security,设置不拦截OPTIONS请求
                .antMatchers(URL_WhiteList).permitAll()                                  //白名单可以访问
                .antMatchers("/admin/**").hasAnyRole("admin","super") //登录管理页面 要拥有的权限
                .anyRequest().authenticated()                                          //除上面外所以请求需要鉴权认证

                //异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)

                //配置自定义的过滤器
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter,jwtAuthenticationFilter().getClass());
    }


    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
