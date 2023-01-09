package dbproject.profesor;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DatePersonaleProfesorPovController implements Initializable {
    @FXML
    private Label nrContract_profesor;
    @FXML
    private Label Nume_profesor;
    @FXML
    private Label Cnp_profesor;
    @FXML
    private Label Adresa_profesor;
    @FXML
    private Label Prenume_profesor;
    @FXML
    private Label Iban_profesor;
    @FXML
    private Label Tel_profesor;
    @FXML
    private Label Email_profesor;
    @FXML
    private Button return_back_profesor;

    private static String username_prof;

    public static void setUsername_prof(String username){
        username_prof = username;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM profesor WHERE email = ?");
            preparedStatement.setString(1, username_prof);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String cnp_temp = resultSet.getString("CNP");
                String nume_temp = resultSet.getString("numeProfesor");
                String prenumeAdmin_temp = resultSet.getString("prenumeProfesor");
                String adresa_temp = resultSet.getString("adresa");
                String tel_temp = resultSet.getString("nrTelefon");
                String email_temp = resultSet.getString("email");
                String IBAN_temp = resultSet.getString("IBAN");
                String nrContract_temp = resultSet.getString("nrContract");
                setInfo(cnp_temp,nume_temp, prenumeAdmin_temp, adresa_temp, tel_temp, email_temp, IBAN_temp, nrContract_temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return_back_profesor.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "ProfesorPanel.fxml", "Welcome!", 600, 473);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setInfo(String cnp, String nume, String prenume, String adresa, String tel, String email, String iban, String nrContract) {
        Nume_profesor.setText(nume);
        Prenume_profesor.setText(prenume);
        Tel_profesor.setText(tel);
        Adresa_profesor.setText(adresa);
        Iban_profesor.setText(iban);
        Cnp_profesor.setText(cnp);
        Email_profesor.setText(email);
        this.nrContract_profesor.setText(nrContract);
    }
}
