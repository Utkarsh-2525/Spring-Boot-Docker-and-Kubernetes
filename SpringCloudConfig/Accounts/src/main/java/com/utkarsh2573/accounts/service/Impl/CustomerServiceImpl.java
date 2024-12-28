package com.utkarsh2573.accounts.service.Impl;

import com.utkarsh2573.accounts.dto.AccountsDto;
import com.utkarsh2573.accounts.dto.CardsDto;
import com.utkarsh2573.accounts.dto.CustomerDetailsDto;
import com.utkarsh2573.accounts.dto.LoansDto;
import com.utkarsh2573.accounts.entity.Accounts;
import com.utkarsh2573.accounts.entity.Customer;
import com.utkarsh2573.accounts.exception.ResourceNotFoundException;
import com.utkarsh2573.accounts.mapper.AccountsMapper;
import com.utkarsh2573.accounts.mapper.CustomerMapper;
import com.utkarsh2573.accounts.repository.AccountsRepository;
import com.utkarsh2573.accounts.repository.CustomerRepository;
import com.utkarsh2573.accounts.service.ICustomerService;
import com.utkarsh2573.accounts.service.client.CardsFeignClient;
import com.utkarsh2573.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on provided mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer ID", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        // checking if due to any error the response returned from LoansDto is null
        if (null != loansDtoResponseEntity)
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());


        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        // checking if due to any error the response returned from CardsDto is null
        if (null != cardsDtoResponseEntity)
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
