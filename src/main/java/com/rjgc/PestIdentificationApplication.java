package com.rjgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.rjgc")
public class PestIdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PestIdentificationApplication.class, args);
    }

}
