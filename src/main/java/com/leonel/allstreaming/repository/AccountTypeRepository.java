package com.leonel.allstreaming.repository;

import com.leonel.allstreaming.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Query("SELECT a FROM AccountType a WHERE UPPER(a.name) = UPPER(?1)")
    Optional<AccountType>  findByNameIgnoreCase (String name);
}