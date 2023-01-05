package dbproject.student;

import dbproject.DBUtils;
import dbproject.Orar;
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
        student.setCellValueFactory(f -> f.getValue().studentProperty());
        activitate.setCellValueFactory(f-> f.getValue().activitateProperty());
        data.setCellValueFactory(f->f.getValue().dataProperty());
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/platformastudiu", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM orarstudenti");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Orar orar = new Orar();
                orar.setStudent(resultSet.getString("Student"));
                orar.setActivitate(resultSet.getString("Activitate"));
                orar.setData(resultSet.getString("Data"));
                content.add(orar);
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
