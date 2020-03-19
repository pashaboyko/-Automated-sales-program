package sample;

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
import sample.pojo.Clothes;
import sample.pojo.Food;
import sample.pojo.Product;
import sample.pojo.Tech;

import java.io.IOException;

import static sample.Listfortovar.buyprd;

public class Controller1 {

    public static double PricesforBuy=0; ;

    public ObservableList<Product> productsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> tableUsers;

    @FXML
    private Button printButton;
    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> barcodeColumn;


    @FXML
    private TableColumn<Product, String> priceColumn;
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

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("barcode"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));


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
                root = FXMLLoader.load(getClass().getResource("good1.fxml"));
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
            Listfortovar.clothess.clear();
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
            labPrice.setText(String.format("%s", checkAll()));
            BarcodeText.clear();


        });



        tableUsers.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product rowData = row.getItem();
                    System.out.println(rowData.getBarcode());
                    showPersonEditDialog(rowData);
                }
            });
            return row;
        });
    }
    public boolean showPersonEditDialog(Product product) {
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
                controller.setProduct((Clothes)product);
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
                    controller.setProduct((Food) product);
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
                        controller.setProduct((Tech) product);
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