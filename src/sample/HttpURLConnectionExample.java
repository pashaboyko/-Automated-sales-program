package sample;

//import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpURLConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://localhost:3000/get-user";

    private static final String POST_URL_DEMO = " http://5fdd894c.ngrok.io/barcode";
    private static final String POST_URL_ALL = " http://5fdd894c.ngrok.io/barcodeall";
    //private static final String POST_URL_LOGIN = "http://25c2c2ef.ngrok.io/login";
    //private static final String POST_PARAMS = "email=a.miron@gmail.com&&password=1";
    private static final String POST_PARAMS_DEMO = "barcode=644832819197";



    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }


    public static void sendPOST(String link , String params) throws IOException {
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("authorization", "Java client");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(params.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //System.out.println(response.toString());
            //JSONObject myResponse = new JSONObject(response.toString());
            //System.out.println(myResponse.toString());
            System.out.println("result after Reading JSON Response");
            /*item m = new item(myResponse);
            System.out.println(m.getName());
            System.out.println(m.getBarcode());
            System.out.println(m.getPrice());
            System.out.println(m.getCategory());
            System.out.println(m.getPoints());
            System.out.println(m.getPhoto());*/

        } else {
            System.out.println("POST request not worked");
        }
    }


}

