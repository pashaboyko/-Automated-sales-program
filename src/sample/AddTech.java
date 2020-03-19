package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.pojo.Tech;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTech {

    public int c=5;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField barcodeField;

    @FXML
    private TextField priceField;



    @FXML
    private TextField nameField;

    @FXML
    private Button canceButton;
    @FXML
    private Button addButton;

    @FXML
    private TextField colorField;

    @FXML
    private TextField sizeField;
    @FXML
    private Label idFail;


    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Connectionn b=new Connectionn();
            if (priceField.getText().isEmpty() || sizeField.getText().isEmpty() || nameField.getText().isEmpty() || colorField.getText().isEmpty() || barcodeField.getText().isEmpty()) {
                idFail.setText("Please fill in all required fields");
            }
            else{
                try {
                    Double d1 = new Double(priceField.getText());
                    try {
                        if(b.barcodeCheck(barcodeField.getText())) {
                            idFail.setText("Please new barcode");
                        }
                        else{

                        Integer d2 = new Integer(sizeField.getText());

                        Tech a=new Tech(c, nameField.getText(),barcodeField.getText(),d1,"0.jpg",d2,colorField.getText());
                        b.addTech(a);
                        c++;
                        addButton.getScene().getWindow().hide();
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage primaryStage_2 = new Stage();
                        primaryStage_2.setTitle("Администратор");
                        primaryStage_2.setScene(new Scene(root));
                        primaryStage_2.setMaximized(true);
                        primaryStage_2.setResizable(false);
                        primaryStage_2.show();

                    } }catch (NumberFormatException e) {
                        sizeField.clear();
                        idFail.setText("Warranty is number!!");
                    }
                } catch (NumberFormatException e) {
                    priceField.clear();
                    idFail.setText("Price is number!!");
                }

            }
        });
        canceButton.setOnAction(event -> {
            canceButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Администратор");

            primaryStage_2.setScene(new Scene(root));
            primaryStage_2.setMaximized(true);
            primaryStage_2.setResizable(false);
            primaryStage_2.show();
        });
    }
}
