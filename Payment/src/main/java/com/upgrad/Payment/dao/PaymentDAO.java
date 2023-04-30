package com.upgrad.Payment.dao;

import com.upgrad.Payment.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDAO extends JpaRepository<TransactionDetailsEntity, Integer> {
}
