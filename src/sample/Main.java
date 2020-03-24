package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.peer.DialogPeer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


import static javafx.application.Application.launch;


public class Main extends Application {

    public static ConToServer con = new ConToServer();


    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("Поисковик недвижимости");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        DialogPeer frame;
        primaryStage.setResizable(false);
        primaryStage.show();


        Connectionn b=new Connectionn();
        b.OutInfo();
        b.addtoProgramm();


    }




    public static void main(String[] args) {
        launch(args);
    }
}