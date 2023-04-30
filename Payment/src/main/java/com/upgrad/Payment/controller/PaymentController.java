package com.upgrad.Payment.controller;

import com.upgrad.Payment.dto.TransactionDTO;
import com.upgrad.Payment.entity.TransactionDetailsEntity;
import com.upgrad.Payment.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPayment(@RequestBody TransactionDTO transactionDTO){
        TransactionDetailsEntity newTransaction = modelMapper.map(transactionDTO, TransactionDetailsEntity.class);
        TransactionDetailsEntity createdTransaction = paymentService.createPayment(newTransaction);
        TransactionDTO createdTransactionDTO = modelMapper.map(createdTransaction, TransactionDTO.class);
        return new ResponseEntity(createdTransactionDTO.getTransactionId(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/transaction/{transactionId}")
    public ResponseEntity getPayment(@PathVariable(name = "transactionId") int id){
        TransactionDetailsEntity transactionDetails = paymentService.getPaymentDetails(id);
        TransactionDTO transactionDTO = modelMapper.map(transactionDetails, TransactionDTO.class);
        return new ResponseEntity(transactionDTO, HttpStatus.OK);
    }
}
