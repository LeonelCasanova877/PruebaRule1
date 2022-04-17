package com.leonel.allstreaming.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorDTO implements Serializable {

    private LocalDateTime timeStamp;
    private int status;
    private String message;
    private List<ErrorDetailsDTO> errorDetails = new ArrayList<>();

    public ErrorDTO(LocalDateTime timeStamp, int status, String message, List<ErrorDetailsDTO> errorDetails) {

        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public ErrorDTO(LocalDateTime timeStamp, int status, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorDetailsDTO> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<ErrorDetailsDTO> errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDTO errorDTO = (ErrorDTO) o;
        return status == errorDTO.status && timeStamp.equals(errorDTO.timeStamp) && message.equals(errorDTO.message) && Objects.equals(errorDetails, errorDTO.errorDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, status, message, errorDetails);
    }
}
