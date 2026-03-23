package com.espensolhaug.esperp.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Payment {
    private PaymentMethod  paymentMethod;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
}

