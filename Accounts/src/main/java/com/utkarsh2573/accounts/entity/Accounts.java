package com.utkarsh2573.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity {
    @Column(name = "customer_id") // No need to mention if column name == field name
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "branch_address")
    private String branchAddress;
}
