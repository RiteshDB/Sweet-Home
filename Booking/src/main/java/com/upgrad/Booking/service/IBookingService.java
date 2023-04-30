package com.upgrad.Booking.service;

import com.upgrad.Booking.dto.TransactionDTO;
import com.upgrad.Booking.entities.BookingInfoEntity;

public interface IBookingService {
    public BookingInfoEntity createBooking(BookingInfoEntity booking);

    public BookingInfoEntity getBookingDetails(int bookingId) throws Throwable;

    public BookingInfoEntity makePayment(int id, TransactionDTO transactionDTO) throws Throwable;
}
