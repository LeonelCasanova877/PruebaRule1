package com.leonel.allstreaming.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import com.leonel.allstreaming.model.Account;
import com.leonel.allstreaming.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {

        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes={MediaType.APPLICATION_JSON_VALUE})
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

    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateAccountState(@RequestParam("accountState") String accountStateName, @PathVariable Long id){

        return new ResponseEntity<>("Account "+ accountService.updateAccountState(accountStateName, id)+" state updated ", HttpStatus.OK);
    }

    @PutMapping(name= "/{id}",consumes={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> updateAccount(@RequestBody @Valid Account Account, @PathVariable Long id) {

        return new ResponseEntity<>("Account with id "+accountService.editAccount(Account, id)+ " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){

        return new ResponseEntity<>("Account with the id "+accountService.deleteAccount(id)+" deleted", HttpStatus.OK);
    }

}
