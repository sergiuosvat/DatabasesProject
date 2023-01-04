package dbproject.superadmin;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateAdminController implements Initializable {
    @FXML
    private Button save;
    @FXML
    private Button back;
    @FXML
    private TextField cnp_admin;
    @FXML
    private TextField nume_admin;
    @FXML
    private TextField prenume_admin;
    @FXML
    private TextField adresa_admin;
    @FXML
    private TextField tel_admin;
    @FXML
    private TextField iban_admin;
    @FXML
    private TextField email_admin;
    @FXML
    private TextField contract_admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event,"SuperAdminPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        save.setOnAction(event -> {
            try {
                DBUtils.CreateAdmin(cnp_admin.getText(), nume_admin.getText(), prenume_admin.getText(), adresa_admin.getText(), tel_admin.getText(), email_admin.getText(), iban_admin.getText() ,contract_admin.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
