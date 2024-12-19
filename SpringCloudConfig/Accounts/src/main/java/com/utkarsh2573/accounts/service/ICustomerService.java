package com.utkarsh2573.accounts.service;

import com.utkarsh2573.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on provided mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
