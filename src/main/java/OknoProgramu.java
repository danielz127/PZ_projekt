import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-10.
 */
// 1000 x 700
public class OknoProgramu extends JFrame  {
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
    Font font;
    WindowCloseListener windowCloseListener;


    public OknoProgramu() {
        super();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 700);
        setResizable(false);
        setVisible(true);

        listenery();
        utworzElementy();
        dodajElementy();

        guiPaneluLogowania();

    }

    private void listenery() {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
    }


    public void utworzElementy() {

        font = getFont();
        eventBus = new EventBus();
        bundle = ResourceBundle.getBundle("messages");
        menuJBar = new MenuJBar(this, eventBus);
        panelLogowania = new JPanel();

        iconImage = Toolkit.getDefaultToolkit().getImage("src/main/resources/dumbbell.png");


    }

    public void dodajElementy() {
        eventBus.register(this);

        setTitle(bundle.getString("app.title"));


        // pamietac ze menu tu sie dodaje
        setJMenuBar(menuJBar);

        add(panelLogowania);
        eventBus.register(panelLogowania);
        setIconImage(iconImage);

    }

    @Subscribe
    public void onEtykietaEvent(EtykietaEvent event) {

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

        background = new JLabel(new ImageIcon("src/main/resources/silownia_tlo.jpg"));
        tekstLogin = new JTextField(20);
        tekstHaslo = new JPasswordField(20);
        labelLogin = new JLabel("Login");
        labelHaslo = new JLabel(bundle.getString("label.password"));
        przyciskLogowania = new JButton("Zaloguj");
        labelHaslo.setLabelFor(tekstHaslo);
        labelLogin.setLabelFor(tekstLogin);


        //wymiary obrazka
        background.setBounds(0,0,1000,700);
        background.setOpaque(false);

        tekstLogin.setBounds(450, 200, 100, 30);
        tekstHaslo.setBounds(450, 250, 100, 30);

        labelLogin.setFont(new Font(font.getFontName(), font.getStyle(), 20));
        labelHaslo.setFont(new Font(font.getFontName(), font.getStyle(), 20));
        labelLogin.setBounds(350, 200, 50, 30);
        labelHaslo.setBounds(350, 250, 50, 30);
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
                sprawdzCzyDobreHaslo();
                //pominLogowanie();

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
            polaczenieBazy();
            String loginPanel = getUsername();
            String hasloPanel = getPassword();

            try {
                myStm = myCon.createStatement();
                myRs = myStm.executeQuery("select Uzytkownik.Login, uzytkownik.Haslo, miasto.Nazwa\n" +
                        "from miasto, uzytkownik, silownia\n" +
                        "where Miasto.IdMiasta = silownia.IdMiasta AND silownia.IdSilowni = uzytkownik.IdSilowni");
                while (myRs.next()) {
                    loginBaza = myRs.getString("Login");
                    hasloBaza = myRs.getString("Haslo");
                    miasto = myRs.getString("Nazwa");
                    sprawdzPoprawnosc(loginPanel, hasloPanel, loginBaza, hasloBaza, miasto);
                    System.out.println(loginBaza + hasloBaza + miasto);
                    //rozlaczyc z baza!!

                }
                try {
                    myCon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;


        }
    }



    public void sprawdzPoprawnosc(String loginPanel, String hasloPanel, String loginBaza, String hasloBaza, String miasto) {
        if (loginPanel.equals(loginBaza) && hasloPanel.equals(hasloBaza)) {

            guiPaneluGlownego();
            panelLogowania.setVisible(false);


        } else {

            tekstHaslo.setText("");
            tekstLogin.setText("");

        }

    }

    public String getUsername() {
        return tekstLogin.getText().trim();
    }

    public String getPassword() {
        return new String(tekstHaslo.getText());
    }


    public void aktualizujEtykiety() {

        bundle = ResourceBundle.getBundle("messages");
        labelHaslo.setBounds(labelHaslo.getX(), labelHaslo.getY(), labelHaslo.getWidth()+50, labelHaslo.getHeight());
        labelHaslo.setText(bundle.getString("label.password"));


    }
}
