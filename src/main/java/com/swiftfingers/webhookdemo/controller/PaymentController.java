package com.swiftfingers.webhookdemo.controller;

import com.swiftfingers.webhookdemo.model.Payment;
import com.swiftfingers.webhookdemo.dto.PaymentRequest;
import com.swiftfingers.webhookdemo.dto.PaymentResponse;
import com.swiftfingers.webhookdemo.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity processPayments (@Valid() @RequestBody PaymentRequest paymentRequest, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        Payment payment = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @PostMapping("/webhook")
    public void handlePaymentResponse (@RequestBody PaymentResponse paymentResponse) {
        paymentService.handlePaymentResponse(paymentResponse);

    }

}
