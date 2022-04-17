package com.leonel.pruebarule1.service;

import com.leonel.pruebarule1.dto.RentalDTO;
import com.leonel.pruebarule1.model.Rental;

import java.util.List;

public interface RentalService {

    Long createRental(Rental rental);

    List<RentalDTO> getAllRentalsByAccountId(Long id);

    List<RentalDTO> getAllRentals();

}
