import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.sun.xml.internal.fastinfoset.stax.events.EventBase;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-10.
 */
// 1000 x 700
public class OknoProgramu extends JFrame {
    //wraz z logowaniem

    JPanel panelLogowania;
    JPanel panelProgram;
    JPanel panelMenu;

    String loginBaza = "";
    String hasloBaza = "";
    String miasto = "";

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
    MenuJBar menuJBar;
    ResourceBundle bundle;
    EventBus eventBus;



    public OknoProgramu() {
        super();
        eventBus = new EventBus();
        eventBus.register(this);
        bundle = ResourceBundle.getBundle("messages");
        setTitle(bundle.getString("app.title"));

        menuJBar = new MenuJBar(this, eventBus);
        // pamietac ze menu tu sie dodaje
        setJMenuBar(menuJBar);
        panelLogowania = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconImage = Toolkit.getDefaultToolkit().getImage("resources/dumbbell.png");

        setIconImage(iconImage);
        setSize(1000, 700);

        add(panelLogowania);

        setResizable(false);
        setVisible(true);

        guiPaneluLogowania();
        polaczenieBazy();
        eventBus.register(panelLogowania);



    }

    @Subscribe
    public void onEtykietaEvent(EtykietaEvent event){

        aktualizujEtykiety();

    }

    public void guiPaneluGlownego() {
        //zobaczyc czy frame sie dobrze dodal
        panelProgram = new JPanel();

        //miasto zwracane przez zapytanie
        panelMenu = new PanelMenu(this, miasto, bundle);

        setLayout(new BorderLayout());
        setSize(this.getSize());
        setVisible(true);

        setBackground(Color.RED);

        panelMenu.setPreferredSize(new Dimension(200, 700));

        add(panelMenu, BorderLayout.WEST);
        panelLogowania.setVisible(false);

        //tutaj drugi panel
        add(new Klienci());
        eventBus.register(panelMenu);
        eventBus.register(panelProgram);
        eventBus.register(menuJBar);
        revalidate();
        repaint();

    }


    public void guiPaneluLogowania() {
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

    public void pominLogowanie() {
//        panelProgram = new PanelProgram(this, "Krakow");
//        panelLogowania.setVisible(false);
//        add(panelProgram);

        guiPaneluGlownego();
    }

    public void polaczenieBazy() {
        try {

            String url = "jdbc:mysql://localhost:3306/zdarzeniowe";
            String userName = "root";
            String password = "root";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            myCon = DriverManager.getConnection(url, userName, password);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean sprawdzCzyDobreHaslo() {
        {

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
                    //rozlaczyc z baza!!
                    myCon.close();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;


        }
    }

    public boolean sprawdzPoprawnosc(String loginPanel, String hasloPanel, String loginBaza, String hasloBaza, String miasto) {
        if (loginPanel.equals(loginBaza) && hasloPanel.equals(hasloBaza)) {

            panelMenu = new PanelProgram(this, miasto, bundle);
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


    public void aktualizujEtykiety() {

        labelHaslo.setText(bundle.getString("button.password"));
    }
}
