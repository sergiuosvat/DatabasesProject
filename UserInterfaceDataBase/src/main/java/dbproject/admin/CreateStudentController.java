package dbproject.admin;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CreateStudentController implements Initializable {
    @FXML
    private TextField nume_box;
    @FXML
    private Button save;
    @FXML
    private TextField cnp_box;
    @FXML
    private TextField prenume_box;
    @FXML
    private TextField adresa_box;
    @FXML
    private TextField tel_box;
    @FXML
    private TextField email_box;
    @FXML
    private TextField iban_box;
    @FXML
    private TextField contract_box;
    @FXML
    private TextField an_box;
    @FXML
    private TextField grupa_box;
    @FXML
    private TextField serie_box;

    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save.setOnAction(event -> {
            try {
                DBUtils.CreateStudent(cnp_box.getText(),nume_box.getText(), prenume_box.getText(), adresa_box.getText(), tel_box.getText(), email_box.getText(), iban_box.getText(), contract_box.getText(), an_box.getText(), serie_box.getText(), grupa_box.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
