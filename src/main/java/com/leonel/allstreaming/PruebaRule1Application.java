package com.leonel.allstreaming;

import com.leonel.allstreaming.dto.RentalDTO;
import com.leonel.allstreaming.model.Account;
import com.leonel.allstreaming.model.AccountState;
import com.leonel.allstreaming.model.Rental;
import com.leonel.allstreaming.repository.RentalRepository;
import com.leonel.allstreaming.service.AccountService;
import com.leonel.allstreaming.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class PruebaRule1Application {

    @Autowired
    AccountService accountService;

    @Scheduled(initialDelay = 1000L, fixedDelayString = "PT1M" )
    public void scheduleFixedDelayTask() {

        List<Account> accounts= accountService.findAllAccounts();
        accounts = accounts.stream().filter(Account -> Account.getAccountState().equals(AccountState.UNAVAILABLE)).collect(Collectors.toList());
        accounts.forEach(account ->{
            List<Rental> rentals = account.getRentals().stream().sorted(Comparator.comparingLong(Rental::getId)).collect(Collectors.toList());
            Rental rental = rentals.get(rentals.size()-1);
            Duration duration = Duration.between(LocalDateTime.now(), rental.getEndDate());
             if(duration.isNegative() || duration.isZero()){
                 System.out.println("Account id updated: "+ account.getId());
                 accountService.updateAccountState(AccountState.AVAILABLE.toString(), account.getId());
             }
         });
    }

    public static void main(String[] args) {
        SpringApplication.run(PruebaRule1Application.class, args);
    }

}
