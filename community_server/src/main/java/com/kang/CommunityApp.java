package com.kang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 康
 */
@SpringBootApplication
@MapperScan(basePackages = "com.kang.mapper")
public class CommunityApp {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApp.class,args);
    }
}
