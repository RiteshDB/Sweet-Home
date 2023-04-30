package com.upgrad.Booking.service;

import com.upgrad.Booking.dao.BookingDAO;
import com.upgrad.Booking.dto.TransactionDTO;
import com.upgrad.Booking.entities.BookingInfoEntity;
import com.upgrad.Booking.exception.IncorrectBookingIdException;
import com.upgrad.Booking.exception.IncorrectPaymentmethodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class BookingService implements IBookingService{

    @Value("${payment.url}")
    private String paymentURL;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BookingDAO bookingDAO;
    @Override
    public BookingInfoEntity createBooking(BookingInfoEntity booking) {
        BookingInfoEntity newBooking = booking;
        newBooking.setRoomNumbers(generateRoomNumber(newBooking.getNumOfRooms()));
        newBooking.setRoomPrice(getPrice(newBooking));
        newBooking.setBookedOn(LocalDate.now());
        newBooking.setTransactionId(0);
        return bookingDAO.save(newBooking);
    }

    @Override
    public BookingInfoEntity getBookingDetails(int bookingId) throws IncorrectBookingIdException {
         BookingInfoEntity bookingDetails = bookingDAO.findById(bookingId)
                 .orElseThrow(() -> new IncorrectBookingIdException("Invalid Booking Id"));
         return bookingDetails;
    }

    @Override
    public BookingInfoEntity makePayment(int id, TransactionDTO transactionDTO) throws IncorrectPaymentmethodException, IncorrectBookingIdException {
        if(transactionDTO.getPaymentMode().equals("CARD") || transactionDTO.getPaymentMode().equals("UPI")) {
            String url = "http://localhost:8083/payment/transaction";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<TransactionDTO> entity = new HttpEntity<>(transactionDTO, headers);
            Integer transactionId =  restTemplate.exchange(url, HttpMethod.POST, entity, Integer.class).getBody();
            BookingInfoEntity bookingInfo = getBookingDetails(id);
            bookingInfo.setTransactionId(transactionId);
            return bookingInfo;
        }
        else{
            throw new IncorrectPaymentmethodException("Invalid mode of payment");
        }

    }

    private int getPrice(BookingInfoEntity newBooking) {

        int days = Period.between(newBooking.getFromDate(), newBooking.getToDate()).getDays();
        int price = 1000* days* newBooking.getNumOfRooms();
        return price;

    }

    private String generateRoomNumber(int numOfRooms) {
        String roomNumbers = "";
        ArrayList<String> rooms = getRandomNumbers(numOfRooms);
        for(String room: rooms){
            roomNumbers += (room+",");
        }
        return roomNumbers;
    }

    public static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }
}
