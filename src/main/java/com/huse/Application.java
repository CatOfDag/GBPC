package com.huse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.huse.mapper")
@ComponentScan("com.huse.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
