package com.leonel.pruebarule1.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class RentalDTO implements Serializable {

    private Long price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String userEmail;
    private Long hours;


    public RentalDTO(Long price, LocalDateTime startDate, LocalDateTime endDate, String userEmail, Long hours) {
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userEmail = userEmail;
        this.hours = hours;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDTO rentalDTO = (RentalDTO) o;
        return price.equals(rentalDTO.price) && startDate.equals(rentalDTO.startDate) && endDate.equals(rentalDTO.endDate) && userEmail.equals(rentalDTO.userEmail) && hours.equals(rentalDTO.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, startDate, endDate, userEmail, hours);
    }
}
