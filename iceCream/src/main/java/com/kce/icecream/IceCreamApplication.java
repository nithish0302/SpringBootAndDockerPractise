package com.kce.icecream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.kce.icecream.feignclient")
public class IceCreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IceCreamApplication.class, args);
    }

}
