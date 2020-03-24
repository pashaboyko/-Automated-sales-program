package sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class ConToServer {
    private String host = "localhost";
    private Integer port = 8691;
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;


    public ConToServer() {
        try {
            socket = new Socket(host, port);
            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendTOServer() {

        try {
            dout.writeUTF("Hello");
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String listenServer() {
        String str="";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(din));
            //JSONObject json = new JSONObject(in.readLine());
            String noReset = "Could not reset.";
            String result ="";
            String output = null;
            System.out.println(in.readLine());
            while ((result = in.readLine()) != null) {
                output = result.replace("[", "").replace("]", "");
                System.out.println(output);
                System.out.println("1o12utput");
                JSONObject jsonObject = new JSONObject(output);
                JSONArray jsonArray = new JSONArray(output);
                System.out.println(jsonObject);
            }
            System.out.println(in.readLine());
            str = din.readUTF();
            System.out.println(str);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return str;
    }


        public  void close(){

    }

}
