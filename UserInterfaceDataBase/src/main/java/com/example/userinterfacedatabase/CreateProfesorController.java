package com.example.userinterfacedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateProfesorController implements Initializable {
    @FXML
    private Button backProf;

    @FXML
    private TextField cnp_prof;
    @FXML
    private TextField nume_prof;
    @FXML
    private TextField prenume_prof;
    @FXML
    private TextField adresa_prof;
    @FXML
    private TextField tel_prof;
    @FXML
    private TextField iban_prof;
    @FXML
    private TextField email_prof;
    @FXML
    private TextField contract_prof;
    @FXML
    private Button saveProf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveProf.setOnAction(event -> {
            try {
                DBUtils.CreateProfesor(cnp_prof.getText(), nume_prof.getText(), prenume_prof.getText(), adresa_prof.getText(), tel_prof.getText(), email_prof.getText(), iban_prof.getText(), contract_prof.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        backProf.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
