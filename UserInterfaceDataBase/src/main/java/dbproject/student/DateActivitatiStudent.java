package dbproject.student;

import dbproject.Activitate;
import dbproject.DBUtils;
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

public class DateActivitatiStudent implements Initializable {
    @FXML
    private Button back;
    @FXML
    private TableView<Activitate> tableView;
    @FXML
    private TableColumn<Activitate, String> idActivitate;
    @FXML
    private TableColumn<Activitate, String> tipActivitate;
    @FXML
    private TableColumn<Activitate, String> dataActivitate;
    @FXML
    private TableColumn<Activitate, String> nrMinimStudenti;
    @FXML
    private TableColumn<Activitate, String> nrCrtStudenti;
    @FXML
    private TableColumn<Activitate, String> nrMaxStudenti;
    @FXML
    private TableColumn<Activitate, String> durataOre;
    @FXML
    private TableColumn<Activitate, String> frecventa;
    @FXML
    private TableColumn<Activitate, String> idMaterie;
    @FXML
    private TableColumn<Activitate, String> idProfesor;

    private static String idStudent;

    public static void setIdStudent(String idstudent)
    {
        idStudent = idstudent;
    }

    ObservableList<Activitate> content = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "StudentPanel.fxml", "Welcome!", 600,400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        idActivitate.setCellValueFactory(f -> f.getValue().idProperty());
        tipActivitate.setCellValueFactory(f -> f.getValue().tipProperty());
        dataActivitate.setCellValueFactory(f -> f.getValue().dataProperty());
        nrMinimStudenti.setCellValueFactory(f -> f.getValue().nrMinProperty());
        nrCrtStudenti.setCellValueFactory(f -> f.getValue().nrCrtProperty());
        nrMaxStudenti.setCellValueFactory(f -> f.getValue().nrMaxProperty());
        durataOre.setCellValueFactory(f -> f.getValue().durataProperty());
        frecventa.setCellValueFactory(f->f.getValue().frecventaProperty());
        idMaterie.setCellValueFactory(f -> f.getValue().idMaterieProperty());
        idProfesor.setCellValueFactory(f -> f.getValue().idProfesorProperty());

        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement2;
        ResultSet resultSet;
        ResultSet resultSet2;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement2 = connection.prepareStatement("SELECT idActivitate FROM inscriereactivitate WHERE idStudent = ?");
            preparedStatement2.setString(1,idStudent);
            resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                String idActiv = resultSet2.getString("idActivitate");
                preparedStatement = connection.prepareStatement("SELECT * FROM activitate where idActivitate = ?");
                preparedStatement.setString(1, idActiv);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Activitate activitate = new Activitate();
                    activitate.setId(resultSet.getString("idActivitate"));
                    activitate.setTip(resultSet.getString("tipActivitate"));
                    activitate.setData(resultSet.getString("dataActivitate"));
                    activitate.setNrMin(resultSet.getString("nrMinStudenti"));
                    activitate.setNrCrt(resultSet.getString("nrCrtStudenti"));
                    activitate.setNrMax(resultSet.getString("nrMaxStudenti"));
                    activitate.setDurata(resultSet.getString("durataOre"));
                    activitate.setFrecventa(resultSet.getString("frecventa"));
                    activitate.setIdMaterie(resultSet.getString("idMaterie"));
                    activitate.setIdProfesor(resultSet.getString("idProfesor"));
                    content.add(activitate);
                }
                tableView.setItems(content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
