module com.example.userinterfacedatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.userinterfacedatabase to javafx.fxml;
    exports com.example.userinterfacedatabase;
}