module com.espensolhaug.esperp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.espensolhaug.esperp to javafx.fxml;
    opens com.espensolhaug.esperp.sales.ui;
    opens com.espensolhaug.esperp.sales.application;
    exports com.espensolhaug.esperp;
    exports com.espensolhaug.esperp.sales.ui;
    exports com.espensolhaug.esperp.sales.application;

}