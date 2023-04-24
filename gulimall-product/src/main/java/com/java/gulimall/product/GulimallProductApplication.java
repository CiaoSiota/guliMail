package com.java.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/*
 * 逻辑删除
 *  1)、配置全局的逻辑删除规则
 *  2)、配置逻辑删除的组件（mybatis plus 3.1.1后前两步均可省略）
 *  3)、给bean加上逻辑删除注解@TableLogic
 */
@MapperScan("com.java.gulimall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
