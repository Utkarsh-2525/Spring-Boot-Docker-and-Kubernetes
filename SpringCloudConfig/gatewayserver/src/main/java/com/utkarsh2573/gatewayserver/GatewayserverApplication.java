package com.utkarsh2573.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(customPath -> customPath
                        .path("/demobank/accounts/**")
                                .filters(filter -> filter
                                        .rewritePath("/demobank/accounts/(?<segment>.*)","/${segment}"))
                                .uri("lb://ACCOUNTS"))
                .route(customPath -> customPath
                        .path("/demobank/loans/**")
                        .filters(filter -> filter
                                .rewritePath("/demobank/accounts/(?<segment>.*)","/${segment}"))
                        .uri("lb://LOANS"))
                .route(customPath -> customPath
                        .path("/demobank/cards/**")
                        .filters(filter -> filter
                                .rewritePath("/demobank/accounts/(?<segment>.*)","/${segment}"))
                        .uri("lb://CARDS"))
                .build();
    }
}
