package sample;

import com.sun.javadoc.SourcePosition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.pojo.Clothes;
import sample.pojo.Food;
import sample.pojo.Product;
import sample.pojo.Tech;
import sun.net.SocksProxy;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.soap.SOAPPart;
import java.io.IOException;

import static sample.Listfortovar.buyprd;
import static sample.Listfortovar.product;

public class Controller {
    private static final String POST_URL_DEMO = " http://5fdd894c.ngrok.io/barcodeall";
    private static final String POST_PARAMS_DEMO = "barcode=644832819197";

    public static double PricesforBuy=0; ;

    public ObservableList<Product_value> productsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Product_value> tableUsers;

    @FXML
    private Button printButton;

    @FXML
    private TableColumn<Product_value, Integer> idColumn;

    @FXML
    private TableColumn<Product_value, String> nameColumn;

    @FXML
    private TableColumn<Product_value, String> barcodeColumn;


    @FXML
    private TableColumn<Product_value, String> priceColumn;
    @FXML
    private TextField BarcodeText;
    @FXML
    private Label labPrice;

    public double checkAll(){

        double c = 0;
        for (int i = 0; i < productsData.size(); i++) {
            c=c+productsData.get(i).getPrice();
        }
        return c;
    }

    @FXML
    private void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<Product_value, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("name"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("barcode"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product_value, String>("price"));


        tableUsers.setItems(productsData);
        printButton.setOnAction(event -> {

          /* Printer printer = Printer.getDefaultPrinter();
            Stage dialogStage = new Stage(StageStyle.DECORATED);
            PrinterJob job = PrinterJob.createPrinterJob(printer);
            if (job != null) {

                tableUsers.setScaleX(0.60);
                tableUsers.setScaleY(0.60);
                tableUsers.setTranslateX(-720);
                tableUsers.setTranslateY(-100);
                boolean success = (job.printPage(tableUsers));
                if (success) {
                    job.endJob();
                }
                tableUsers.setTranslateX(0);
                tableUsers.setTranslateY(0);
                tableUsers.setScaleX(1.0);
                tableUsers.setScaleY(1.0);
            }
            */
            printButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("good.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage primaryStage_2 = new Stage();
            primaryStage_2.setTitle("Thanks");

            primaryStage_2.setScene(new Scene(root));
            primaryStage_2.setMaximized(true);
            primaryStage_2.setResizable(false);
            primaryStage_2.show();
            Bill_form Formbill=new Bill_form();
            Formbill.startPrint();
            productsData.clear();
            buyprd.clear();

        });




        BarcodeText.setOnAction(event -> {

            try {
                String params = String.format("barcode=%s", BarcodeText.getText());

                System.out.println(
                HttpURLConnectionExample.sendPOST(POST_URL_DEMO, params));
                Product_value product = new Product_value(HttpURLConnectionExample.sendPOST(POST_URL_DEMO, params));
                System.out.println(product);
                productsData.add(product);
                Listfortovar.product.add(product);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }




            /*Listfortovar.clothess.clear();
            Listfortovar.foods.clear();
            Listfortovar.techs.clear();
            Connectionn b = new Connectionn();
            b.addtoProgramm();
            for (int i = 0; i < Listfortovar.techs.size(); i++) {
                if (Listfortovar.techs.get(i).getBarcode().equals(BarcodeText.getText())) {
                    productsData.add((Listfortovar.techs.get(i)));
                    buyprd.add((Listfortovar.techs.get(i)));

                }
            }
            for (int i = 0; i < Listfortovar.clothess.size(); i++) {
                if (Listfortovar.clothess.get(i).getBarcode().equals(BarcodeText.getText())) {
                    productsData.add((Listfortovar.clothess.get(i)));
                    buyprd.add((Listfortovar.clothess.get(i)));

                }
            }
            for (int i = 0; i < Listfortovar.foods.size(); i++) {
                if (Listfortovar.foods.get(i).getBarcode().equals(BarcodeText.getText())) {
                    productsData.add((Listfortovar.foods.get(i)));
                    buyprd.add((Listfortovar.foods.get(i)));

                }
            }


             */
            labPrice.setText(String.format("%s", checkAll()));
            Main.con.sendTOServer();
            Main.con.listenServer();
            BarcodeText.clear();


        });



        tableUsers.setRowFactory(tv -> {
            TableRow<Product_value> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product_value rowData = row.getItem();
                    System.out.println(rowData.getBarcode());
                    showPersonEditDialog(rowData);
                }
            });
            return row;
        });
    }
    public boolean showPersonEditDialog(Product_value product) {
        try {
            System.out.println(product.getBarcode());
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            System.out.println(product.getClass().getName());
            if(product.getClass().getName() == "sample.pojo.Clothes"){
                System.out.println(product.getClass().getName());
            loader.setLocation(getClass().getResource("infoAboutClothes.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Info about clothes");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            InfoAboutClothes controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.setProduct((Clothes)product);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return true;}
        else {
                if (product.getClass().getName() == "sample.pojo.Food") {
                    System.out.println(product.getClass().getName());
                    loader.setLocation(getClass().getResource("infoAboutFood.fxml"));

                    AnchorPane page = (AnchorPane) loader.load();
                    // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Info about food");
                    dialogStage.initModality(Modality.WINDOW_MODAL);

                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    // Set the person into the controller.
                    InfoAboutFood controller = loader.getController();
                    controller.setDialogStage(dialogStage);
                    //controller.setProduct((Food) product);
                    // Show the dialog and wait until the user closes it
                    dialogStage.showAndWait();
                    return true;
                } else {
                    if (product.getClass().getName() == "sample.pojo.Tech") {
                        System.out.println(product.getClass().getName());
                        loader.setLocation(getClass().getResource("infoAboutTech.fxml"));

                        AnchorPane page = (AnchorPane) loader.load();
                        // Create the dialog Stage.
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Info about Tech");
                        dialogStage.initModality(Modality.WINDOW_MODAL);

                        Scene scene = new Scene(page);
                        dialogStage.setScene(scene);

                        // Set the person into the controller.
                        InfoAboutTech controller = loader.getController();
                        controller.setDialogStage(dialogStage);
                        //controller.setProduct((Tech) product);
                        // Show the dialog and wait until the user closes it
                        dialogStage.showAndWait();
                        return true;
                        } else return false;
                    }
                }
            }

         catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}