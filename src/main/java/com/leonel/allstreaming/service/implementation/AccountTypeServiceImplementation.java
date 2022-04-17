package com.leonel.allstreaming.service.implementation;

import com.leonel.allstreaming.dto.ErrorDTO;
import com.leonel.allstreaming.exception.AllStreamingException;
import com.leonel.allstreaming.model.AccountType;
import com.leonel.allstreaming.repository.AccountTypeRepository;
import com.leonel.allstreaming.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountTypeServiceImplementation implements AccountTypeService {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Long createAccountType(AccountType accountType) throws AllStreamingException {

        if(accountTypeRepository.findByNameIgnoreCase(accountType.getName()).isPresent()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Account type already exist"
            );
            throw new AllStreamingException(error, HttpStatus.BAD_REQUEST);
        }
        return accountTypeRepository.save(accountType).getId();
    }

    @Override
    public List<AccountType> findAllAccountTypes() {

        return accountTypeRepository.findAll();
    }

    @Override
    public Long editAccountType(AccountType accountTypeFromRequest, Long id) {

        AccountType accountTypeFromDB = accountTypeRepository.findById(id).orElseThrow(()->{
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Account type not found"
            );
            throw new AllStreamingException(error, HttpStatus.NOT_FOUND);
        });

        if(!accountTypeFromDB.getName().equalsIgnoreCase(accountTypeFromRequest.getName())){
            if(accountTypeRepository.findByNameIgnoreCase(accountTypeFromRequest.getName()).isPresent()){
                ErrorDTO error = new ErrorDTO(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Account type already exist"
                );
                throw new AllStreamingException(error, HttpStatus.BAD_REQUEST);
            }
        }

        accountTypeFromDB.setName(accountTypeFromRequest.getName());
        accountTypeFromDB.setPricePerHour(accountTypeFromRequest.getPricePerHour());

        return accountTypeRepository.save(accountTypeFromDB).getId();
    }

    @Override
    public Long deleteAccountType(Long id) {

        if(accountTypeRepository.findById(id).isEmpty()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Account type with id "+id+" not found"
            );
            throw  new AllStreamingException(error, HttpStatus.NOT_FOUND);
        }

        accountTypeRepository.deleteById(id);
        return id;
    }
}
