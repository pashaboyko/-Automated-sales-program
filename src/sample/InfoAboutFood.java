package sample;




import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



import sample.pojo.Food;
import sample.pojo.Product;


public class InfoAboutFood {

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
    private TextField nameField;

    @FXML
    private TextField DateField;

    @FXML
    private Button buttonOK;


    public void setProduct(Food product) {
        this.product= product;

        nameField.setText(product.getName());
        barcodeField.setText(String.valueOf(product.getBarcode()));
        DateField.setText(String.valueOf(product.getDate().strd()));
        priceField.setText(String.valueOf(product.getPrice()));
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