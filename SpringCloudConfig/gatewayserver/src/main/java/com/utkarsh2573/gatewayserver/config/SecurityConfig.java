package com.utkarsh2573.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers("/demobank/accounts/**").hasRole("ACCOUNTS")
                .pathMatchers("/demobank/cards/**").hasRole("CARDS")
                .pathMatchers("/demobank/loans/**").hasRole("LOANS"))
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(Customizer.withDefaults()));
        http.csrf(csrfSpec -> csrfSpec.disable());
        return http.build();
    }
}
