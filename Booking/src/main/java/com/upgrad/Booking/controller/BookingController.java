package com.upgrad.Booking.controller;

import com.upgrad.Booking.dto.BookingDTO;
import com.upgrad.Booking.dto.TransactionDTO;
import com.upgrad.Booking.entities.BookingInfoEntity;
import com.upgrad.Booking.exception.IncorrectBookingIdException;
import com.upgrad.Booking.exception.IncorrectPaymentmethodException;
import com.upgrad.Booking.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hotel")
public class BookingController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody BookingDTO bookingDTO){
        BookingInfoEntity bookingInfo = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        BookingInfoEntity createdBooking = bookingService.createBooking(bookingInfo);
        BookingDTO createBookingDTO = modelMapper.map(createdBooking, BookingDTO.class);
        return new ResponseEntity(createBookingDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/booking/{id}")
    public ResponseEntity getBookingDetails(@PathVariable(name = "id") int id) throws Throwable {
        BookingInfoEntity bookingInfo = bookingService.getBookingDetails(id);
        BookingDTO bookingDTO = modelMapper.map(bookingInfo, BookingDTO.class);
        return new ResponseEntity(bookingDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/live")
    public ResponseEntity live(){
        return new ResponseEntity("Live", HttpStatus.OK);
    }

    @PostMapping(value = "/booking/{id}/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makePaymentCall(@PathVariable(name = "id") int id , @RequestBody TransactionDTO transactionDTO) throws IncorrectPaymentmethodException, IncorrectBookingIdException {
        BookingInfoEntity bookingInfo = bookingService.makePayment(id, transactionDTO);
        return new ResponseEntity(bookingInfo, HttpStatus.OK);
    }



}
