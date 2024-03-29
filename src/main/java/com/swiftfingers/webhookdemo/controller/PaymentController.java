package com.swiftfingers.webhookdemo.controller;

import com.swiftfingers.webhookdemo.model.Payment;
import com.swiftfingers.webhookdemo.dto.PaymentRequest;
import com.swiftfingers.webhookdemo.dto.PaymentResponse;
import com.swiftfingers.webhookdemo.service.PaymentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity processPayments(@Valid @RequestBody PaymentRequest paymentRequest) {
        log.info("Received payment request {} ", paymentRequest);

        Payment payment = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @PostMapping("/webhook")
    public void handlePaymentResponse (@Valid @RequestBody PaymentResponse paymentResponse) {
        log.info("Payment response : {}", paymentResponse);
        paymentService.handlePaymentResponse(paymentResponse);

    }

}
