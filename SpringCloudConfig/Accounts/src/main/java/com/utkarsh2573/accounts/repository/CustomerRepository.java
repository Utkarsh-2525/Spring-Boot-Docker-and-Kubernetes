package com.utkarsh2573.accounts.repository;

import com.utkarsh2573.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
    multiple queries can be run using And in between and passing parameter separated by commas
    where findBy acts as Select Query
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
