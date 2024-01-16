package com.swiftfingers.webhookdemo.repository;

import com.swiftfingers.webhookdemo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findPaymentsByTransactionId(String transactionId);
}
