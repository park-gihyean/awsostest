package com.example.jisoo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.example.jisoo.mapper")
public class MyBatisConfig {
    
}