package com.leonel.pruebarule1.dto;

import java.io.Serializable;
import java.util.Objects;

public class ErrorDetailsDTO implements Serializable {

    private Object rejectedValue;
    private String field;
    private String message;

    public ErrorDetailsDTO(Object rejectedValue, String field, String message) {
        this.rejectedValue = rejectedValue;
        this.field = field;
        this.message = message;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDetailsDTO that = (ErrorDetailsDTO) o;
        return rejectedValue.equals(that.rejectedValue) && field.equals(that.field) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rejectedValue, field, message);
    }
}
