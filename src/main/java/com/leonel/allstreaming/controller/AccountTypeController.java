package com.leonel.allstreaming.controller;


import com.leonel.allstreaming.model.AccountType;
import com.leonel.allstreaming.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "accounts/types")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @PostMapping(consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createAccountType(@RequestBody @Valid AccountType accountType){

        return new ResponseEntity<>("Account type " + accountTypeService
                .createAccountType(accountType), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {

        return new ResponseEntity<>(accountTypeService.findAllAccountTypes(), HttpStatus.OK);
    }

    @PutMapping(name="/{id}", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateAccountType(@RequestBody @Valid AccountType accountType, @PathVariable Long id) {

        return new ResponseEntity<>("Account type with id "+accountTypeService.editAccountType(accountType, id)+ " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountType(@PathVariable Long id){

        return new ResponseEntity<>("AccountType with the id "+accountTypeService.deleteAccountType(id)+" deleted", HttpStatus.OK);
    }
}
