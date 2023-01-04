module com.example.userinterfacedatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dbproject to javafx.fxml;
    exports dbproject;
    exports dbproject.admin;
    opens dbproject.admin to javafx.fxml;
    exports dbproject.student;
    opens dbproject.student to javafx.fxml;
}