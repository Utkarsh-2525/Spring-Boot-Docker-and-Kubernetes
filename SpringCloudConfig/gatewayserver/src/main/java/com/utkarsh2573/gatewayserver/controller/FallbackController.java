package com.utkarsh2573.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/contact-support")
    public Mono<String> contactSupport() {
        return Mono.just("An Error Occurred. Please try after sometime or contact support team!!");
    }
}
