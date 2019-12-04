package com.hzgg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/3 10:35
 */
@SpringBootApplication
@MapperScan("com.hzgg.test.mapper")
public class ShiroSpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShiroSpringApplication.class, args);
    }
}
