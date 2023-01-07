package dbproject.profesor;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlocareProfesorPovController implements Initializable {
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
    private TextField materie;
    @FXML
    private DatePicker data;
    public static String idProfesor;
    private String idMaterie;

    public static void setIdProfesor(String idProfesor1)
    {
        idProfesor = idProfesor1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "ProfesorPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        tipactiv.getItems().addAll(arr);
        save.setOnAction(event -> {
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            ResultSet resultSet;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("SELECT  * from materie where numeMaterie = ?");
                preparedStatement.setString(1,materie.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                {
                    idMaterie = resultSet.getString("idMaterie");
                }
                LocalDate date = data.getValue();
                preparedStatement1 = connection.prepareStatement("CALL adaugaActivitate(?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement1.setString(1, tipactiv.getValue());
                preparedStatement1.setString(2,date.toString());
                preparedStatement1.setString(3,min.getText());
                preparedStatement1.setString(4,max.getText());
                preparedStatement1.setString(5,durata.getText());
                preparedStatement1.setString(6,frecv.getText());
                preparedStatement1.setString(7,idMaterie);
                preparedStatement1.setString(8,idProfesor);
                preparedStatement1.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati fost alocat cu succes!");
                alert.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
