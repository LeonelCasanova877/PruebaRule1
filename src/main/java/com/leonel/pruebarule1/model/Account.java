package com.leonel.pruebarule1.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "name cannot be blank or null")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "password cannot be blank or null")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "Email cannot be null")
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email format is not valid")
    @Column(unique=true,nullable = false)
    private String email;

    @Column(nullable = false)
    private AccountState accountState = AccountState.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "accountTypeId")
    @NotNull(message = "Account must have an associated type")
    private AccountType accountType;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Rental> rentals;

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

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
