package com.leonel.pruebarule1.service.implementation;

import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.repository.AccountRepository;
import com.leonel.pruebarule1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(Long id) throws Rule1Exception {
        return accountRepository.findById(id).orElseThrow(()-> new Rule1Exception("Account with id "+id+" not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Long createAccount(Account account) {
        return accountRepository.save(account).getId();
    }
}
