package dbproject.student;

import dbproject.DBUtils;
import dbproject.utils.Orar;
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

public class OrarController implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button download;
    @FXML
    private TableView<Orar> tableView;
    @FXML
    private TableColumn<Orar, String> student;
    @FXML
    private TableColumn<Orar, String> activitate;
    @FXML
    private TableColumn<Orar, String> data;
    @FXML
    private TableColumn<Orar, String> materie;

    private static String idStudent;
    private String numeMaterie;
    public static void setId(String name1)
    {
        idStudent = name1;
    }

    ObservableList<Orar> content = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event,"StudentPanel.fxml", "Welcome!", 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        student.setCellValueFactory(f -> f.getValue().durataProperty());
        activitate.setCellValueFactory(f-> f.getValue().activitateProperty());
        data.setCellValueFactory(f->f.getValue().dataProperty());
        materie.setCellValueFactory(f->f.getValue().materieProperty());
        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement1;
        PreparedStatement preparedStatement2;
        ResultSet resultSet;
        ResultSet resultSet1;
        ResultSet resultSet2;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM inscriereactivitate where idStudent = ?");
            preparedStatement.setString(1, idStudent);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String idActiv = resultSet.getString("idActivitate");
                preparedStatement1 = connection.prepareStatement("SELECT  * from activitate where idActivitate = ?");
                preparedStatement1.setString(1,idActiv);
                resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next())
                {
                    String idMaterie = resultSet1.getString("idMaterie");
                    preparedStatement2 = connection.prepareStatement("SELECT * from materie where idMaterie = ?");
                    preparedStatement2.setString(1,idMaterie);
                    resultSet2 = preparedStatement2.executeQuery();
                    if (resultSet2.next())
                    {
                        numeMaterie = resultSet2.getString("numeMaterie");
                    }
                    Orar orar = new Orar();
                    orar.setDurata(resultSet1.getString("durataOre"));
                    orar.setActivitate(resultSet1.getString("tipActivitate"));
                    orar.setData(resultSet1.getString("dataActivitate"));
                    orar.setMaterie(numeMaterie);
                    content.add(orar);
                }
            }
            tableView.setItems(content);
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
