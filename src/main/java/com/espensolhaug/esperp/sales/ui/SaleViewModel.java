package com.espensolhaug.esperp.sales.ui;

import com.espensolhaug.esperp.sales.domain.Sale;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class SaleViewModel {
    private final StringProperty id;
    private final StringProperty customer;
    private final StringProperty total;
    private final StringProperty status;

    public SaleViewModel(Sale sale) {
        this.id = new SimpleStringProperty(sale.getId().toString());
        this.customer = new SimpleStringProperty(sale.getCustomer());
        this.total = new SimpleStringProperty(formatCurrency(sale.getTotalAmount()));
        this.status = new SimpleStringProperty(formatStatus(sale));
    }
    private String formatCurrency(BigDecimal amount) {
        return amount.toString();
    }
    private String formatStatus(Sale sale) {
        return switch (sale.getStatus()) {
            case COMPLETED -> "Completed";
            case ON_HOLD -> "On-Hold";
            case CANCELLED -> "Cancelled";
        };
    }

    public StringProperty idProperty() { return id; }
    public StringProperty customerProperty() { return customer; }
    public StringProperty totalProperty() { return total; }
    public StringProperty statusProperty() { return status; }
}
