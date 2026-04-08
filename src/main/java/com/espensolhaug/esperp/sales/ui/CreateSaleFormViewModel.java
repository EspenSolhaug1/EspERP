package com.espensolhaug.esperp.sales.ui;

import com.espensolhaug.esperp.sales.application.CreateSaleItemRequest;
import com.espensolhaug.esperp.sales.application.CreateSaleRequest;
import com.espensolhaug.esperp.sales.domain.PaymentMethod;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.util.List;

public class CreateSaleFormViewModel {
    private final StringProperty customer = new SimpleStringProperty("");
    private final StringProperty cashier = new SimpleStringProperty("");
    private final StringProperty store = new SimpleStringProperty("");
    private final StringProperty product = new SimpleStringProperty("");
    private final StringProperty quantity = new SimpleStringProperty("");
    private final StringProperty price = new SimpleStringProperty("");

    private final ObjectProperty<PaymentMethod> paymentMethod = new SimpleObjectProperty<>();
    private final BooleanBinding valid;

    public CreateSaleFormViewModel() {
        valid = Bindings.createBooleanBinding(() ->
                !customer.get().isBlank() &&
                !cashier.get().isBlank() &&
                !store.get().isBlank() &&
                !product.get().isBlank() &&
                isPositiveInteger(quantity.get()) &&
                isPositiveDecimal(price.get()) &&
                paymentMethod.get() != null,
                customer, cashier, store, product, quantity, price, paymentMethod);
    }

    private boolean isPositiveInteger(String value) {
        try {
            return Integer.parseInt(value) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isPositiveDecimal(String value) {
        try {
            return new BigDecimal(value).compareTo(BigDecimal.ZERO) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public StringProperty customerProperty() { return customer; }
    public StringProperty cashierProperty() { return cashier; }
    public StringProperty storeProperty() { return store; }
    public StringProperty productProperty() { return product; }
    public StringProperty quantityProperty() { return quantity; }
    public StringProperty priceProperty() { return price; }
    public ObjectProperty<PaymentMethod> paymentMethodProperty() { return paymentMethod; }

    public BooleanBinding validProperty() { return valid; }

    public CreateSaleRequest toRequest() {
        return new CreateSaleRequest(
                customer.get(),
                cashier.get(),
                store.get(),
                paymentMethod.get(),
                List.of(
                        new CreateSaleItemRequest(
                                product.get(),
                                Integer.parseInt(quantity.get()),
                                new BigDecimal(price.get())
                        )
                )
        );
    }

    public void clear() {
        customer.set("");
        cashier.set("");
        store.set("");
        product.set("");
        quantity.set("");
        price.set("");
        paymentMethod.set(null);
    }
}
