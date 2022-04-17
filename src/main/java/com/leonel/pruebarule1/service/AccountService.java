package com.leonel.pruebarule1.service;

import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.model.AccountType;

import java.util.List;

public interface AccountService {

    Account findById(Long id) throws Rule1Exception;

    Long createAccount(Account account);

    List<Account> findAccountsByType(String accountTypeName);

    Long updateAccountState(String accountStateName, Long id);

    List<Account> findAllAccounts();

    Long editAccount(Account account, Long id);

    Long deleteAccount(Long id);

}
