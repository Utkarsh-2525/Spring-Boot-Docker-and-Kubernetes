package com.utkarsh2573.accounts.service;

import com.utkarsh2573.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return account number based on a given number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return update status
     */
    boolean updateAccount(CustomerDto customerDto);
}
