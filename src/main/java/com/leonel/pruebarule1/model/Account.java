package com.leonel.pruebarule1.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String description;

    @Column(unique=true)
    private String email;
    private AccountState accountState;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<AccountType> accountTypes;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountType> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<AccountType> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }
}
