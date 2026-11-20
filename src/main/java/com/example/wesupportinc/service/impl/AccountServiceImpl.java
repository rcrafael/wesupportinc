package com.example.wesupportinc.service.impl;

import org.apache.commons.validator.routines.EmailValidator;
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

     // if got time, I will remove the manual mapping of Entity and DTO and use
     // mapper library like MapStruct

     @Override
     public Long createAccount(AccountDto accountDto) {

          AccountEntity accountEntity = new AccountEntity();

          if (EmailValidator.getInstance().isValid(accountDto.getCustomerEmail()) == false) {
               throw new IllegalArgumentException("Invalid email address");
          }

          accountEntity.setCustomerName(accountDto.getCustomerName());
          accountEntity.setCustomerEmail(accountDto.getCustomerEmail());
          accountEntity.setCustomerMobile(accountDto.getCustomerMobile());
          accountEntity.setAddress1(accountDto.getAddress1());
          accountEntity.setAddress2(accountDto.getAddress2());
          accountEntity.setAccountType(accountDto.getAccountType());

          AccountEntity savedAccountEntity = accountRepository.save(accountEntity);

          return savedAccountEntity.getCustomerNumber(); // Return the generated customer number
     }

     @Override
     public AccountDto getAccountById(Long customerNumber) {
          AccountEntity accountEntity = accountRepository.findById(customerNumber)
                    .orElseThrow(
                              () -> new RuntimeException("Customer Account found."));

          AccountDto accountDto = new AccountDto();
          accountDto.setCustomerNumber(accountEntity.getCustomerNumber());
          accountDto.setCustomerName(accountEntity.getCustomerName());
          accountDto.setCustomerEmail(accountEntity.getCustomerEmail());
          accountDto.setCustomerMobile(accountEntity.getCustomerMobile());
          accountDto.setAddress1(accountEntity.getAddress1());
          accountDto.setAddress2(accountEntity.getAddress2());
          accountDto.setAccountType(accountEntity.getAccountType());

          return accountDto;
     }

}
