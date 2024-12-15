package com.utkarsh2573.accounts;

import com.utkarsh2573.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API Documentation",
                description = "A demo bank Accounts Microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Utkarsh Mishra",
                        email = "utkarsh2573@gmail.com",
                        url = "https://github.com/Utkarsh-2525"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "A demo bank Accounts Microservice REST API Documentation",
                url = "https://springdoc.org/#modules"
        )
)
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
        
    }
}
