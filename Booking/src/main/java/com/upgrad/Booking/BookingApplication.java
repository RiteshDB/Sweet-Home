package com.upgrad.Booking;

import com.upgrad.Booking.dao.BookingDAO;
import com.upgrad.Booking.entities.BookingInfoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RestController
@SpringBootApplication
@EnableEurekaServer
public class BookingApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(BookingApplication.class, args);

	}

	@Bean
	public static ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public static RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
