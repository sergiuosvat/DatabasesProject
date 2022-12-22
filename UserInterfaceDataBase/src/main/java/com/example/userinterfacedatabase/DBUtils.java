package com.example.userinterfacedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {


    public static void changeScene(ActionEvent event, String fxmlFile, String title, int v, int v1) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, v, v1));
        stage.show();
    }

    public static void logInUser(ActionEvent event, String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE email = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                preparedStatement = connection.prepareStatement("SELECT * FROM profesor WHERE email = ?");
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE email = ?");
                    preparedStatement.setString(1, username);
                    resultSet = preparedStatement.executeQuery();
                    if (!resultSet.isBeforeFirst()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username-ul nu exista!");
                        alert.show();
                    } else {
                        changeScene(event, "StudentPanel.fxml", "Welcome!", 800, 441);
                    }
                } else {
                    changeScene(event, "ProfesorPanel.fxml", "Welcome!", 800, 441);
                }
            } else {
                DatePersonaleAdminController.setUsername(username);
                changeScene(event, "AdminPanel.fxml", "Welcome!", 600, 400);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void CreateStudent(String cnp, String nume, String prenume, String adresa, String tel , String email, String iban, String contract, String an, String serie, String grupa ) throws SQLException {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM student WHERE email = ?");
            psCheckUserExists.setString(1, email);
            resultSet = psCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Studentul exista deja!");
                alert.show();
            }
            else
            {
                psInsert = connection.prepareStatement("INSERT INTO student(CNP, numeStudent, prenumeStudent, adresa, nrTelefon, email, IBAN, nrContract, anDeStudiu, serieAn, grupaSerie) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1,cnp);
                psInsert.setString(2,nume);
                psInsert.setString(3,prenume);
                psInsert.setString(4,adresa);
                psInsert.setString(5,tel);
                psInsert.setString(6,email);
                psInsert.setString(7,iban);
                psInsert.setString(8,contract);
                psInsert.setString(9,an);
                psInsert.setString(10,serie);
                psInsert.setString(11,grupa);
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(psInsert!=null)
            {
                psInsert.close();
            }
            if(psCheckUserExists!=null)
            {
                psCheckUserExists.close();
            }
            if(resultSet!=null)
            {
                resultSet.close();
            }
            if(connection!=null)
            {
                connection.close();
            }
        }
    }

}


