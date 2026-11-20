package com.example.wesupportinc.model.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.example.wesupportinc.model.entity.AccountEntity.AccountType;

@Converter
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {

     @Override
     public String convertToDatabaseColumn(AccountType type) {
          return type == null ? null : type.getCode();
     }

     @Override
     public AccountType convertToEntityAttribute(String code) {
          return AccountType.fromCode(code);
     }
}