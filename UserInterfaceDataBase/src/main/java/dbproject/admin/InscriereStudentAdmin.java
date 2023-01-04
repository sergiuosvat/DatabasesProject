package dbproject.admin;

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

public class InscriereStudentAdmin implements Initializable {
    @FXML
    public TextField idActiv;
    @FXML
    public Button save;
    @FXML
    public Button back;
    @FXML
    public Button delete;
    @FXML
    private TextField emailStud;

    private static String idStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        save.setOnAction(event -> {
            String s = idActiv.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            ResultSet resultSet;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement1 = connection.prepareStatement("SELECT * from student where email = ?");
                preparedStatement1.setString(1, emailStud.getText());
                resultSet = preparedStatement1.executeQuery();
                while (resultSet.next())
                {
                    idStudent = resultSet.getString("idStudent");
                }
                preparedStatement = connection.prepareStatement("CALL adaugaParticipantActivitate(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,s);
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati adaugat cu succes!");
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
            PreparedStatement preparedStatement1;
            ResultSet resultSet;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement1 = connection.prepareStatement("SELECT idStudent from student where email = ?");
                preparedStatement1.setString(1, emailStud.getText());
                resultSet = preparedStatement1.executeQuery();
                while (resultSet.next())
                {
                    idStudent = resultSet.getString("idStudent");
                }
                preparedStatement = connection.prepareStatement("CALL eliminaParticipantActivitate(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,a);
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati eliminat cu succes!");
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
