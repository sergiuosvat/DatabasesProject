package dbproject.admin;

import dbproject.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InscriereStudentGrupController implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private TextField mail;
    @FXML
    private TextField idGrup;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button suggestions;
    private String idStudent;
    ObservableList<String> content = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "AdminPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        save.setOnAction(event -> {
            String s = idGrup.getText();
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            ResultSet resultSet;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement1 = connection.prepareStatement("SELECT * from student where email = ?");
                preparedStatement1.setString(1,mail.getText());
                resultSet = preparedStatement1.executeQuery();
                if (resultSet.next()) {
                    idStudent = resultSet.getString("idStudent");
                }
                preparedStatement = connection.prepareStatement("CALL adaugaParticipantGrupStudiu(?, ?)");
                preparedStatement.setString(1, idStudent);
                preparedStatement.setString(2,s);
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati adaugat cu succes!");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Eroare!");
                alert.show();
                throw new RuntimeException(e);
            }
        });
        suggestions.setOnAction(event -> {
            Connection connection;
            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1;
            ResultSet resultSet;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL sugestiiParticipantiGrupStudiu(?)");
                preparedStatement.setString(1,idGrup.getText());
                preparedStatement.executeQuery();
                preparedStatement1 = connection.prepareStatement("SELECT * from sugestii");
                resultSet = preparedStatement1.executeQuery();
                while (resultSet.next())
                {
                    String email = resultSet.getString("Student");
                    content.add(email);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            listView.setItems(content);
        });

    }
}
