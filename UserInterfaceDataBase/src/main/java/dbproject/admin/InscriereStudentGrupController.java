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
    private String idStudent;
    ObservableList<String> content = FXCollections.observableArrayList();
    private int total;
    private String id;
    private String email;

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
        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement1;
        PreparedStatement preparedStatement2;
        ResultSet resultSet;
        ResultSet resultSet1;
        ResultSet resultSet2;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT  COUNT(*) as total from student");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                total = resultSet.getInt("total");
            }
            for (int i = 1;i<=total;i++)
            {
                preparedStatement1 = connection.prepareStatement("SELECT * from student where idStudent = ?");
                preparedStatement1.setString(1, String.valueOf(i));
                resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next())
                {
                    id = resultSet1.getString("idStudent");
                    email = resultSet1.getString("email");
                }

                preparedStatement2 = connection.prepareStatement("SELECT idGrup from inscrieregrupstudiu where idStudent = ?");
                preparedStatement2.setString(1,id);
                resultSet2 = preparedStatement2.executeQuery();
                if (!resultSet2.next())
                {
                    content.add(email);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listView.setItems(content);
    }
}
