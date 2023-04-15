package com.kang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kang
 * @description 集合 本地静态资源映射处理器 和 跨域处理
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String filePath;

    /**
     * 通过CorsFilter解决跨域问题，防止添加拦截器后跨域失败
     */
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(source);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowCredentials(true)
//                //GET,HEAD,POST,PUT,DELETE,OPTIONS
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                //跨域允许最大时长
//                .maxAge(3600);
//    }

    /**
     * 配置本地资源映射路径
     * @param registry z
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**").addResourceLocations("file:"+filePath);
    }
}
