package sample;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import sample.pojo.Clothes;

import sample.pojo.Product;
import sample.pojo.Tech;


public class InfoAboutTech {
    private Stage dialogStage;
    private Product product;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField barcodeField;

    @FXML
    private ImageView imgSet;

    @FXML
    private TextField priceField;

    @FXML
    private TextField warrantyField;

    @FXML
    private TextField nameField;

    @FXML
    private Button buttonOK;

    @FXML
    private TextField colorField;



    public void setProduct(Tech product) {
        this.product= product;

        nameField.setText(product.getName());
        barcodeField.setText(String.valueOf(product.getBarcode()));
        warrantyField.setText(String.valueOf(product.getWarranty()));
        priceField.setText(String.valueOf(product.getPrice()));
        colorField.setText(product.getColor());
        System.out.println(product.getPhoto());

        Image image = new Image(getClass().getResource(product.getPhoto()).toExternalForm());

        imgSet.setImage(image);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void initialize() {



        buttonOK.setOnAction(event -> {
            dialogStage.close();


        });


    }
}