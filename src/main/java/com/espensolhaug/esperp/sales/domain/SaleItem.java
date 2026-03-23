package com.espensolhaug.esperp.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SaleItem {
    private String product;
    private int quantity;
    private BigDecimal priceAtSaleTime;
}
