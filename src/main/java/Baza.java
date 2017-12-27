import java.sql.*;

public class Baza {
    ResultSet myRs;
    Connection myCon;
    Statement myStm;
    public void utworzPOlaczenie(){
        try {

            String url = "jdbc:mysql://localhost:3306/zdarzeniowe";

            //bardzo tajne haslo
            String userName = "root";
            String password = "root";

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
