package com.ustc.oep;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement //开启注解
@EnableCaching//开启注解缓存
@EnableWebMvc
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        log.info("项目启动成功");
    }
}
