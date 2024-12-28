package com.utkarsh2573.accounts.service.client;

import com.utkarsh2573.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoansFeignClient{
    /**
     * @param correlationID
     * @param mobileNumber
     * @return
     */
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationID, String mobileNumber) {
        return null;
    }
}
