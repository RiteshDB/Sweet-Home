package com.upgrad.Booking.exception.handler;


import com.upgrad.Booking.dto.ErrorResponse;
import com.upgrad.Booking.exception.IncorrectBookingIdException;
import com.upgrad.Booking.exception.IncorrectPaymentmethodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomeBookingErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectPaymentmethodException.class)
    public final ResponseEntity<ErrorResponse> handleincorrectPaymentException(IncorrectPaymentmethodException exception, WebRequest webRequest){
        String message = exception.getLocalizedMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, 400);
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectBookingIdException.class)
    public final ResponseEntity<ErrorResponse> handleIncorrectBookingIDException(IncorrectBookingIdException exception, WebRequest webRequest){
        String message = exception.getLocalizedMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, 400);
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }
}

