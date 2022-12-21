package com.example.userinterfacedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_username;

    @FXML
    private Button login_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_button.setOnAction(event -> {
            try {
                DBUtils.logInUser(event,tf_username.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
