package com.espensolhaug.esperp.sales.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleViewModel {
    private final StringProperty customer;
    private final StringProperty total;
    private final StringProperty status;

    public SaleViewModel(Sale sale) {
        this.customer = new SimpleStringProperty(sale.getCustomer());
        this.total = new SimpleStringProperty(sale.getTotalAmount().toString());
        this.status = new SimpleStringProperty(sale.getStatus().toString());
    }
}
