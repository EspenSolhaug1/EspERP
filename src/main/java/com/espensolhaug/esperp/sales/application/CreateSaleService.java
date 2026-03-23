package com.espensolhaug.esperp.sales.application;

import com.espensolhaug.esperp.sales.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateSaleService {
    // private SaleRepository saleRepository;

    public Sale createSale(CreateSaleRequest request) {
        List<SaleItem> items;
        BigDecimal totalAmount;
        Payment payment;
        if (!validate(request)) {
            throw new IllegalArgumentException("Invalid request");
        }

        items = mapItems(request);
        totalAmount = calculateTotalAmount(items);
        payment = createPayment(request, totalAmount);

        LocalDateTime timestamp = LocalDateTime.now();
        return new Sale(
                UUID.randomUUID(),
                request.getCustomer(),
                request.getCashier(),
                request.getStore(),
                payment,
                timestamp,
                SaleStatus.COMPLETED,
                items,
                totalAmount
        );
    }

    private boolean validate(CreateSaleRequest request) {
        if ( request.getCustomer().isBlank() ) {
            return false;
        }
        if ( request.getCashier().isBlank() ) {
            return false;
        }
        if ( request.getStore().isBlank() ) {
            return false;
        }
        if ( request.getSaleItems().stream()
                .anyMatch( item ->
                        item.getProduct().isBlank() ||
                        item.getPriceAtSaleTime().compareTo(BigDecimal.ZERO) <= 0 ||
                        item.getQuantity() <= 0)) {
            return false;
        }
        return true;
    }

    private List<SaleItem> mapItems(CreateSaleRequest request) {
        List<SaleItem> saleItems = new ArrayList<>();
        request.getSaleItems().forEach( item -> {
            saleItems.add(
                    new SaleItem(
                            item.getProduct(),
                            item.getQuantity(),
                            item.getPriceAtSaleTime()
                    )
            );
        });
        return saleItems;
    }

    private BigDecimal calculateTotalAmount(List<SaleItem> items) {
        return items.stream().map(SaleItem::getPriceAtSaleTime).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Payment createPayment(CreateSaleRequest request, BigDecimal amount) {
        return new Payment(request.getPaymentMethod(), amount, PaymentStatus.COMPLETED);
    }
}
