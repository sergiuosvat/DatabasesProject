package dbproject.student;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InscriereStudent implements Initializable {
    @FXML
    public TextField idActiv;
    @FXML
    public Button save;
    @FXML
    public Button back;
    @FXML
    public Button delete;

    private static String idStudent;

    public static void setIdStudent(String idStudent1)
    {
        idStudent = idStudent1;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "StudentPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        save.setOnAction(event -> {
            String s = idActiv.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL adaugaParticipantActivitate(?, ?)");
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
        delete.setOnAction(event -> {
            String a = idActiv.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL eliminaParticipantActivitate(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,a);
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
