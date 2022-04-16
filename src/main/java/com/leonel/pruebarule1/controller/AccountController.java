package com.leonel.pruebarule1.controller;

import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "accounts",consumes={"application/json"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {

        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody @Valid Account account){

        return new ResponseEntity<>("Account "+ accountService.createAccount(account)+" created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccountsByType(@RequestParam("accountTypeName") String accountTypeName ) {

        return new ResponseEntity<>(accountService.findAccountsByType(accountTypeName), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {

        return new ResponseEntity<>(accountService.findAllAccounts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccountState(@RequestParam("accountState") String accountStateName, @PathVariable Long id){

        return new ResponseEntity<>("Account "+ accountService.updateAccountState(accountStateName, id)+" state updated ", HttpStatus.OK);
    }

}
