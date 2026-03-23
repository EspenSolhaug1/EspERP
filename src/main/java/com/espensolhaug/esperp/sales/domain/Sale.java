package com.espensolhaug.esperp.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Sale {
    @Getter
    private UUID id;
    @Getter
    private final String customer;
    @Getter
    private final String cashier;
    @Getter
    private final String store;
    @Getter
    private Payment payment;
    @Getter
    private LocalDateTime timestamp;
    @Getter
    private SaleStatus status;
    @Getter
    private List<SaleItem> items;
    @Getter
    private BigDecimal totalAmount;

    public Sale(String customer, String cashier, String store, Payment payment) {
        this.customer = customer;
        this.cashier = cashier;
        this.store = store;
        this.payment = payment;
    }
}
