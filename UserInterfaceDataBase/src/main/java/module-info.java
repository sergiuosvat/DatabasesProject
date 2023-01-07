module com.example.userinterfacedatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;


    opens dbproject to javafx.fxml;
    exports dbproject;
    exports dbproject.admin;
    opens dbproject.admin to javafx.fxml;
    exports dbproject.student;
    opens dbproject.student to javafx.fxml;
    exports dbproject.superadmin;
    opens dbproject.superadmin to javafx.fxml;
    exports dbproject.profesor;
    opens dbproject.profesor to javafx.fxml;
}