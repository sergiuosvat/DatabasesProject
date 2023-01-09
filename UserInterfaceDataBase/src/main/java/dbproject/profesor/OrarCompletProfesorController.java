package dbproject.profesor;

import dbproject.Activitate2;
import dbproject.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OrarCompletProfesorController implements Initializable {
    @FXML
    private Button back;
    @FXML
    private TableView<Activitate2> tableView;

    @FXML
    private TableColumn<Activitate2, String> tipActivitate;
    @FXML
    private TableColumn<Activitate2, String> dataActivitate;

    @FXML
    private TableColumn<Activitate2, String> nrCrtStudenti;

    @FXML
    private TableColumn<Activitate2, String> durataOre;
    @FXML
    private TableColumn<Activitate2, String> frecventa;
    @FXML
    private TableColumn<Activitate2, String> materie;
    @FXML
    private Button download;

    private static String idProfesor;

    public static void setIdProfesorPov(String idprof)
    {
        idProfesor = idprof;
    }

    ObservableList<Activitate2> content = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event, "ProfesorPanel.fxml", "Welcome!", 600,473);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        tipActivitate.setCellValueFactory(f -> f.getValue().tipProperty());
        dataActivitate.setCellValueFactory(f -> f.getValue().dataProperty());
        nrCrtStudenti.setCellValueFactory(f -> f.getValue().nrCrtProperty());
        durataOre.setCellValueFactory(f -> f.getValue().durataProperty());
        frecventa.setCellValueFactory(f->f.getValue().frecventaProperty());
        materie.setCellValueFactory(f -> f.getValue().MaterieProperty());

        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement2;
        ResultSet resultSet;
        ResultSet resultSet2;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement2 = connection.prepareStatement("SELECT * FROM activitate WHERE idProfesor = ?");
            preparedStatement2.setString(1,idProfesor);
            resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                String idMaterie = resultSet2.getString("idMaterie");
                preparedStatement = connection.prepareStatement("SELECT * FROM materie where idMaterie = ?");
                preparedStatement.setString(1, idMaterie);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    Activitate2 activitate = new Activitate2();
                    activitate.setTip(resultSet2.getString("tipActivitate"));
                    activitate.setData(resultSet2.getString("dataActivitate"));
                    activitate.setNrCrt(resultSet2.getString("nrCrtStudenti"));
                    activitate.setDurata(resultSet2.getString("durataOre"));
                    activitate.setFrecventa(resultSet2.getString("frecventa"));
                    activitate.setMaterie(resultSet.getString("numeMaterie"));
                    content.add(activitate);
                }
                tableView.setItems(content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        download.setOnAction(event -> {
            FileChooser saveFile = new FileChooser();
            saveFile.setTitle("Save File");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PNG files", "*.png");
            saveFile.getExtensionFilters().add(extensionFilter);
            File file = saveFile.showSaveDialog(null);
            WritableImage writableImage = tableView.snapshot(null,null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage,null), "png", file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
