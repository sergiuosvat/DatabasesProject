package dbproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorPanelController implements Initializable {
    @FXML
    private Button logout;
    @FXML
    private Button note;
    @FXML
    private Button datepers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "LoginForm.fxml", "Login Form", 800, 441);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        note.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "NotareStudenti.fxml", "Notare Student", 600, 479);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        datepers.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DatePersonaleProfesorPov.fxml", "Date Personale", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
