package com.upgrad.Payment.service;

import com.upgrad.Payment.dao.PaymentDAO;
import com.upgrad.Payment.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{

    @Autowired
    private PaymentDAO paymentDAO;
    @Override
    public TransactionDetailsEntity createPayment(TransactionDetailsEntity transaction) {
        TransactionDetailsEntity newTransaction = transaction;
        return paymentDAO.save(newTransaction);
    }

    @Override
    public TransactionDetailsEntity getPaymentDetails(int id) {
        TransactionDetailsEntity transaction = paymentDAO.getById(id);
        return transaction;
    }
}
