package dbproject.student;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    public Button date_activ1;
    @FXML
    private Button log_out;
    @FXML
    private Button date_pers;
    @FXML
    private Button grupStud;
    @FXML
    private Button grupstud;

    @FXML
    private Button grupstud1;
    @FXML
    private Button note;
    @FXML
    private Button orar;
    @FXML
    private Button currentDay;
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
        grupStud.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "GrupStudiuView.fxml", "Date activitati", 658, 400);
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
                DBUtils.changeScene(event, "NoteStudent.fxml", "Note", 926,400);
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
        currentDay.setOnAction(event -> {
            Connection connection;
            PreparedStatement preparedStatement1;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement1 = connection.prepareStatement("CALL programStudent(?, ?, ?, ?, ?)");
                preparedStatement1.setString(1, NoteStudentController.getIdStudent());
                preparedStatement1.setString(2,"NU");
                preparedStatement1.setString(3,"NU");
                preparedStatement1.setString(4,"DA");
                preparedStatement1.setString(5,"NU");
                preparedStatement1.executeQuery();
                Desktop.getDesktop().open(new File("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads"));
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Eroare!");
                alert.show();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
