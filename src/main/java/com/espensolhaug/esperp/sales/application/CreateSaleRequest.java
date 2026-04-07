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

    public CreateSaleRequest(String customer, String cashier, String store, PaymentMethod paymentMethod, List<CreateSaleItemRequest> saleItems) {
        this.customer = customer;
        this.cashier = cashier;
        this.store = store;
        this.paymentMethod = paymentMethod;
        this.saleItems = saleItems;
    }

    @Override
    public String toString() {
        return "CreateSaleRequest{" +
                "customer='" + customer + '\'' +
                ", cashier='" + cashier + '\'' +
                ", store='" + store + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", saleItems=" + saleItems.toString() +
                '}';
    }
}
