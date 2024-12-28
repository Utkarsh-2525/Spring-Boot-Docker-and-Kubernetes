package com.utkarsh2573.accounts.service.client;

import com.utkarsh2573.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient{
    /**
     * @param correlationID
     * @param mobileNumber
     * @return
     */
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationID, String mobileNumber) {
        return null;
    }
}
