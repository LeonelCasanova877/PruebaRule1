package com.leonel.allstreaming.service;

import com.leonel.allstreaming.exception.AllStreamingException;
import com.leonel.allstreaming.model.Account;

import java.util.List;

public interface AccountService {

    Account findById(Long id) throws AllStreamingException;

    Long createAccount(Account account);

    List<Account> findAccountsByType(String accountTypeName);

    Long updateAccountState(String accountStateName, Long id);

    List<Account> findAllAccounts();

    Long editAccount(Account account, Long id);

    Long deleteAccount(Long id);

}
