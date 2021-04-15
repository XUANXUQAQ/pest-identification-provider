package com.rjgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rjgc.mapper")
public class PestIdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PestIdentificationApplication.class, args);
    }

}
