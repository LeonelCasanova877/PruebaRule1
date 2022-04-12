package com.leonel.pruebarule1.service;

import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.Account;

public interface AccountService {

    Account findById(Long id) throws Rule1Exception;

    Long createAccount(Account account);

}
