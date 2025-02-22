package com.utkarsh2573.accounts.controller;

import com.utkarsh2573.accounts.dto.CustomerDetailsDto;
import com.utkarsh2573.accounts.dto.ErrorResponseDto;
import com.utkarsh2573.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Customers in DemoBank",
        description = "REST APIs in DemoBank to Fetch Customer Details"
)
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
// Versioning can be done as (path = "/api/v1")
@Validated
@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerService IcustomerService;

    public CustomerController(ICustomerService customerService) {
        this.IcustomerService = customerService;
    }

    @Operation(
            summary = "Fetch Customer REST API",
            description = "Fetch Customer Details using Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status FETCHED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("DemoBank-CorrelationID")
                                                                   String correlationID,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be of 10 digits")
                                                                   String mobileNumber) {
        logger.debug("DemoBank-CorrelationID: " + correlationID);
        CustomerDetailsDto customerDetailsDto = IcustomerService.fetchCustomerDetails(mobileNumber, correlationID);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
