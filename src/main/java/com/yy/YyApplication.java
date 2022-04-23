package com.yy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bs
 * @create 2022-04-20
 */
@SpringBootApplication
@MapperScan("com.yy.mapper")
public class YyApplication {
    public static void main(String[] args) {
        SpringApplication.run(YyApplication.class, args);
    }
}
