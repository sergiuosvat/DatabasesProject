package com.example.userinterfacedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, int v, int v1) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        root = loader.load();

        Stage stage = (Stage)( (Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, v, v1));
        stage.show();
    }

    public static void logInUser(ActionEvent event, String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE email = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst())
            {
                preparedStatement = connection.prepareStatement("SELECT * FROM profesor WHERE email = ?");
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst())
                {
                    preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE email = ?");
                    preparedStatement.setString(1, username);
                    resultSet = preparedStatement.executeQuery();
                    if(!resultSet.isBeforeFirst())
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Username-ul nu exista!");
                        alert.show();
                    }
                    else
                    {
                        changeScene(event, "StudentPanel.fxml", "Welcome!", 800, 441);
                    }
                }
                else
                {
                    changeScene(event,"ProfesorPanel.fxml", "Welcome!", 800, 441);
                }
            }
            else {
                changeScene(event,"AdminPanel.fxml", "Welcome!", 800, 441);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null)
            {
                resultSet.close();
            }
            if(preparedStatement != null)
            {
                preparedStatement.close();
            }
            if(connection != null)
            {
                connection.close();
            }
        }


    }
}
