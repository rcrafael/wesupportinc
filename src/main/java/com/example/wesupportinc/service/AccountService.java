package com.example.wesupportinc.service;

import com.example.wesupportinc.model.dto.AccountDto;

public interface AccountService {

     public Long createAccount(AccountDto accountDto);

     public AccountDto getAccountById(Long customerNumber);
}
