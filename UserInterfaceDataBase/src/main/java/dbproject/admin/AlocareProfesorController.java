package dbproject.admin;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlocareProfesorController implements Initializable {
    @FXML
    private ChoiceBox<String> tipactiv;
    private final String[] arr = {"Seminar", "Curs", "Laborator"};
    @FXML
    private Button save;
    @FXML
    private Button back;
    @FXML
    private TextField min;
    @FXML
    private TextField max;
    @FXML
    private TextField durata;
    @FXML
    private TextField frecv;
    @FXML
    private TextField profesor;
    @FXML
    private TextField materie;
    @FXML
    private DatePicker data;
    private String idMaterie;
    private String idProfesor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        tipactiv.getItems().addAll(arr);
        save.setOnAction(event -> {
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            ResultSet resultSet;
            ResultSet resultSet1;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("SELECT * FROM profesor WHERE email = ?");
                preparedStatement.setString(1, profesor.getText());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    idProfesor = resultSet.getString("idProfesor");
                }
                preparedStatement1 = connection.prepareStatement("SELECT  * from materie where numeMaterie = ?");
                preparedStatement1.setString(1,materie.getText());
                resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next())
                {
                    idMaterie = resultSet1.getString("idMaterie");
                }
                LocalDate date = data.getValue();
                preparedStatement2 = connection.prepareStatement("CALL adaugaActivitate(?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement2.setString(1, tipactiv.getValue());
                preparedStatement2.setString(2,date.toString());
                preparedStatement2.setString(3,min.getText());
                preparedStatement2.setString(4,max.getText());
                preparedStatement2.setString(5,durata.getText());
                preparedStatement2.setString(6,frecv.getText());
                preparedStatement2.setString(7,idMaterie);
                preparedStatement2.setString(8,idProfesor);
                preparedStatement2.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Profesorul a fost alocat cu succes!");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
