package com.espensolhaug.esperp.sales.ui;

import com.espensolhaug.esperp.sales.application.CreateSaleItemRequest;
import com.espensolhaug.esperp.sales.application.CreateSaleRequest;
import com.espensolhaug.esperp.sales.application.CreateSaleService;
import com.espensolhaug.esperp.sales.application.ListSalesService;
import com.espensolhaug.esperp.sales.domain.PaymentMethod;
import com.espensolhaug.esperp.sales.domain.Sale;
import com.espensolhaug.esperp.sales.domain.SaleStatus;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SalesController {
    private final CreateSaleService createSaleService;
    private final ListSalesService listSalesService;
    @FXML private Label welcomeSalesText;
    @FXML private TextField customerField = new TextField();
    @FXML private TextField cashierField = new TextField();
    @FXML private TextField storeField = new TextField();
    @FXML private TextField productField = new TextField();
    @FXML private TextField quantityField = new TextField();
    @FXML private TextField priceField = new TextField();
    @FXML private ChoiceBox<String> paymentBox;
    private String paymentMethod;
    @FXML private Button submitButton;
    @FXML private TableView<Sale> salesTable;
    @FXML private TableColumn<Sale, String> customerColumn;
    @FXML private TableColumn<Sale, BigDecimal> totalColumn;
    @FXML private TableColumn<Sale, SaleStatus> statusColumn;

    public SalesController(CreateSaleService createSaleService, ListSalesService listSalesService) {
        this.createSaleService = createSaleService;
        this.listSalesService = listSalesService;
    }

    @FXML
    public void initialize() {
        customerColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCustomer()));
        totalColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getTotalAmount()));
        statusColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getStatus()));
        refreshTable();
    }

    private void refreshTable() {
        List<Sale> sales = listSalesService.getAllSales();
        salesTable.getItems().setAll(sales);
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        CreateSaleRequest request = buildRequest();
        Sale sale = createSaleService.createSale(request);
        System.out.println(sale.toString());
        refreshTable();
        clearForm();
    }

    private CreateSaleRequest buildRequest() {
        return new CreateSaleRequest(
                customerField.getText(),
                cashierField.getText(),
                storeField.getText(),
                PaymentMethod.valueOf(paymentBox.getValue()),
                List.of(new CreateSaleItemRequest(
                        productField.getText(),
                        parseInt(quantityField.getText()),
                        new BigDecimal(priceField.getText()
                        )
                ))
        );
    }

    private void clearForm() {
        customerField.clear();
        cashierField.clear();
        storeField.clear();
        productField.clear();
        quantityField.clear();
        priceField.clear();
    }
}
