package dbproject.student;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GrupStudiuStudent implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private TextField nrMin;
    @FXML
    private TextField nrMax;
    @FXML
    private TextField numeMaterie;
    @FXML
    private DatePicker dataGrup;

    @FXML
    private TextField idGrup;
    @FXML
    private Button delete;
    @FXML
    private TextField profName;

    private String idMaterie;
    private String idProf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event,"StudentPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        save.setOnAction(event -> {
            String min = nrMin.getText();
            String max = nrMax.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            ResultSet resultSet;
            ResultSet resultSet1;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("SELECT * from materie where numeMaterie= ?");
                preparedStatement.setString(1, numeMaterie.getText());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    idMaterie = resultSet.getString("idMaterie");
                }
                preparedStatement2 = connection.prepareStatement("SELECT * from profesor where email = ?");
                preparedStatement2.setString(1,profName.getText());
                resultSet1 = preparedStatement2.executeQuery();
                if (resultSet1.next())
                {
                    idProf = resultSet1.getString("idProfesor");
                }
                preparedStatement1 = connection.prepareStatement("CALL adaugaGrupStudiu(?, ?, ?, ?, ?)");
                LocalDate myDate = dataGrup.getValue();
                preparedStatement1.setString(1,myDate.toString());
                preparedStatement1.setString(2,min);
                preparedStatement1.setString(3,max);
                preparedStatement1.setString(4,idMaterie);
                preparedStatement1.setString(5,idProf);
                preparedStatement1.executeQuery();
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
            String s = idGrup.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL eliminaGrupStudiu(?)");
                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati eliminat grupul cu succes!");
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
