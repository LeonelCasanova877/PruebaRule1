package com.leonel.allstreaming.service.implementation;

import com.leonel.allstreaming.dto.ErrorDTO;
import com.leonel.allstreaming.dto.RentalDTO;
import com.leonel.allstreaming.exception.AllStreamingException;
import com.leonel.allstreaming.model.Account;
import com.leonel.allstreaming.model.AccountState;
import com.leonel.allstreaming.model.Rental;
import com.leonel.allstreaming.repository.RentalRepository;
import com.leonel.allstreaming.service.AccountService;
import com.leonel.allstreaming.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImplementation implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Long createRental(Rental rental) {

        if (rental.getAccount().getId() == null) {
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Account id cannot be null"
            );
            throw new AllStreamingException(error, HttpStatus.BAD_REQUEST);
        }

        Duration duration= Duration.between(rental.getStartDate(), rental.getEndDate());
        if(duration.isNegative() || duration.isZero()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "The start date should be earlier than the end date "
            );
            throw new AllStreamingException(error, HttpStatus.BAD_REQUEST);
        }
        Long hours = duration.toHours();
        Account account = accountService.findById(rental.getAccount().getId());
        if(!account.getAccountState().equals(AccountState.AVAILABLE)){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "The account is unavailable or blocked"
            );
            throw new AllStreamingException(error, HttpStatus.BAD_REQUEST);
        }
        accountService.updateAccountState(AccountState.UNAVAILABLE.toString(), account.getId());
        Long price =  hours * account.getAccountType().getPricePerHour();
        rental.setPrice(price);
        return rentalRepository.save(rental).getId();
    }

    @Override
    public List<RentalDTO> getAllRentalsByAccountId(Long id) {

       List<Rental> rentals = rentalRepository.findAllByAccount_Id(id);
        return getRentalDTOS(rentals);
    }

    @Override
    public List<RentalDTO> getAllRentals() {

        List<Rental> rentals = rentalRepository.findAll();
        return getRentalDTOS(rentals);
    }

    @Override
    public Long deleteRental(Long id) {

        if(rentalRepository.findById(id).isEmpty()){
            ErrorDTO error = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    "Rental with id "+id+" not found"
            );
            throw  new AllStreamingException(error, HttpStatus.NOT_FOUND);
        }

        rentalRepository.deleteById(id);
        return id;
    }

    private List<RentalDTO> getRentalDTOS(List<Rental> rentals) {

        List<RentalDTO> rentalDTOS = new ArrayList<>();
        rentals.forEach(rental -> {
            Duration duration = Duration.between(rental.getStartDate(), rental.getEndDate());
            Long hours = duration.toHours();
            rentalDTOS.add(new RentalDTO(
                    rental.getPrice(),
                    rental.getStartDate(),
                    rental.getEndDate(),
                    rental.getUserEmail(),
                    hours
                    )
            );
        });
        return rentalDTOS;
    }
}
