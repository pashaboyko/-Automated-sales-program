package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.pojo.Food;
import sample.pojo.Tech;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AddFood {
    public int countId2=5;

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
    private Button addButton;

    @FXML
    private Button canceButton;
    @FXML
    private DatePicker dateId;

    @FXML
    private Label idFail;


    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            if (priceField.getText().isEmpty() || nameField.getText().isEmpty() ||  barcodeField.getText().isEmpty()||priceField.getText().isEmpty()) {
                idFail.setText("Please fill in all required fields");

            }
            else {
                try {
                    Double d1 = new Double(priceField.getText());
                    Connectionn b= new Connectionn();
                    if(b.barcodeCheck(barcodeField.getText())){
                        idFail.setText("Please new barcode");
                        barcodeField.clear();
                    }
                    else {
                    LocalDate localDate = dateId.getValue();
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                    //Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                    Date date = new Date();


                    int year  = localDate.getYear();
                    int month = localDate.getMonthValue();
                    int day   = localDate.getDayOfMonth();



                    System.out.println(dateId.getDayCellFactory());

                    Food a = new Food(countId2, nameField.getText(), barcodeField.getText(), d1, "0.jpg", day, month, year);
                    b.addFood(a);
                    addButton.getScene().getWindow().hide();
                    countId2++;
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
