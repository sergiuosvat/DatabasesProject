module com.example.userinterfacedatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.userinterfacedatabase to javafx.fxml;
    exports com.example.userinterfacedatabase;
}