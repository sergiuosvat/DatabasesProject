package com.example.userinterfacedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button log_out;

    @FXML
    private Button date_pers;

    @FXML
    private Button createStudent;

    @FXML
    private Button createProfesor;

    @FXML
    private Button datePersAng;

    @FXML
    private Button date_activ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log_out.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "LoginForm.fxml", "Login Form", 800, 441);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        date_pers.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DatePersonaleAdmin.fxml", "Date Personale", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        createStudent.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "CreateStudent.fxml", "Creaza Student", 600, 485);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        createProfesor.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "CreateProfesor.fxml", "Creaza Profesor", 600, 485);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        datePersAng.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DatePersAngSearch.fxml", "Cauta o persoana", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        date_activ.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "DateActivitati.fxml", "Date activitati", 931, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
