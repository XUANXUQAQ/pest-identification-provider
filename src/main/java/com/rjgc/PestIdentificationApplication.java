package com.rjgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.rjgc.mapper")
@EnableTransactionManagement
public class PestIdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PestIdentificationApplication.class, args);
    }

}
