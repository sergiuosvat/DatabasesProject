package dbproject.student;

import dbproject.DBUtils;
import dbproject.utils.GrupStudiu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class GrupStudiuView implements Initializable {
    @FXML
    private Button back;
    @FXML
    private TableView<GrupStudiu> tableView;
    @FXML
    private TableColumn<GrupStudiu, String> dataGrup;
    @FXML
    private TableColumn<GrupStudiu, String> nrCrt;
    @FXML
    private TableColumn<GrupStudiu, String> max;
    @FXML
    private TableColumn<GrupStudiu, String> materia;

    private static String idStudent;

    public static void setIdStudent(String idstudent)
    {
        idStudent = idstudent;
    }

    ObservableList<GrupStudiu> content = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "StudentPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dataGrup.setCellValueFactory(f -> f.getValue().dataProperty());
        nrCrt.setCellValueFactory(f -> f.getValue().nrCrtProperty());
        materia.setCellValueFactory(f -> f.getValue().materieProperty());
        max.setCellValueFactory(f -> f.getValue().maxProperty());

        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement2;
        PreparedStatement preparedStatement1;
        ResultSet resultSet;
        ResultSet resultSet2;
        ResultSet resultSet1;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement2 = connection.prepareStatement("SELECT * FROM inscrieregrupstudiu WHERE idStudent = ?");
            preparedStatement2.setString(1,idStudent);
            resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                String idGrup = resultSet2.getString("idGrup");
                preparedStatement = connection.prepareStatement("SELECT * FROM grupstudiu where idGrup = ?");
                preparedStatement.setString(1, idGrup);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    GrupStudiu grupStudiu = new GrupStudiu();
                    grupStudiu.setData(resultSet.getString("dataGrup"));
                    grupStudiu.setMax(resultSet.getString("nrMaxParticipanti"));
                    grupStudiu.setNrCrt(resultSet.getString("nrCrtParticipanti"));
                    String idMaterie = resultSet.getString("idMaterie");
                    preparedStatement1 = connection.prepareStatement("SELECT * from materie where idMaterie = ?");
                    preparedStatement1.setString(1,idMaterie);
                    resultSet1 = preparedStatement1.executeQuery();
                    if (resultSet1.next())
                    {
                        grupStudiu.setMaterie(resultSet1.getString("numeMaterie"));
                    }
                    content.add(grupStudiu);
                }
                tableView.setItems(content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
