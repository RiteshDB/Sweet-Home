package com.upgrad.Payment.service;

import com.upgrad.Payment.entity.TransactionDetailsEntity;

public interface IPaymentService {
    public TransactionDetailsEntity createPayment(TransactionDetailsEntity transaction);

    public TransactionDetailsEntity getPaymentDetails(int id);
}
