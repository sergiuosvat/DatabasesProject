package dbproject.profesor;

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

public class OrarAstaziProfesorController implements Initializable {
    @FXML
    private Button back;
    @FXML
    private Button download;
    @FXML
    private TableView<Orar> tableView;

    @FXML
    private TableColumn<Orar, String> activitate;
    @FXML
    private TableColumn<Orar, String> data;

    ObservableList<Orar> content = FXCollections.observableArrayList();

    private static String idProfesor;

    public static void setIdProfesor(String idProfesor1)
    {
        idProfesor = idProfesor1;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activitate.setCellValueFactory(f-> f.getValue().activitateProperty());
        data.setCellValueFactory(f->f.getValue().dataProperty());
        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement1;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("CALL programProfesor(?,?,?,?,?)");
            preparedStatement.setString(1,idProfesor);
            preparedStatement.setString(2,"DA");
            preparedStatement.setString(3,"NU");
            preparedStatement.setString(4,"NU");
            preparedStatement.setString(5,"NU");
            preparedStatement.executeQuery();

            preparedStatement1 = connection.prepareStatement("SELECT * from tabelZiCurentaProf");
            resultSet = preparedStatement1.executeQuery();
            while (resultSet.next())
            {
                Orar orar = new Orar();
                orar.setActivitate(resultSet.getString("Activitate"));
                orar.setData(resultSet.getString("Ora"));
                content.add(orar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableView.setItems(content);

        back.setOnAction(event -> {
            try {
                DBUtils.changeScene(event,"ProfesorPanel.fxml", "Welcome!", 600, 473);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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
