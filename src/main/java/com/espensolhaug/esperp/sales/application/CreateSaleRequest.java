package com.espensolhaug.esperp.sales.application;

import com.espensolhaug.esperp.sales.domain.PaymentMethod;
import lombok.Getter;

import java.util.List;

public class CreateSaleRequest {
    @Getter
    private String customer;
    @Getter
    private String cashier;
    @Getter
    private String store;
    @Getter
    private PaymentMethod paymentMethod;
    @Getter
    private List<CreateSaleItemRequest> saleItems;
}
