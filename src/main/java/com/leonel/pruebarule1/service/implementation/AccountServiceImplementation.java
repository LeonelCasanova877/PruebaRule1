package com.leonel.pruebarule1.service.implementation;

import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.model.AccountType;
import com.leonel.pruebarule1.repository.AccountRepository;
import com.leonel.pruebarule1.repository.AccountTypeRepository;
import com.leonel.pruebarule1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Account findById(Long id) throws Rule1Exception {

        return accountRepository.findById(id)
                .orElseThrow(()-> new Rule1Exception("Account with id "+id+" not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Long createAccount(Account account) {

        account.setEmail(account.getEmail().toLowerCase());
        return accountRepository.save(account).getId();
    }

    @Override
    public List<Account> findAccountsByType(AccountType accountType) {

        AccountType filterType = accountTypeRepository.findByNameIgnoreCase(accountType.getName())
                .orElseThrow(()-> new Rule1Exception("account type not found", HttpStatus.NOT_FOUND));
        return accountRepository.findAllByAccountType(filterType);
    }
}
