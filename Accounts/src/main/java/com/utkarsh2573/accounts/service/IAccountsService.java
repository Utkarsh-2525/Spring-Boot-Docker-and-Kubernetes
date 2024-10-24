package com.utkarsh2573.accounts.service;

import com.utkarsh2573.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto object
     */
    void createAccount(CustomerDto customerDto);
}
