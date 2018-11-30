package com.cc.dapp.manager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
//@EnableScheduling
//@EnableCaching
public class ManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApiApplication.class, args);
    }
}
