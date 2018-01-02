import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Baza {
    ResultSet myRs;
    Connection myCon;
    Statement myStm;
    BazaParser bazaParser;
    ArrayList<String> daneBazy;
    public Baza() {
      bazaParser = new BazaParser();
      bazaParser.pobierzXML();
      daneBazy = bazaParser.getDaneBazy();

    }

    public void utworzPolaczenie(){
        try {


            String url = daneBazy.get(0);
            String userName = daneBazy.get(1);
            String password = daneBazy.get(2);


            Class.forName("com.mysql.jdbc.Driver").newInstance();

            myCon = DriverManager.getConnection(url, userName, password);



        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void rozlaczBaze(){
        try {
            myCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
