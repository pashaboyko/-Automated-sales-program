package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class Main  {



/*

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("chooseColor.fxml"));
        primaryStage.setTitle("Покупка без касира");
        primaryStage.setScene(new Scene(root));

        DialogPeer frame;

        primaryStage.show();
        String printData = "Joj";



        Connectionn b=new Connectionn();
        //b.OutInfo();
        //b.addtoProgramm();


    }

*/


    public static void main(String[] args) {
        int MAX_CONNECTIONS = 3;

        try{

            ArrayList<Socket> sockets = new ArrayList<>();
            for (int i=0 ; i<MAX_CONNECTIONS; i++ )
            sockets.add(new Socket("localhost",8687));

            for (int i=0 ; i<MAX_CONNECTIONS; i++ ) {
                DataOutputStream dout = new DataOutputStream(sockets.get(i).getOutputStream());

                System.out.println("a"+i);

                dout.writeUTF("Hello");
                dout.flush();



            }

            for (int i=0 ; i<MAX_CONNECTIONS; i++ ) {
                DataInputStream din = new DataInputStream(sockets.get(i).getInputStream());
                System.out.println("send first mess");
                String str = din.readUTF();//in.readLine();
                System.out.println("b"+i);
                System.out.println("Message" + str);


            }
            for (int i=0 ; i<MAX_CONNECTIONS; i++ ) {
            }
        }

        catch(Exception e){
            e.printStackTrace();}


    }


}
