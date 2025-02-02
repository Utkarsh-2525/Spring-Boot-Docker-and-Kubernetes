package com.utkarsh2573.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            name = "Name of the customer",
            example = "Utkarsh Mishra"
    )
    @NotEmpty(message = "Name cannot be null or empty!")
    @Size(min = 5, max = 30, message = "The length of Name should be between 5 and 30 (inclusive)!")
    private String name;

    @Schema(
            name = "Email of the customer",
            example = "utkarsh2573@gmail.com"
    )
    @NotEmpty(message = "Email Address cannot be null or empty!")
    @Email(message = "Email address should be a valid value!")
    private String email;

    @Schema(
            name = "Phone Number of the customer",
            example = "9012346578"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be of 10 digits!")
    private String mobileNumber;

    private AccountsDto accountsDto;
}

