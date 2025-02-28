package com.utkarsh2573.accounts.service.client;

import com.utkarsh2573.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", url = "http://loans:8090", fallback = LoansFallBack.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("DemoBank-CorrelationID")
                                              String correlationID,
                                              @RequestParam String mobileNumber);

}
