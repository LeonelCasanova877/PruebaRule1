package com.leonel.allstreaming.service;

import com.leonel.allstreaming.model.AccountType;

import java.util.List;

public interface AccountTypeService {

    Long createAccountType(AccountType accountType);

    List<AccountType> findAllAccountTypes();

    Long editAccountType(AccountType accountType, Long id);

    Long deleteAccountType(Long id);
}
