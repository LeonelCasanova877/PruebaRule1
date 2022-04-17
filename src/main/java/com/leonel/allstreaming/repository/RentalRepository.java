package com.leonel.allstreaming.repository;

import com.leonel.allstreaming.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findAllByAccount_Id (@Param("account_id") Long id);
}