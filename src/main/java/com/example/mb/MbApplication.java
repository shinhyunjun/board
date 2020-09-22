package com.example.mb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mb.mapper")
public class MbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbApplication.class, args);
    }

}
