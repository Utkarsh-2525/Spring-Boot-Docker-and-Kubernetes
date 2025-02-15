package com.utkarsh2573.accounts.service;

import com.utkarsh2573.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return account details based on given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return account update status
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return account delete status
     */
    boolean deleteAccount(String mobileNumber);

    /**
     *
     * @param accountNumber
     * @return boolean returning if the update of communication status is successful or not
     */
    boolean updateCommunicationStatus(Long accountNumber);
}
