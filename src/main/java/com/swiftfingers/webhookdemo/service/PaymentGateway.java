package com.swiftfingers.webhookdemo.service;

import com.swiftfingers.webhookdemo.enums.PaymentStatus;
import com.swiftfingers.webhookdemo.model.Card;
import com.swiftfingers.webhookdemo.dto.PaymentRequest;
import com.swiftfingers.webhookdemo.dto.PaymentResponse;
import com.swiftfingers.webhookdemo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * A Hypothetical or Simulated Payment Gateway Service that does the actual payment and calls the
 * webhook URL to submit a payment response using HTTP
 */

@Service
public class PaymentGateway {

    @Value("${webhook.url}")
    private String webhookURL;
    public final RestTemplate restTemplate;
    private final CardRepository cardRepository;

    public PaymentGateway(RestTemplate restTemplate, CardRepository cardRepository) {
        this.restTemplate = restTemplate;
        this.cardRepository = cardRepository;
    }
    public void processPayments(PaymentRequest paymentRequest) {
        // Simulate actual payment processing logic here in the gateway service
        // In a real scenario, you would integrate with a secure payment processing service.
        // Here, we simulate a successful payment if the following are met:
        // - card number is correct, expiry date has not reached and balance available is greater than payment amount

        Optional<Card> optionalCard = cardRepository.findCardByCardNumber(paymentRequest.getCardNumber());
        PaymentResponse response;
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            if (card.getBalance() <= paymentRequest.getAmount()) {
                response = buildPaymentResponse("Insufficient balance.", PaymentStatus.FAILED);
            } else if (!isValidExpiryDate(paymentRequest.getExpiryDate())) {
                response = buildPaymentResponse("The card has expired.", PaymentStatus.FAILED);
            } else {
                response = buildPaymentResponse("The payment was successful", PaymentStatus.COMPLETED);
            }
        } else {
            response = buildPaymentResponse("Incorrect Card Details", PaymentStatus.FAILED);
        }

        response.setTransactionId(paymentRequest.getTransactionId());
        response.setUserEmail(paymentRequest.getUserId());

        //send response to a webhook url
        restTemplate.postForLocation(webhookURL, response);
    }

    private static boolean isValidExpiryDate(String input) {
        try {
            // Parse the input string to a YearMonth object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth expiryDate = YearMonth.parse(input, formatter);

            // Get the current date
            YearMonth currentDate = YearMonth.now();

            // Compare the expiry date with the current date
            return expiryDate.isAfter(currentDate) || expiryDate.equals(currentDate);
        } catch (DateTimeParseException e) {
           throw e;
        }
    }

    private PaymentResponse buildPaymentResponse(String message, PaymentStatus status) {
        return PaymentResponse.builder()
                .message(message)
                .isPaymentSuccessful(false)
                .status(status)
                .build();
    }
}
