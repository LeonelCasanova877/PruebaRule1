package com.leonel.pruebarule1.service;

import com.leonel.pruebarule1.model.AccountType;

import java.util.List;

public interface AccountTypeService {

    Long createAccountType(AccountType accountType);

    List<AccountType> findAllAccountTypes();
}
