package com.espensolhaug.esperp.sales.ui;

import com.espensolhaug.esperp.sales.application.CreateSaleItemRequest;
import com.espensolhaug.esperp.sales.application.CreateSaleRequest;
import com.espensolhaug.esperp.sales.domain.PaymentMethod;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

import java.math.BigDecimal;
import java.util.List;

public class CreateSaleFormViewModel {
    private final StringProperty customer = new SimpleStringProperty("");
    private final StringProperty cashier = new SimpleStringProperty("");
    private final StringProperty store = new SimpleStringProperty("");
    private final StringProperty product = new SimpleStringProperty("");
    private final StringProperty quantity = new SimpleStringProperty("");
    private final StringProperty price = new SimpleStringProperty("");

    // Validators
    private final BooleanBinding customerValid;
    private final BooleanBinding cashierValid;
    private final BooleanBinding storeValid;
    private final BooleanBinding productValid;
    private final BooleanBinding quantityValid;
    private final BooleanBinding priceValid;
    private final BooleanBinding paymentValid;
    private final BooleanBinding valid;

    // Checks for first user interaction
    private final BooleanProperty customerTouched = new SimpleBooleanProperty(false);
    private final BooleanProperty cashierTouched = new SimpleBooleanProperty(false);
    private final BooleanProperty storeTouched = new SimpleBooleanProperty(false);
    private final BooleanProperty productTouched = new SimpleBooleanProperty(false);
    private final BooleanProperty quantityTouched = new SimpleBooleanProperty(false);
    private final BooleanProperty priceTouched = new SimpleBooleanProperty(false);

    private final ObjectProperty<PaymentMethod> paymentMethod = new SimpleObjectProperty<>();

    public CreateSaleFormViewModel() {
        customerValid = Bindings.createBooleanBinding(
                () -> !customer.get().isBlank(), customer);
        cashierValid = Bindings.createBooleanBinding(
                () -> !cashier.get().isBlank(), cashier);
        storeValid = Bindings.createBooleanBinding(
                () -> !store.get().isBlank(), store);
        productValid = Bindings.createBooleanBinding(
                () -> !product.get().isBlank(), product);
        quantityValid = Bindings.createBooleanBinding(
                () -> isPositiveInteger(quantity.get()), quantity);
        priceValid = Bindings.createBooleanBinding(
                () -> isPositiveDecimal(price.get()), price);
        paymentValid = Bindings.createBooleanBinding(
                () -> paymentMethod.get() != null, paymentMethod);

        valid = customerValid
                .and(cashierValid)
                .and(storeValid)
                .and(productValid)
                .and(quantityValid)
                .and(priceValid)
                .and(paymentValid);
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

        customerTouched.set(false);
        cashierTouched.set(false);
        storeTouched.set(false);
        productTouched.set(false);
        quantityTouched.set(false);
        priceTouched.set(false);
    }

    public BooleanBinding customerInvalidVisible() {
        return customerTouched.and(customerValid.not());
    }
    public BooleanBinding cashierInvalidVisible() {
        return cashierTouched.and(cashierValid.not());
    }
    public BooleanBinding storeInvalidVisible() {
        return storeTouched.and(storeValid.not());
    }
    public BooleanBinding productInvalidVisible() {
        return productTouched.and(productValid.not());
    }
    public BooleanBinding quantityInvalidVisible() {
        return quantityTouched.and(quantityValid.not());
    }
    public BooleanBinding priceInvalidVisible() {
        return priceTouched.and(priceValid.not());
    }

    public StringProperty customerProperty() { return customer; }
    public StringProperty cashierProperty() { return cashier; }
    public StringProperty storeProperty() { return store; }
    public StringProperty productProperty() { return product; }
    public StringProperty quantityProperty() { return quantity; }
    public StringProperty priceProperty() { return price; }
    public ObjectProperty<PaymentMethod> paymentMethodProperty() { return paymentMethod; }

    public BooleanBinding customerValidProperty() { return customerValid; }
    public BooleanBinding cashierValidProperty() { return cashierValid; }
    public BooleanBinding storeValidProperty() { return storeValid; }
    public BooleanBinding productValidProperty() { return productValid; }
    public BooleanBinding quantityValidProperty() { return quantityValid; }
    public BooleanBinding priceValidProperty() { return priceValid; }
    public BooleanBinding paymentValidProperty() { return paymentValid; }

    public BooleanProperty customerTouchedProperty() { return customerTouched; }
    public BooleanProperty cashierTouchedProperty() { return cashierTouched; }
    public BooleanProperty storeTouchedProperty() { return storeTouched; }
    public BooleanProperty productTouchedProperty() { return productTouched; }
    public BooleanProperty quantityTouchedProperty() { return quantityTouched; }
    public BooleanProperty priceTouchedProperty() { return priceTouched; }

}
