package com.imranmadbar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceTwoApplication.class, args);
    }

}
