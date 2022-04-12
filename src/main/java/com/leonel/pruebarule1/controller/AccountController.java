package com.leonel.pruebarule1.controller;

import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @PostMapping("accounts")
    public ResponseEntity<String> createAccount(@RequestBody Account account){
        return new ResponseEntity<>("Account "+ accountService.createAccount(account)+" created", HttpStatus.CREATED);
    }
}
