package com.leonel.pruebarule1.repository;

import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByAccountType(AccountType accountType);
}