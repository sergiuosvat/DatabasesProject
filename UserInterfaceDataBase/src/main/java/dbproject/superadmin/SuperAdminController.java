package dbproject.superadmin;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuperAdminController implements Initializable {
    @FXML
    private Button admin;
    @FXML
    private Button log_out;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log_out.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "LoginForm.fxml", "Login Form", 800, 441);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        admin.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "CreateAdmin.fxml", "CreateAdminController", 600, 485);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
