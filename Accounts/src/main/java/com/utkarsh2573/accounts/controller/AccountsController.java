package com.utkarsh2573.accounts.controller;

import com.utkarsh2573.accounts.constants.AccountsConstants;
import com.utkarsh2573.accounts.dto.CustomerDto;
import com.utkarsh2573.accounts.dto.ErrorResponseDto;
import com.utkarsh2573.accounts.dto.ResponseDto;
import com.utkarsh2573.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "CRUD REST APIs in demo bank to CREATE, DELETE, UPDATE AND FETCH account details"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// Versioning can be done as (path = "/api/v1")
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create account REST API",
            description = "Create a New Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch account REST API",
            description = "Fetch an Existing Account using Mobile Number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status FETCHED"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be of 10 digits")
                                                           String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update account REST API",
            description = "Update an Existing Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status UPDATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }

    @Operation(
            summary = "Update account REST API",
            description = "Delete an Existing Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status DELETED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be of 10 digits") String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    }
}
