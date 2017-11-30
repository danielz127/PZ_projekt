import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Daniel on 2017-11-10.
 */
// 1000 x 700
public class OknoLogowania extends JFrame {

    JPanel panelLogowania;
    JPanel panelMenu;

    ResultSet myRs;
    Connection myCon;
    Statement myStm;
    JLabel labelLogin;
    JTextField tekstLogin;
    JLabel labelHaslo;
    JTextField tekstHaslo;
    JButton przyciskLogowania;
    GridBagConstraints gbc;
    boolean succeeded;
    Image iconImage;
    JLabel background;


    public OknoLogowania() {
        super("Siłownia");

        panelLogowania = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconImage = Toolkit.getDefaultToolkit().getImage("resources/dumbbell.png");

        setIconImage(iconImage);
        setSize(1000, 700);

        add(panelLogowania);

        setResizable(false);
        setVisible(true);
        guiPanelu();
        polaczenieBazy();

    }


    public void guiPanelu() {
        panelLogowania.setVisible(true);

        panelLogowania.setLayout(null);
        background = new JLabel(new ImageIcon("resources/silownia_tlo.jpg"));
        labelLogin = new JLabel("Login");

        tekstLogin = new JTextField(20);
        tekstHaslo = new JPasswordField(20);
        labelHaslo = new JLabel("Haslo");
        przyciskLogowania = new JButton("Zaloguj");
        labelHaslo.setLabelFor(tekstHaslo);
        labelLogin.setLabelFor(tekstLogin);

        background.setBounds(0, 0, 1000, 1000);
        tekstLogin.setBounds(450, 200, 100, 30);
        tekstHaslo.setBounds(450, 250, 100, 30);

        labelLogin.setBounds(400, 200, 50, 30);
        labelHaslo.setBounds(400, 250, 50, 30);
        przyciskLogowania.setBounds(450, 300, 100, 30);

        panelLogowania.add(tekstLogin);
        panelLogowania.add(labelHaslo);
        panelLogowania.add(tekstHaslo);
        panelLogowania.add(przyciskLogowania);
        panelLogowania.add(labelLogin);
        panelLogowania.add(background);

        przyciskLogowania.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //tutaj usunac, zeby wlaczyc logowanie
                //sprawdzCzyDobreHaslo();
                pominLogowanie();

            }
        });
    }

    public void pominLogowanie(){
        panelMenu = new PanelProgram( this, "Krakow");
        panelLogowania.setVisible(false);
        add(panelMenu);


    }
    public void polaczenieBazy() {
        try {

            String url = "jdbc:mysql://localhost:3306/zdarzenia";
            String userName = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            myCon = DriverManager.getConnection(url, userName, password);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean sprawdzCzyDobreHaslo() {
        {
            String loginBaza = "";
            String hasloBaza = "";
            String miasto = "";
            String loginPanel = getUsername();
            String hasloPanel = getPassword();

            try {
                myStm = myCon.createStatement();
                myRs = myStm.executeQuery("select Logowanie.Login, Logowanie.Haslo, Miasta.Miasto from Miasta, Logowanie where Miasta.IdMiasta = Logowanie.IdLogowania");
                while (myRs.next()) {
                    loginBaza = myRs.getString("Login");
                    hasloBaza = myRs.getString("Haslo");
                    miasto = myRs.getString("Miasto");
                    sprawdzPoprawnosc(loginPanel, hasloPanel, loginBaza, hasloBaza, miasto);
                    System.out.println(loginBaza + hasloBaza + miasto);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }
    }

    public boolean sprawdzPoprawnosc(String loginPanel, String hasloPanel, String loginBaza, String hasloBaza, String miasto) {
        if (loginPanel.equals(loginBaza) && hasloPanel.equals(hasloBaza)) {

            panelMenu = new PanelProgram(this, miasto);
            panelLogowania.setVisible(false);
            this.add(panelMenu);
            succeeded = true;
            return succeeded;

        } else {

            tekstHaslo.setText("");
            tekstLogin.setText("");
            succeeded = false;
            return succeeded;
        }

    }

    public String getUsername() {
        return tekstLogin.getText().trim();
    }

    public String getPassword() {
        return new String(tekstHaslo.getText());
    }


}
