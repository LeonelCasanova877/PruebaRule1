package com.leonel.allstreaming.repository;

import com.leonel.allstreaming.model.Account;
import com.leonel.allstreaming.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByAccountType(AccountType accountType);

    Optional<Account> findByEmail(String email);
}