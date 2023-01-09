package dbproject.profesor;

import dbproject.DBUtils;
import dbproject.student.NoteStudentController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfessorPanelController implements Initializable {
    @FXML
    private Button logout;
    @FXML
    private Button note;
    @FXML
    private Button datepers;
    @FXML
    private Button alocProf;
    @FXML
    private Button currentDay;
    @FXML
    private Button orarComplet;

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
        alocProf.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AlocareProfesorPov.fxml", "Alocare Profesor", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        currentDay.setOnAction(event -> {
            Connection connection;
            PreparedStatement preparedStatement1;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement1 = connection.prepareStatement("CALL programProfesor(?, ?, ?, ?, ?)");
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
        orarComplet.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DateActivitatiProfesor.fxml", "Orar complet", 658, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
