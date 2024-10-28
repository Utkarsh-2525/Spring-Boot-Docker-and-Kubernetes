package com.utkarsh2573.accounts.service.Impl;

import com.utkarsh2573.accounts.constants.AccountsConstants;
import com.utkarsh2573.accounts.dto.AccountsDto;
import com.utkarsh2573.accounts.dto.CustomerDto;
import com.utkarsh2573.accounts.entity.Accounts;
import com.utkarsh2573.accounts.entity.Customer;
import com.utkarsh2573.accounts.exception.CustomerAlreadyExistsException;
import com.utkarsh2573.accounts.exception.ResourceNotFoundException;
import com.utkarsh2573.accounts.mapper.AccountsMapper;
import com.utkarsh2573.accounts.mapper.CustomerMapper;
import com.utkarsh2573.accounts.repository.AccountsRepository;
import com.utkarsh2573.accounts.repository.CustomerRepository;
import com.utkarsh2573.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent())
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number " + customerDto.getMobileNumber());

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("M.Utkarsh");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("M.Utkarsh");
        return newAccount;
    }

    /**
     * @param mobileNumber
     * @return account number based on a given number
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }
}
