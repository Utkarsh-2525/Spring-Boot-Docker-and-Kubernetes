package com.utkarsh2573.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account Information"
)
public class AccountsDto {

    @NotEmpty(message = "Account Number cannot be null or empty!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be of 10 digits")
    @Schema(
            description = "Account Number of customer in demo account"
    )
    private Long accountNumber;

    @Schema(
            description = "Account Type of customer in demo account",
            example = "SAVINGS"
    )
    @NotEmpty(message = "Account Type cannot be null or empty!")
    private String accountType;

    @Schema(
            description = "Branch Address of the customer in demo account",
            example = "123, New Jersey, Ohio"
    )
    @NotEmpty(message = "Branch Address cannot be null or empty!")
    private String branchAddress;
}
