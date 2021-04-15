package com.rjgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.rjgc.mapper")
@ServletComponentScan
public class PestIdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PestIdentificationApplication.class, args);
    }

}
