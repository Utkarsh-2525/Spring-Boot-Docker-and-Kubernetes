package com.utkarsh2573.accounts.dto;

/**
 *
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */
public record accountsMessageDto(Long accountNumber, String name, String email, String mobileNumber) {
}
