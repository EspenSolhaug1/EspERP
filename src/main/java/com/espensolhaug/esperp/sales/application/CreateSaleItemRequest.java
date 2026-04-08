package com.espensolhaug.esperp.sales.application;

import lombok.Getter;

import java.math.BigDecimal;

public class CreateSaleItemRequest {
    @Getter
    private String product;
    @Getter
    private int quantity;
    @Getter
    private BigDecimal priceAtSaleTime;

    public CreateSaleItemRequest(String product, int quantity, BigDecimal priceAtSaleTime) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtSaleTime = priceAtSaleTime;
    }

    @Override
    public String toString() {
        return "CreateSaleItemRequest{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", priceAtSaleTime=" + priceAtSaleTime +
                '}';
    }
}
