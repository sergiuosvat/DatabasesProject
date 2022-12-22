package com.example.userinterfacedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DatePersonaleAdminController extends AdminController implements Initializable {
    @FXML
    private Label Nume;
    @FXML
    private Label Cnp;
    @FXML
    private Label Adresa;
    @FXML
    private Label Prenume;
    @FXML
    private Label Iban;
    @FXML
    private Label Tel;
    @FXML
    private Label Email;
    @FXML
    private Button return_back;
    private static String Username;


    public static void setUsername(String username) {
        Username = username;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE email = ?");
            preparedStatement.setString(1, Username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String cnp_temp = resultSet.getString("CNP");
                String nume_temp = resultSet.getString("numeAdmin");
                String prenumeAdmin_temp = resultSet.getString("prenumeAdmin");
                String adresa_temp = resultSet.getString("adresa");
                String tel_temp = resultSet.getString("nrTelefon");
                String email_temp = resultSet.getString("email");
                String IBAN_temp = resultSet.getString("IBAN");
                setInfo(cnp_temp,nume_temp, prenumeAdmin_temp, adresa_temp, tel_temp, email_temp, IBAN_temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return_back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setInfo(String cnp, String nume, String prenume, String adresa, String tel, String email, String iban) {
        Nume.setText(nume);
        Prenume.setText(prenume);
        Tel.setText(tel);
        Adresa.setText(adresa);
        Iban.setText(iban);
        Cnp.setText(cnp);
        Email.setText(email);
    }

}
