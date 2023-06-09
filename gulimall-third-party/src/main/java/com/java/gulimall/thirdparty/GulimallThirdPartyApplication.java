package com.java.gulimall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//注册向nacos
@EnableDiscoveryClient
@SpringBootApplication
public class  GulimallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallThirdPartyApplication.class, args);
    }

}
