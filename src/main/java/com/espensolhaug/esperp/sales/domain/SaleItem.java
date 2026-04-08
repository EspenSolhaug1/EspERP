package com.espensolhaug.esperp.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SaleItem {
    private String product;
    private int quantity;
    private BigDecimal priceAtSaleTime;
}
