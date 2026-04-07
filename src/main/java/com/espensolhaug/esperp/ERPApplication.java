package com.espensolhaug.esperp;

import com.espensolhaug.esperp.sales.application.CreateSaleService;
import com.espensolhaug.esperp.sales.application.ListSalesService;
import com.espensolhaug.esperp.sales.application.SaleRepository;
import com.espensolhaug.esperp.sales.infrastructure.FakeSaleRepository;
import com.espensolhaug.esperp.sales.ui.SalesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ERPApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        SaleRepository repository = new FakeSaleRepository();
        CreateSaleService createSaleService = new CreateSaleService(repository);
        ListSalesService listSalesService = new ListSalesService(repository);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/espensolhaug/esperp/sales/ui/sales.fxml"));
        fxmlLoader.setControllerFactory(type -> {
            if(type == SalesController.class) {
                return new SalesController(createSaleService, listSalesService);
            }
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        });

        Parent uiRoot = fxmlLoader.load();
        Scene scene = new Scene(uiRoot, 700, 400);
        stage.setTitle("EspERP!");
        stage.setScene(scene);
        stage.show();
    }
}
