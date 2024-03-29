package com.raffler.mscreditrating;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@EnableRabbit
@SpringBootApplication
public class MsCreditRatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCreditRatingApplication.class, args);
    }

}
