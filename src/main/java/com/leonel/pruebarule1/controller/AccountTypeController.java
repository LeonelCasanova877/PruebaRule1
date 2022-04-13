package com.leonel.pruebarule1.controller;


import com.leonel.pruebarule1.model.AccountType;
import com.leonel.pruebarule1.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @PostMapping("accounts/type")
    public ResponseEntity<String> createAccountType(@RequestBody AccountType accountType){
        return new ResponseEntity<>("Account type " + accountTypeService
                .createAccountType(accountType), HttpStatus.CREATED);
    }
}
