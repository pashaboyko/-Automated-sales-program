package sample;

import sample.pojo.Clothes;
import sample.pojo.Food;
import sample.pojo.Tech;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connectionn {
    private static final String url = "jdbc:mysql://localhost:3306/product";
    private static final String user = "root";
    private static final String password = "lock172839465";
    private static java.sql.Connection con;
    private static Statement stmt;
    private static ResultSet rs;
public Connectionn(){

    try {

        // opening database connection to MySQL server
        con = DriverManager.getConnection(url, user, password);

        // getting Statement object to execute query
        stmt = con.createStatement();
    } catch (SQLException sqlEx) {
        sqlEx.printStackTrace();
    }

        // executing SELECT query
}
   public   void OutInfo (){

       try {


           String queryy = "select id, name, bordercode, price, photo, size, color, season  from clothes";

           rs = stmt.executeQuery(queryy);

           while (rs.next()) {
               int id = rs.getInt(1);
               String name = rs.getString(2);
               String barcode = rs.getString(3);
               Double price= rs.getDouble(4);
               System.out.printf("id" + id +  "name: " + name + "barcode: " +  barcode + " price: " + price + "%n");
           }
           String queryy1 = "select id, name, bordercode, price, photo, day, month , year  from food";

           rs = stmt.executeQuery(queryy1);


           while (rs.next()) {
               int id = rs.getInt(1);
               String name = rs.getString(2);
               String barcode = rs.getString(3);
               Double price= rs.getDouble(4);
               System.out.printf("id" + id +  "name: " + name + "barcode: " +  barcode + " price: " + price + "%n");
           }
           String queryy2 = "select id, name, bordercode, price, photo, warranty, color  from tech";

           rs = stmt.executeQuery(queryy2);

           while (rs.next()) {
               int id = rs.getInt(1);
               String name = rs.getString(2);
               String barcode = rs.getString(3);
               Double price= rs.getDouble(4);
               System.out.printf("id" + id +  "name: " + name + "barcode: " +  barcode + " price: " + price + "%n");
           }


       } catch (SQLException sqlEx) {
           sqlEx.printStackTrace();
       }

   }

   public  void close(){

           try { con.close(); } catch(SQLException se) { /*can't do anything */ }
           try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
           try { rs.close(); } catch(SQLException se) { /*can't do anything */ }

   }

   boolean adminCheck(String bar){
       String queryy = "select  barcode  from Admin";
       try {
       rs = stmt.executeQuery(queryy);
           while (rs.next()) {
               System.out.println(rs.getString(1));
               System.out.println(bar);
               String b = new String(rs.getString(1));
               if(b.equals(bar)){
                   System.out.println("2");
                   return true;
               }
           }
           return false;
       } catch (SQLException sqlEx) {
           sqlEx.printStackTrace();
           return false;
       }

   }

    boolean barcodeCheck(String bar){
        String queryy = "select  bordercode  from clothes";
        try {
            rs = stmt.executeQuery(queryy);
            while (rs.next()) {
                String b = new String(rs.getString(1));
                if(b.equals(bar)){
                    return true;
                }
            }
            String queryy1 = "select  bordercode  from food";
            rs = stmt.executeQuery(queryy1);
            while (rs.next()) {
                String b = new String(rs.getString(1));
                if (b.equals(bar)) {
                    return true;
                }
            }
            String queryy2 = "select  bordercode  from tech";
            rs = stmt.executeQuery(queryy2);
            while (rs.next()) {
                String b = new String(rs.getString(1));
                if (b.equals(bar)) {
                    return true;
                }
            }

return false;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }

    }



   public void addtoProgramm(){

       try {
           String queryy = "select id, name, bordercode, price, photo, size, color, season  from clothes";

           rs = stmt.executeQuery(queryy);

           while (rs.next()) {
               Listfortovar.clothess.add(new Clothes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
              }
           /*Runtime rt = Runtime.getRuntime();
           File test=new File("/Users/pashaboyko/Library/Mobile Documents/com~apple~CloudDocs/h.sql");
           PrintStream ps;*/
           String queryy1 = "select id, name, bordercode, price, photo, day, month , year  from food";

           rs = stmt.executeQuery(queryy1);

           while (rs.next()) {
               Listfortovar.foods.add(new Food(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8)));
           }

           String queryy2 = "select id, name, bordercode, price, photo, warranty, color  from tech";

           rs = stmt.executeQuery(queryy2);

           while (rs.next()) {
               Listfortovar.techs.add(new Tech(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4), rs.getString(5),rs.getInt(6),rs.getString(7)));
           }


       } catch (SQLException sqlEx) {
           sqlEx.printStackTrace();
       }

   }


   public void deleteElement(int a, int ie) {
       if (a == 3) {
           String deleteTableSQL = "delete from tech where id = "+ie;
           try {
               // выполняем запрос delete SQL
               stmt.execute(deleteTableSQL);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }
           else{
               if(a==2){
                   String deleteTableSQL = "delete from food where id = "+ie;
                   try {
                       // выполняем запрос delete SQL
                       stmt.execute(deleteTableSQL);
                   } catch (SQLException e) {
                       System.out.println(e.getMessage());
                   }
               }
               else{ if (a==1){
                       String deleteTableSQL = "delete from clothes where id = "+ie;
                       try {
                           // выполняем запрос delete SQL
                           stmt.execute(deleteTableSQL);
                       } catch (SQLException e) {
                           System.out.println(e.getMessage());
                       }
                   }

               }
           }
           }
    public void addTech(Tech a){
        System.out.println(a.getId());
        String addTableSQL = "INSERT INTO tech(ID, name, bordercode, price,warranty,color) VALUES ("+a.getId()+","
                +"'"+a.getName()+"',"
                +"'"+a.getBarcode()+"',"
                +a.getPrice()+","
                +a.getWarranty()+","
                +"'"+a.getColor()+"')";

        try {
            // выполняем запрос delete SQL
            stmt.executeUpdate(addTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addClothes(Clothes a){
        System.out.println(a.getId());
        String addTableSQL = "INSERT INTO clothes(ID, name, bordercode, price,size,color,season) VALUES ("+a.getId()+","
                +"'"+a.getName()+"',"
                +"'"+a.getBarcode()+"',"
                +a.getPrice()+","
                +a.getSize()+","
                +"'"+a.getColor()+"',"
                +"'"+a.getSeason()+"')";
        try {
            // выполняем запрос delete SQL
            stmt.executeUpdate(addTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
   /* public void addTech(Tech a){
        String addTableSQL = "INSERT INTO TEch"
                + "(ID, name, bordercode, price,warranty,color) " + "VALUES"
                + "("+a.getId()+","+a.getName()+","+a.getBarcode()+","+a.getPrice()+","+a.getWarranty()+","+a.getColor()+")";
        try {
            // выполняем запрос delete SQL
            stmt.execute(addTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

    public void addFood (Food a){
        System.out.println(a.getId());
        String addTableSQL = "INSERT INTO food(ID, name, bordercode, price,day,month,year) VALUES ("+a.getId()+","
                +"'"+a.getName()+"',"
                +"'"+a.getBarcode()+"',"
                +a.getPrice()+","
                +a.getDate().getDay()+","
                +a.getDate().getMonth()+","
                +a.getDate().getYear()+")";
        try {
            // выполняем запрос delete SQL
            stmt.executeUpdate(addTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    }













