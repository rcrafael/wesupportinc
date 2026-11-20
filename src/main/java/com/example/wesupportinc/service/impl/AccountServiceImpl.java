package com.example.wesupportinc.service.impl;

import org.springframework.stereotype.Service;

import com.example.wesupportinc.model.dto.AccountDto;
import com.example.wesupportinc.model.entity.AccountEntity;
import com.example.wesupportinc.repository.AccountRepository;
import com.example.wesupportinc.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

     private final AccountRepository accountRepository;

     public AccountServiceImpl(AccountRepository accountRepository) {
          this.accountRepository = accountRepository;
     }

     @Override
     public Long createAccount(AccountDto accountDto) {

          AccountEntity accountEntity = new AccountEntity();
          accountEntity.setCustomerName(accountDto.getCustomerName());
          accountEntity.setCustomerEmail(accountDto.getCustomerEmail());
          accountEntity.setCustomerMobile(accountDto.getCustomerMobile());
          accountEntity.setAddress1(accountDto.getAddress1());
          accountEntity.setAddress2(accountDto.getAddress2());
          accountEntity.setAccountType(accountDto.getAccountType());

          AccountEntity savedAccountEntity = accountRepository.save(accountEntity);

          return savedAccountEntity.getCustomerNumber(); // Return the generated customer number
     }

}
