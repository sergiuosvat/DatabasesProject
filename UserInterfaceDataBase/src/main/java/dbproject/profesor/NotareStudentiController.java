package dbproject.profesor;

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

public class NotareStudentiController implements Initializable {
    @FXML
    private TextField seminar;
    @FXML
    private TextField colocviu;
    @FXML
    private TextField examen;
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private TextField notacoloc;
    @FXML
    private TextField notasem;
    @FXML
    private TextField notaexam;
    @FXML
    private TextField email;
    @FXML
    private TextField materie;
    private String idStudent;
    private String idMaterie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "ProfesorPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        save.setOnAction(event -> {
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            ResultSet resultSet;
            ResultSet resultSet1;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("SELECT * from student where email = ?");
                preparedStatement.setString(1, email.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                {
                    idStudent = resultSet.getString("idStudent");
                }
                preparedStatement1 = connection.prepareStatement("SELECT * from materie where numeMaterie = ?");
                preparedStatement1.setString(1,materie.getText());
                resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next())
                {
                    idMaterie = resultSet1.getString("idMaterie");
                }
                preparedStatement2 = connection.prepareStatement("CALL medieAleasa(?, ? ,?, ?, ?, ?, ?, ?)");
                preparedStatement2.setString(1, seminar.getText());
                preparedStatement2.setString(2, colocviu.getText());
                preparedStatement2.setString(3, examen.getText());
                preparedStatement2.setString(4, notasem.getText());
                preparedStatement2.setString(5, notacoloc.getText());
                preparedStatement2.setString(6, notaexam.getText());
                preparedStatement2.setString(7,idStudent);
                preparedStatement2.setString(8,idMaterie);
                preparedStatement2.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Notele au fost adaugate cu success!");
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
