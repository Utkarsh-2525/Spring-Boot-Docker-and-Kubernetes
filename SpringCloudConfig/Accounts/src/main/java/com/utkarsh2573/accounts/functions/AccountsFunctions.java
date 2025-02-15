package com.utkarsh2573.accounts.functions;

import com.utkarsh2573.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService accountsService){
        return accountNumber -> {
            log.info("Updating communication status for the account number " + accountNumber);
            accountsService.updateCommunicationStatus(accountNumber);
        };
    }
}
