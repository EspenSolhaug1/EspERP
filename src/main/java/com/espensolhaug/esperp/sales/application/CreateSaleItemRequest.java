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
}
