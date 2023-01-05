package dbproject.student;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    public Button date_activ1;
    @FXML
    private Button log_out;
    @FXML
    private Button date_pers;
    @FXML
    private Button date_activ;
    @FXML
    private Button grupstud;

    @FXML
    private Button grupstud1;
    @FXML
    private Button note;
    @FXML
    private Button orar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log_out.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "LoginForm.fxml", "Login Form", 800, 441);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        date_pers.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DatePersonalePovStudent.fxml", "Date Personale", 696, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        date_activ.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DateActivitatiStudent.fxml", "Date activitati", 931, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        date_activ1.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "InscriereStudent.fxml", "Inscriere Activitate", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        grupstud.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "InscriereGrupStudiu.fxml", "Inscriere grup de studiu", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        grupstud1.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "GrupStudiuStudent.fxml", "Creare/Stergere grup de studiu", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        note.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "NoteStudent.fxml", "Note", 770,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        orar.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "Orar.fxml", "Orar", 765,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
