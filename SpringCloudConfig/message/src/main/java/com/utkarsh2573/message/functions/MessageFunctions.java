package com.utkarsh2573.message.functions;

import com.utkarsh2573.message.dto.accountsMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<accountsMessageDto, accountsMessageDto> email() {
        return accountsMessageDto -> {
            logger.info("Sending email with details: " + accountsMessageDto.toString());
            return accountsMessageDto;
        };
    }

    @Bean
    public Function<accountsMessageDto, Long> sms() {
        return accountsMessageDto -> {
            logger.info("Sending sms with details: {}", accountsMessageDto.toString());
            return accountsMessageDto.accountNumber();
        };
    }
}
