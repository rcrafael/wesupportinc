package com.example.wesupportinc.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
     private int transactionStatusCode;
     private String transactionStatusDescription;
     private Object data;
}
