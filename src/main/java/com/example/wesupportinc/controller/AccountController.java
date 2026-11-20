package com.example.wesupportinc.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wesupportinc.model.ApiResponse;
import com.example.wesupportinc.model.dto.AccountDto;
import com.example.wesupportinc.service.AccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

     private final AccountService accountService;

     public AccountController(AccountService accountService) {
          this.accountService = accountService;
     }

     // if got time, I will use ControllerAdvice to handle the exception and return a
     // proper response instead of handling it in the controller

     @PostMapping()
     public ResponseEntity<ApiResponse> createAccount(@RequestBody AccountDto accountDto) {
          ApiResponse response = new ApiResponse();

          try {
               Long customerNumber = accountService.createAccount(accountDto);
               response.setTransactionStatusDescription("Customer account created");
               response.setTransactionStatusCode(HttpStatus.CREATED.value());
               response.setData(customerNumber);
          } catch (DataIntegrityViolationException e) {
               response.setTransactionStatusDescription("Email already exists");
               response.setTransactionStatusCode(HttpStatus.CONFLICT.value());
          } catch (Exception e) {
               response.setTransactionStatusDescription(e.getMessage());
               response.setTransactionStatusCode(HttpStatus.BAD_REQUEST.value());
          }

          return ResponseEntity.ok(response);
     }

     @GetMapping("/{customerNumber}")
     public ResponseEntity<ApiResponse> getAccountById(@PathVariable Long customerNumber) {
          ApiResponse response = new ApiResponse();

          try {
               AccountDto accountDto = accountService.getAccountById(customerNumber);
               response.setTransactionStatusDescription("Customer account found");
               response.setTransactionStatusCode(HttpStatus.FOUND.value());
               response.setData(accountDto);
          } catch (Exception e) {
               response.setTransactionStatusDescription(e.getMessage());
               response.setTransactionStatusCode(HttpStatus.NOT_FOUND.value());
          }

          return ResponseEntity.ok(response);
     }
}
