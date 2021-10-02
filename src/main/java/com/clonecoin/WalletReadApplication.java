package com.clonecoin;

import com.clonecoin.walletread.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableEurekaClient
public class WalletReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletReadApplication.class, args);
        System.out.println("\n\n 작동 시작\n\n");
    }


}
