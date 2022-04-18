package com.leonel.allstreaming.controller;


import com.leonel.allstreaming.dto.RentalDTO;
import com.leonel.allstreaming.model.Rental;
import com.leonel.allstreaming.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping(consumes={MediaType.APPLICATION_JSON_VALUE})
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRental(@PathVariable Long id){

        return new ResponseEntity<>("Rental with the id: "+rentalService.deleteRental(id)+" deleted", HttpStatus.OK);
    }

}
