package com.leonel.pruebarule1.controller;


import com.leonel.pruebarule1.model.AccountType;
import com.leonel.pruebarule1.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "accounts/types", consumes={"application/json"})
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @PostMapping
    public ResponseEntity<String> createAccountType(@RequestBody @Valid AccountType accountType){

        return new ResponseEntity<>("Account type " + accountTypeService
                .createAccountType(accountType), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {

        return new ResponseEntity<>(accountTypeService.findAllAccountTypes(), HttpStatus.OK);
    }
}
