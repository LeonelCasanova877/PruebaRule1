package com.leonel.pruebarule1.service.implementation;

import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.AccountType;
import com.leonel.pruebarule1.repository.AccountTypeRepository;
import com.leonel.pruebarule1.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeServiceImplementation implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Long createAccountType(AccountType accountType) throws Rule1Exception {

        if(accountTypeRepository.findByNameIgnoreCase(accountType.getName()).isPresent()){
            throw new Rule1Exception("Account type already exist", HttpStatus.BAD_REQUEST);
        }
        return accountTypeRepository.save(accountType).getId();
    }
}
