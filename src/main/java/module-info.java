module com.example.zorocafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.zorocafe to javafx.fxml;
    exports com.example.zorocafe;
}