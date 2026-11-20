package com.example.wesupportinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wesupportinc.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
