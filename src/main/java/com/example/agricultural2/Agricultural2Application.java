package com.example.agricultural2;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.agricultural2.mapper")
@SpringBootApplication
public class Agricultural2Application {

    public static void main(String[] args) {
        SpringApplication.run(Agricultural2Application.class, args);
    }

}
