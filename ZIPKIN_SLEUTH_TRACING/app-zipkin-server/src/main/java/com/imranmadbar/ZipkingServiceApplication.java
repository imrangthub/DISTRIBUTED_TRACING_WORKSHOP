package com.imranmadbar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;


@EnableZipkinServer
@SpringBootApplication
public class ZipkingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkingServiceApplication.class, args);
    }

}
