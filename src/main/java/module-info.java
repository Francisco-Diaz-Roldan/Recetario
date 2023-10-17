module com.example.diseno_interfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.diseno_interfaces to javafx.fxml;
    exports com.example.diseno_interfaces;
}