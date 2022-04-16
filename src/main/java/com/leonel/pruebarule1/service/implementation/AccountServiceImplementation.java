package com.leonel.pruebarule1.service.implementation;

import com.leonel.pruebarule1.dto.ErrorDTO;
import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.model.AccountState;
import com.leonel.pruebarule1.model.AccountType;
import com.leonel.pruebarule1.repository.AccountRepository;
import com.leonel.pruebarule1.repository.AccountTypeRepository;
import com.leonel.pruebarule1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Account findById(Long id) throws Rule1Exception {

        return accountRepository.findById(id)
                .orElseThrow(()-> {
                    ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                            HttpStatus.NOT_FOUND.value(),
                            "Account with id "+id+" not found"
                    );
                    return new Rule1Exception(error, HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public Long createAccount(Account account) {

        account.setEmail(account.getEmail().toLowerCase());
        if(accountRepository.findByEmail(account.getEmail()).isPresent()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "There is already an account associated with this email"
            );
            throw new Rule1Exception(error, HttpStatus.BAD_REQUEST);
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<Account> findAccountsByType(String accountTypeName) {

        AccountType filterType = accountTypeRepository.findByNameIgnoreCase(accountTypeName)
                .orElseThrow(()-> {
                    ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                            HttpStatus.NOT_FOUND.value(),
                            "account type not found"
                    );
                    return new Rule1Exception(error, HttpStatus.NOT_FOUND);
                });
        return accountRepository.findAllByAccountType(filterType).stream()
                .filter(account -> account.getAccountState().equals(AccountState.AVAILABLE)).collect(Collectors.toList());
    }

    @Override
    public Long updateAccountState(String accountStateName, Long id)  {

        if(!Arrays.stream(AccountState.values()).anyMatch(e -> e.name().equals(accountStateName.toUpperCase()))){
            ErrorDTO error = new ErrorDTO(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Account state not valid"
            );
            throw  new Rule1Exception(error, HttpStatus.BAD_REQUEST);
        }
        Account accountToChange = findById(id);
        AccountState newAccountState = AccountState.valueOf(accountStateName.toUpperCase());
        accountToChange.setAccountState(newAccountState);
        return accountRepository.save(accountToChange).getId();
    }

    @Override
    public List<Account> findAllAccounts() {

        return accountRepository.findAll();
    }
}
