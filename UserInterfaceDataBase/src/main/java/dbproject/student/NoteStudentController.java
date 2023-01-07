package dbproject.student;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NoteStudentController implements Initializable {
    @FXML
    private Label nota1;
    @FXML
    private Label nota2;
    @FXML
    private Label nota3;
    @FXML
    private Label nota4;
    @FXML
    private Label nota5;
    @FXML
    private Label nota6;
    @FXML
    private Label medie;
    @FXML
    private Button back;
    private static String idStudent;


    public static void setIdStudent(String idstudent)
    {
        idStudent = idstudent;
    }
    public static String getIdStudent()
    {
        return idStudent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection;
        PreparedStatement preparedStatement1;
        ResultSet resultSet;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement1 = connection.prepareStatement("SELECT * from situatie where idStudent = ? ");
            preparedStatement1.setString(1, idStudent);
            resultSet = preparedStatement1.executeQuery();
            if (resultSet.next())
            {
                nota1.setText(resultSet.getString("notaMaterie1"));
                nota2.setText(resultSet.getString("notaMaterie2"));
                nota3.setText(resultSet.getString("notaMaterie3"));
                nota4.setText(resultSet.getString("notaMaterie4"));
                nota5.setText(resultSet.getString("notaMaterie5"));
                nota6.setText(resultSet.getString("notaMaterie6"));
                medie.setText(resultSet.getString("Medie"));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Eroare!");
            alert.show();
            throw new RuntimeException(e);
        }
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "StudentPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
