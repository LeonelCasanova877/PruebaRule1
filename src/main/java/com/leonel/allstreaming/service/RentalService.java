package com.leonel.allstreaming.service;

import com.leonel.allstreaming.dto.RentalDTO;
import com.leonel.allstreaming.model.Rental;

import java.util.List;

public interface RentalService {

    Long createRental(Rental rental);

    List<RentalDTO> getAllRentalsByAccountId(Long id);

    List<RentalDTO> getAllRentals();

    Long deleteRental(Long id);

}
