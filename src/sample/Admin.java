package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addTovar;

    @FXML
    private Button deleteTovar;
    @FXML
    private Button backButton;

    @FXML
    private TextField adminCode;



    @FXML
    void initialize() {
String f;
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Store");

            primaryStage_2.setScene(new Scene(root));
            primaryStage_2.setMaximized(true);
            primaryStage_2.setResizable(false);
            primaryStage_2.show();
        });

        addTovar.setOnAction(event -> {
Connectionn admin=new Connectionn();
if(admin.adminCheck(adminCode.getText())) {
    addTovar.getScene().getWindow().hide();
    Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource("typeOfTovar.fxml"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    Stage primaryStage_2 = new Stage();
    primaryStage_2.setTitle("Add product");

    primaryStage_2.setScene(new Scene(root));

    primaryStage_2.show();
}
});

        deleteTovar.setOnAction(event -> {
            Connectionn admin=new Connectionn();
            if(admin.adminCheck(adminCode.getText())) {
                deleteTovar.getScene().getWindow().hide();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("deleteTovar.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage primaryStage_2 = new Stage();
                primaryStage_2.setTitle("Удаление Товара");

                primaryStage_2.setScene(new Scene(root));

                primaryStage_2.show();
            }
            });








    }
}