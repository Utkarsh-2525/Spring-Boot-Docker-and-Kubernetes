package com.utkarsh2573.accounts.service.Impl;

import com.utkarsh2573.accounts.constants.AccountsConstants;
import com.utkarsh2573.accounts.dto.CustomerDto;
import com.utkarsh2573.accounts.entity.Accounts;
import com.utkarsh2573.accounts.entity.Customer;
import com.utkarsh2573.accounts.exception.CustomerAlreadyExists;
import com.utkarsh2573.accounts.mapper.CustomerMapper;
import com.utkarsh2573.accounts.repository.AccountsRepository;
import com.utkarsh2573.accounts.repository.CustomerRepository;
import com.utkarsh2573.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent())
            throw new CustomerAlreadyExists("Customer already exists with the given mobile number!"+ customerDto.getMobileNumber());

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomers = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomers));
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
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
