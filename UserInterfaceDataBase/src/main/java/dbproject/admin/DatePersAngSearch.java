package dbproject.admin;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DatePersAngSearch implements Initializable {
    @FXML
    private Button searchAng;
    @FXML
    private Button backDateAng;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backDateAng.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        searchAng.setOnAction(event -> {
            try {
                DBUtils.searchUser(event, searchField.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
