package com.espensolhaug.esperp.sales.ui;


import com.espensolhaug.esperp.sales.application.CreateSaleService;
import com.espensolhaug.esperp.sales.application.ListSalesService;
import com.espensolhaug.esperp.sales.domain.PaymentMethod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class SalesController {
    private final CreateSaleService createSaleService;
    private final ListSalesService listSalesService;
    @FXML private TextField customerField;
    @FXML private TextField cashierField;
    @FXML private TextField storeField;
    @FXML private TextField productField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private ChoiceBox<PaymentMethod> paymentBox;

    @FXML private Button submitButton;
    @FXML private TableView<SaleViewModel> salesTable;
    @FXML private TableColumn<SaleViewModel, String> customerColumn;
    @FXML private TableColumn<SaleViewModel, String> totalColumn;
    @FXML private TableColumn<SaleViewModel, String> statusColumn;

    private final CreateSaleFormViewModel form = new CreateSaleFormViewModel();

    public SalesController(CreateSaleService createSaleService, ListSalesService listSalesService) {
        this.createSaleService = createSaleService;
        this.listSalesService = listSalesService;
    }

    @FXML
    public void initialize() {
        // Binds text field
        customerField.textProperty().bindBidirectional(form.customerProperty());
        cashierField.textProperty().bindBidirectional(form.cashierProperty());
        storeField.textProperty().bindBidirectional(form.storeProperty());
        productField.textProperty().bindBidirectional(form.productProperty());
        quantityField.textProperty().bindBidirectional(form.quantityProperty());
        priceField.textProperty().bindBidirectional(form.priceProperty());
        // Payment binding
        paymentBox.getItems().setAll(PaymentMethod.values());
        paymentBox.valueProperty().bindBidirectional(form.paymentMethodProperty());
        // Table setup
        customerColumn.setCellValueFactory(data -> data.getValue().customerProperty());
        totalColumn.setCellValueFactory(data -> data.getValue().totalProperty());
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());

        refreshTable();
    }

    private void refreshTable() {
        List<SaleViewModel> viewModels = listSalesService.getAllSales()
                .stream()
                .map(SaleViewModel::new)
                .toList();
        salesTable.getItems().setAll(viewModels);
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        try {
        createSaleService.createSale(form.toRequest());
        refreshTable();
        form.clear();
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
