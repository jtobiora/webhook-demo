package com.swiftfingers.webhookdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private double amount;
    private String userId;
    @NotBlank(message = "Card number cannot be empty")
    private String cardNumber;
    @NotBlank(message = "Expiry date must be provided")
    private String expiryDate;
    private String transactionId;
}
