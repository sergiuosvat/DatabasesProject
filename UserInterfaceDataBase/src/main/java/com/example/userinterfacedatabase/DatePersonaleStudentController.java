package com.example.userinterfacedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DatePersonaleStudentController implements Initializable {
    @FXML
    private Label nrContract;
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
    private Label an;

    @FXML
    private Label serie;
    @FXML
    private Label grupa;
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
            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE email = ?");
            preparedStatement.setString(1, Username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String cnp_temp = resultSet.getString("CNP");
                String nume_temp = resultSet.getString("numeStudent");
                String prenumeAdmin_temp = resultSet.getString("prenumeStudent");
                String adresa_temp = resultSet.getString("adresa");
                String tel_temp = resultSet.getString("nrTelefon");
                String email_temp = resultSet.getString("email");
                String IBAN_temp = resultSet.getString("IBAN");
                String nrContract_temp = resultSet.getString("nrContract");
                String an =resultSet.getString("anDeStudiu");
                String serie = resultSet.getString("serieAn");
                String grupa =resultSet.getString("grupaSerie");
                setInfoStud(cnp_temp,nume_temp, prenumeAdmin_temp, adresa_temp, tel_temp, email_temp, IBAN_temp, nrContract_temp,serie,an, grupa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return_back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DatePersAngSearch.fxml", "Cauta o persoana", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setInfoStud(String cnp, String nume, String prenume, String adresa, String tel, String email, String iban, String nrContract, String serie, String an, String grupa) {
        this.Nume.setText(nume);
        this.Prenume.setText(prenume);
        this.Tel.setText(tel);
        this.Adresa.setText(adresa);
        this.Iban.setText(iban);
        this.Cnp.setText(cnp);
        this.Email.setText(email);
        this.nrContract.setText(nrContract);
        this.serie.setText(serie);
        this.an.setText(an);
        this.grupa.setText(grupa);

    }
}
