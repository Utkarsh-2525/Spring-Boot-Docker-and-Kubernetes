package com.utkarsh2573.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty!")
    @Size(min = 5, max = 30, message = "The length of Name should be between 5 and 30 (inclusive)!")
    private String name;

    @NotEmpty(message = "Email Address cannot be null or empty!")
    @Email(message = "Email address should be a valid value!")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be of 10 digits!")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
