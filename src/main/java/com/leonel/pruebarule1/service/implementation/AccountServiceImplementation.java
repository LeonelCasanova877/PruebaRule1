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

        if (account.getAccountType().getId() == null) {
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Account type id cannot be null"
            );
            throw new Rule1Exception(error, HttpStatus.BAD_REQUEST);
        }

        if(accountRepository.findByEmail(account.getEmail()).isPresent()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "There is already an account associated with this email"
            );
            throw new Rule1Exception(error, HttpStatus.BAD_REQUEST);
        }

        if(accountTypeRepository.findById(account.getAccountType().getId()).isEmpty()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Account type with id "+account.getAccountType().getId()+" not found"
            );
            throw  new Rule1Exception(error, HttpStatus.NOT_FOUND);
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

    @Override
    public Long editAccount(Account accountFromRequest, Long id) {

        accountFromRequest.setEmail(accountFromRequest.getEmail().toLowerCase());
        Account accountFromDB = findById(id);

        if (accountFromRequest.getAccountType().getId() == null) {
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Account type id cannot be null"
            );
            throw new Rule1Exception(error, HttpStatus.BAD_REQUEST);
        }

        if(!accountFromDB.getEmail().equals(accountFromRequest.getEmail())){
            if(accountRepository.findByEmail(accountFromRequest.getEmail()).isPresent()){
                ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "There is already an account associated with this email"
                );
                throw new Rule1Exception(error, HttpStatus.BAD_REQUEST);
            }
        }

        accountFromDB.setAccountType(accountFromRequest.getAccountType());
        accountFromDB.setEmail(accountFromRequest.getEmail());
        accountFromDB.setDescription(accountFromRequest.getDescription());
        accountFromDB.setName(accountFromRequest.getName());
        accountFromDB.setPassword(accountFromRequest.getPassword());

        if(accountTypeRepository.findById(accountFromDB.getAccountType().getId()).isEmpty()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Account type with id "+accountFromDB.getAccountType().getId()+" not found"
            );
            throw  new Rule1Exception(error, HttpStatus.NOT_FOUND);
        }
        return accountRepository.save(accountFromDB).getId();
    }

    @Override
    public Long deleteAccount(Long id) {
        if(accountRepository.findById(id).isEmpty()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Account type with id "+id+" not found"
            );
            throw  new Rule1Exception(error, HttpStatus.NOT_FOUND);
        }

        accountRepository.deleteById(id);
        return id;
    }
}
