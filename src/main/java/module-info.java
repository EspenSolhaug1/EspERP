module com.espensolhaug.esperp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens com.espensolhaug.esperp to javafx.fxml;
    exports com.espensolhaug.esperp;
}