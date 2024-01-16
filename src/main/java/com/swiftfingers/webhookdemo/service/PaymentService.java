package com.swiftfingers.webhookdemo.service;

import com.swiftfingers.webhookdemo.enums.PaymentStatus;
import com.swiftfingers.webhookdemo.model.Payment;
import com.swiftfingers.webhookdemo.dto.PaymentRequest;
import com.swiftfingers.webhookdemo.dto.PaymentResponse;
import com.swiftfingers.webhookdemo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGatewayService;

    private final EmailService emailService;

    public PaymentService (PaymentRepository paymentRepository,
                           PaymentGateway paymentGatewayService,
                           EmailService emailService) {
        this.paymentRepository = paymentRepository;
        this.paymentGatewayService = paymentGatewayService;
        this.emailService = emailService;
    }

    public Payment processPayment(PaymentRequest paymentRequest) {
        String transactionId = UUID.randomUUID().toString();
        Payment payment = Payment.builder().userId(paymentRequest.getUserId())
                .paymentAmount(paymentRequest.getAmount()).transactionId(transactionId)
                .status(PaymentStatus.PROCESSING)
                .message("Payment pending.")
                .build();

        payment = paymentRepository.save(payment);
        paymentRequest.setTransactionId(payment.getTransactionId());
        paymentGatewayService.processPayments(paymentRequest);

        return payment;
    }

    public Payment findByTransactionId (String transactionId) {
        return paymentRepository.findPaymentsByTransactionId(transactionId).orElseThrow();
    }

    public void updatePaymentStatus (PaymentResponse response) {
        if (Objects.isNull(response))
            throw new NoSuchElementException("No Payment response received");

        Payment payment = findByTransactionId(response.getTransactionId());
        payment.setStatus(response.getStatus());
        payment.setMessage(response.getMessage());
        paymentRepository.save(payment);
    }

}
