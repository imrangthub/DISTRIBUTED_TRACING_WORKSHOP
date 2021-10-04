package com.imranmadbar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceOneApplication.class, args);
    }

}
