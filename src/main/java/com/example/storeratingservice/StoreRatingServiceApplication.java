package com.example.storeratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StoreRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreRatingServiceApplication.class, args);
    }

}
