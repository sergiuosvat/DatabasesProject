package dbproject.student;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InscriereStudentGrupStudiu implements Initializable {
    @FXML
    private Button inscriere;
    @FXML
    private Button back;
    @FXML
    private Button leave;
    @FXML
    private TextField idgrup;

    private static String idStudent;

    public static void setIdStudent(String idstudent)
    {
        idStudent = idstudent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event,"StudentPanel.fxml","Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        inscriere.setOnAction(event -> {
            String s = idgrup.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL adaugaParticipantGrupStudiu(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,s);
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati fost adaugat cu succes!");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Eroare!");
                alert.show();
                throw new RuntimeException(e);
            }
        });
        leave.setOnAction(event -> {
            String s = idgrup.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL eliminaParticipantGrupStudiu(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,s);
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati fost eliminat cu succes!");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Eroare!");
                alert.show();
                throw new RuntimeException(e);
            }
        });

    }
}
