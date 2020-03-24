package sample;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONException;

import org.json.JSONObject;

public class Client {
    private Socket socket;
    private OutputStream output;
    private Writer writer;
    private BufferedWriter buffer;
    private static final Client instance = new Client();
    public String[][] board = new String[3][3];
    private static Scanner input;

    private Client() {
    }

    public static void main(String[] args) {
        input = new Scanner(System.in);
        getInstance().connect();
        getInstance().to_listen();
    }

    public void connect() {
        try {
            this.socket = new Socket("127.0.0.1", 3000);
            this.output = this.socket.getOutputStream();
            this.writer = new OutputStreamWriter(this.output);
            this.buffer = new BufferedWriter(this.writer);
            this.buffer.flush();
        } catch (UnknownHostException var2) {
            var2.printStackTrace();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void sendMessage(JSONObject json) {
        try {
            this.buffer.write(json.toString());
            this.buffer.flush();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void to_listen() {
        try {
            InputStream input = this.socket.getInputStream();
            InputStreamReader input_reader = new InputStreamReader(input);
            BufferedReader buffer = new BufferedReader(input_reader);

            while(true) {
                while(!buffer.ready()) {
                }

                JSONObject json = new JSONObject(buffer.readLine());
                if (json.has("flag")) {
                    this.print(this.convertJsonToArrayString(json));
                    if (this.checkWinner(json.get("flag").toString())) {
                        break;
                    }
                } else {
                    this.print(this.convertJsonToArrayString(json));
                    this.getData();
                    this.sendMessage(this.convertBoardToJson());
                }
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        } catch (JSONException var6) {
            var6.printStackTrace();
        }

    }

    public static Client getInstance() {
        return instance;
    }

    public void makeMatrix() {
        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board.length; ++j) {
                this.board[i][j] = " ";
            }
        }

    }

    public void getData() {
        System.out.println("Faça sua jogada: ");
        System.out.println("Digite a linha: ");
        int row = input.nextInt();
        System.out.println("Digite a coluna: ");

        int column;
        for(column = input.nextInt(); !this.verifyRowColumn(row, column); column = input.nextInt()) {
            System.out.println("Posição inválida, digite novamente!");
            System.out.println("Digite a linha: ");
            row = input.nextInt();
            System.out.println("Digite a coluna: ");
        }

        this.board[row][column] = "x";
    }

    public JSONObject convertBoardToJson() {
        JSONObject json = new JSONObject();
        List<String> list = new ArrayList();

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board.length; ++j) {
                list.add(this.board[i][j]);
            }

            try {
                json.put(String.valueOf(i + 1), list.toString());
            } catch (JSONException var5) {
                var5.printStackTrace();
            }

            list.clear();
        }

        return json;
    }

    public void print(List<String[]> list) {
        this.makeMatrix();
        
        for(int i = 0; i < this.board.length; ++i) {
            for(i = 0; i < this.board.length; ++i) {
                if (((String[])list.get(i))[i].equals("\"\"")) {
                    this.board[i][i] = this.board[i][i];
                } else {
                    this.board[i][i] = ((String[])list.get(i))[i].replace("\"", "").replace("\"", "");
                }
            }
        }

        String mat = "+---+---+---+\n";

        int i;
        for(i = 0; i < this.board.length; ++i) {
            mat = mat + "|";

            for(int j = 0; j < this.board.length; ++j) {
                mat = mat + " " + this.board[i][j] + " |";
            }

            mat = mat + "\n";
            mat = mat + "+---+---+---+\n";
        }

        System.out.println("Servidor jogou: ");
        System.out.print(mat);
    }

    public boolean checkWinner(String flag) {
        if (flag.equals("x")) {
            System.out.println("Jogador ganhou !!!");
            return true;
        } else if (flag.equals("e")) {
            System.out.println("Partida empatada.");
            return true;
        } else if (flag.equals("o")) {
            System.out.println("Servidor ganhou !");
            return true;
        } else {
            return false;
        }
    }

    private List<String[]> convertJsonToArrayString(JSONObject json) {
        try {
            String lineOne = json.getString("1");
            String lineTwo = json.getString("2");
            String lineTree = json.getString("3");
            List<String[]> list = new ArrayList();
            String[] aux1 = lineOne.replace("[", "").replace("]", "").split(",");
            String[] aux2 = lineTwo.replace("[", "").replace("]", "").split(",");
            String[] aux3 = lineTree.replace("[", "").replace("]", "").split(",");
            list.add(aux1);
            list.add(aux2);
            list.add(aux3);
            return list;
        } catch (JSONException var9) {
            var9.printStackTrace();
            return null;
        }
    }

    private boolean verifyRowColumn(int row, int column) {
        if (column >= 0 && column <= 2) {
            if (row >= 0 && row <= 2) {
                return this.board[row][column].equals(" ");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
