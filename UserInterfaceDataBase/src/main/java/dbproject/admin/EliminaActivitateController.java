package dbproject.admin;

import dbproject.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EliminaActivitateController implements Initializable {

    @FXML
    private TextField idactiv;
    @FXML
    private Button back;
    @FXML
    private Button save;


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
            Connection connection;
            PreparedStatement preparedStatement;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
                preparedStatement = connection.prepareStatement("CALL eliminaActivitate(?)");
                preparedStatement.setString(1, idactiv.getText());
                preparedStatement.executeQuery();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Ati eliminat cu succes!");
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
