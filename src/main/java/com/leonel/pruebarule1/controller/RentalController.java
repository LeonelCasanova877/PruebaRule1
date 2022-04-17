package com.leonel.pruebarule1.controller;


import com.leonel.pruebarule1.dto.RentalDTO;
import com.leonel.pruebarule1.model.Rental;
import com.leonel.pruebarule1.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "rentals", consumes={"application/json"})
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public ResponseEntity<String> createRental(@RequestBody @Valid Rental rental){

        return new ResponseEntity<>("Rental " + rentalService.createRental(rental), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RentalDTO>> getAllRentalsByAccountId(@PathVariable Long id){

        return new ResponseEntity<>(rentalService.getAllRentalsByAccountId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RentalDTO>> getAllRentals(){

        return new ResponseEntity<>(rentalService.getAllRentals(), HttpStatus.OK);
    }

}
