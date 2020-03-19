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

public class DeleteTovar {

    public static double PricesforBuy = 0;
    ;
    private Connectionn b = new Connectionn();
    public ObservableList<Product> productsData = FXCollections.observableArrayList();

    public void addTovars() {
        Listfortovar.clothess.clear();
        Listfortovar.foods.clear();
        Listfortovar.techs.clear();
        productsData.clear();


        Connectionn b = new Connectionn();
        b.addtoProgramm();
        for (int i = 0; i < Listfortovar.techs.size(); i++) {
            productsData.add((Listfortovar.techs.get(i)));
        }
        for (int i = 0; i < Listfortovar.clothess.size(); i++) {

            productsData.add((Listfortovar.clothess.get(i)));
        }
        for (int i = 0; i < Listfortovar.foods.size(); i++) {
            productsData.add((Listfortovar.foods.get(i)));
        }
    }


    @FXML
    private TableView<Product> tableUsers;


    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> barcodeColumn;


    @FXML
    private TableColumn<Product, String> priceColumn;



    @FXML
    private void initialize() {

        addTovars();
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
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



        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("barcode"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));


        tableUsers.setItems(productsData);









        tableUsers.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Product rowData = row.getItem();
                    System.out.println(rowData.getBarcode());
                    deleteProductType(rowData);
                }
            });
            return row;
        });
    }
    public boolean deleteProductType(Product product) {
            if(product.getClass().getName() == "sample.pojo.Clothes"){
                b.deleteElement(1,product.getId());
                addTovars();
                return true;}
            else {
                if (product.getClass().getName() == "sample.pojo.Food") {
                    b.deleteElement(2,product.getId());
                    addTovars();
                    return true;
                } else {
                    if (product.getClass().getName() == "sample.pojo.Tech") {
                        b.deleteElement(3,product.getId());
                        addTovars();
                        return true;
                    } else return false;

                }

            }


    }

}