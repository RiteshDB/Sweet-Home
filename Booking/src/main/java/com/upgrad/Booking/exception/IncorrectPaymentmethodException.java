package com.upgrad.Booking.exception;

public class IncorrectPaymentmethodException extends Exception{
    public IncorrectPaymentmethodException(String error){
        super(error);
    }
}
