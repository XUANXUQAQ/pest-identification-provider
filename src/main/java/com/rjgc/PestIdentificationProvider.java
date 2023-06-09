package com.rjgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.rjgc.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
public class PestIdentificationProvider {

    public static void main(String[] args) {
        SpringApplication.run(PestIdentificationProvider.class, args);
    }

}
