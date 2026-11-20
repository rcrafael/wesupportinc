package com.example.wesupportinc.model.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long customerNumber;

     @Column(name = "customerName", nullable = false, columnDefinition = "CHAR(50)")
     private String customerName;

     @Column(name = "customerMobile", nullable = false, columnDefinition = "CHAR(20)")
     private String customerMobile;

     @Column(name = "customerEmail", nullable = false, columnDefinition = "CHAR(50)", unique = true) // i want to make
                                                                                                     // this unique so
                                                                                                     // that I dont need
                                                                                                     // to check if the
                                                                                                     // email already
                                                                                                     // exists in the
                                                                                                     // database before
                                                                                                     // creating a new
                                                                                                     // account
     private String customerEmail;

     @Column(name = "address1", nullable = false, columnDefinition = "CHAR(100)")
     private String address1;

     @Column(name = "address2", columnDefinition = "CHAR(100)")
     private String address2;

     @Convert(converter = AccountTypeConverter.class)
     @Column(name = "accountType", nullable = false, length = 1)
     private AccountType accountType;

     public enum AccountType {
          SAVINGS("S"),
          CURRENT("C");

          private final String code;

          AccountType(String code) {
               this.code = code;
          }

          @JsonValue
          public String getCode() {
               return code;
          }

          @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
          public static AccountType fromCode(String code) {
               if (code == null) {
                    return null;
               }
               for (AccountType t : values()) {
                    if (t.code.equals(code)) {
                         return t;
                    }
               }
               throw new IllegalArgumentException("Unknown account type code: " + code);
          }
     }

}
