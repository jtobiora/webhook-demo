package com.swiftfingers.webhookdemo.dto;

import com.swiftfingers.webhookdemo.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String message;
    private PaymentStatus status;
    private String transactionId;
    private boolean isPaymentSuccessful;
}
