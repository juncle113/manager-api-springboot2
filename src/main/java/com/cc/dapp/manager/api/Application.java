package com.cc.dapp.manager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 程序启动入口
 *
 * 开启接口文档Swagger2
 * 开启缓存SpringCache（ehcache实现）
 * 开启事务管理
 * 待开启EnableScheduling
 * 待开启EnableWebSecurity
 *
 * @author sunli
 * @date 2018/12/07
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
