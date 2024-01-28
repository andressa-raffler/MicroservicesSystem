package com.raffler.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MsCloudGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(MsCloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(r -> r.path("/client/**").uri("lb://msclient")) //toda vez que eu chamar o /client/ ele vai redirecionar para o loadbalancer
                .route(r -> r.path("/cards/**").uri("lb://mscards"))
                .route(r -> r.path("/credit-rating/**").uri("lb://mscreditrating"))
                .build();
    }

}
