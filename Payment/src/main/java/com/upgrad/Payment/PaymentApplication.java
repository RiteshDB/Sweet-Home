package com.upgrad.Payment;

import com.upgrad.Payment.dao.PaymentDAO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaServer
public class PaymentApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PaymentApplication.class, args);
//		System.out.println("This this Payment service");
//		PaymentDAO paymentDAO = context.getBean(PaymentDAO.class);
//		System.out.println(paymentDAO);



	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
