package com.example.wesupportinc.model.dto;

import com.example.wesupportinc.model.entity.AccountEntity.AccountType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
     private Long customerNumber;
     private String customerName;
     private String customerMobile;
     private String customerEmail;
     private String address1;
     private String address2;
     private AccountType accountType;
}
