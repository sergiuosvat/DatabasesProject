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
    private String idMaterie;
    private String idActivitate;

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
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            PreparedStatement preparedStatement3;
            ResultSet resultSet;
            ResultSet resultSet1;
            ResultSet resultSet2;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement2 = connection.prepareStatement("SELECT * from materie where numeMaterie = ?");
                preparedStatement2.setString(1,idActiv.getText());
                resultSet1 = preparedStatement2.executeQuery();
                if(resultSet1.next())
                {
                    idMaterie = resultSet1.getString("idMaterie");
                }
                preparedStatement3 = connection.prepareStatement("SELECT * from activitate where idMaterie = ?");
                preparedStatement3.setString(1,idMaterie);
                resultSet2 = preparedStatement3.executeQuery();
                if (resultSet2.next())
                {
                    idActivitate = resultSet2.getString("idActivitate");
                }
                preparedStatement1 = connection.prepareStatement("SELECT * from student where email = ?");
                preparedStatement1.setString(1, emailStud.getText());
                resultSet = preparedStatement1.executeQuery();
                while (resultSet.next())
                {
                    idStudent = resultSet.getString("idStudent");
                }
                preparedStatement = connection.prepareStatement("CALL adaugaParticipantActivitate(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,idActivitate);
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
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            PreparedStatement preparedStatement3;
            ResultSet resultSet;
            ResultSet resultSet1;
            ResultSet resultSet2;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement2 = connection.prepareStatement("SELECT * from materie where numeMaterie = ?");
                preparedStatement2.setString(1,idActiv.getText());
                resultSet1 = preparedStatement2.executeQuery();
                if(resultSet1.next())
                {
                    idMaterie = resultSet1.getString("idMaterie");
                }
                preparedStatement3 = connection.prepareStatement("SELECT * from activitate where idMaterie = ?");
                preparedStatement3.setString(1,idMaterie);
                resultSet2 = preparedStatement3.executeQuery();
                if (resultSet2.next())
                {
                    idActivitate = resultSet2.getString("idActivitate");
                }
                preparedStatement1 = connection.prepareStatement("SELECT * from student where email = ?");
                preparedStatement1.setString(1, emailStud.getText());
                resultSet = preparedStatement1.executeQuery();
                while (resultSet.next())
                {
                    idStudent = resultSet.getString("idStudent");
                }
                preparedStatement = connection.prepareStatement("CALL eliminaParticipantActivitate(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,idActivitate);
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
