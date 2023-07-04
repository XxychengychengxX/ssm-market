package com.project.ssmproject2;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.project.ssmproject2.mapper")
@Slf4j
@EnableAspectJAutoProxy
@EnableTransactionManagement
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/","http://192.168.0" +
        ".107:5173/","http://192.168.0.107:5173"}, allowCredentials =
        "true",allowedHeaders = {"Authorization","authorization"})
public class SsmProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(SsmProject2Application.class, args);
    }

}
