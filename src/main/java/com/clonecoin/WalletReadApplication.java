package com.clonecoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class WalletReadApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletReadApplication.class, args);
        System.out.println("\n\n 작동 시작\n\n");
    }
}
