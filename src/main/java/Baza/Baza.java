package Baza;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Baza {
    public ResultSet myRs;
    public Connection myCon;
    public Statement myStm;
    BazaParser bazaParser;
    ArrayList<String> daneBazy;
    private static Logger logr=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
            logr.info("Blad polaczenia z baza");

        }
    }

    public void rozlaczBaze(){
        try {
            myCon.close();
        } catch (SQLException e) {
            logr.info("Blad przy rozlaczaniu z baza");
        }
    }
}
