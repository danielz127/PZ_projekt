package Main;

import AbstractActions.MenuJBar;
import Baza.Baza;
import Exceptions.PasswordException;
import Interfejsy.AktualizacjaEtykiet;
import Listenery.WindowCloseListener;
import PaneleMenu.PanelGl.PanelMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-10.
 */
// 1000 x 700
public class OknoProgramu extends JFrame implements AktualizacjaEtykiet {
    //wraz z logowaniem

    public PanelMenu panelMenu;
    public String miasto = "";
    public Baza baza;
    public MenuJBar menuJBar;
    public String silowniaNazwa;
    JPanel panelLogowania;
    JPanel panelProgram;
    String loginBaza = "";
    String hasloBaza = "";
    JLabel labelLogin;
    JTextField tekstLogin;
    JLabel labelHaslo;
    JTextField tekstHaslo;
    JButton przyciskLogowania;
    Image iconImage;
    JLabel background;
    ResourceBundle bundle;
    Font font;
    WczytajProperties wczytajProperties;


    public OknoProgramu() {
        super();
        wczytajProperties = new WczytajProperties();
        //setAlwaysOnTop(true);

        setBounds(50, 50, wczytajProperties.getSzerokosc(), wczytajProperties.getWysokosc());
        //moze warto zmienic
        setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        swingWrokerBazy();
        listenery();
        utworzElementy();
        dodajElementy();

        guiPaneluLogowania();

    }

    private void swingWrokerBazy() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                baza = new Baza();
                return null;
            }

        };
        worker.execute();
    }

    private void listenery() {
        addWindowListener(new WindowCloseListener(this));
    }


    public void utworzElementy() {
        //baza byc moze w tle


        font = getFont();
        bundle = ResourceBundle.getBundle("messages");
        setTitle(bundle.getString("app.title"));

        menuJBar = new MenuJBar(this, bundle);
        panelLogowania = new JPanel();

        iconImage = Toolkit.getDefaultToolkit().getImage("src/main/resources/ikony/dumbbell.png");


    }

    public void dodajElementy() {


        // pamietac ze menu tu sie dodaje
        setJMenuBar(menuJBar);

        add(panelLogowania);

        setIconImage(iconImage);
        setVisible(true);

    }


    public void guiPaneluGlownego() {
        //zobaczyc czy frame sie dobrze dodal
        panelProgram = new JPanel();

        //miasto zwracane przez zapytanie
        panelMenu = new PanelMenu(this, miasto);

        setLayout(new BorderLayout());
        setSize(this.getSize());
        setVisible(true);


        panelMenu.setPreferredSize(new Dimension(200, 700));

        add(panelMenu, BorderLayout.WEST);
        panelLogowania.setVisible(false);

        //tutaj drugi panel - domyslnie klienci - ale powinno byc na przycisk
        //  add(new PaneleMenu.PaneleMenu.Klient.Klient.Klienci(baza, this));


    }


    public void guiPaneluLogowania() {
        panelLogowania.setVisible(true);

        panelLogowania.setLayout(null);

        background = new JLabel(new ImageIcon("src/main/resources/silownia_tlo.jpg"));
        tekstLogin = new JTextField(20);
        tekstHaslo = new JPasswordField(20);
        labelLogin = new JLabel("Login");
        labelHaslo = new JLabel(bundle.getString("label.password"));
        przyciskLogowania = new JButton("Zaloguj", new ImageIcon("src/main/resources/ikony/login.png"));


        //wymiary obrazka
        background.setBounds(0, 0, 1000, 700);
        background.setOpaque(false);

        tekstLogin.setBounds(450, 200, 100, 30);
        tekstHaslo.setBounds(450, 250, 100, 30);

        labelLogin.setFont(new Font(font.getFontName(), font.getStyle(), 20));
        labelHaslo.setFont(new Font(font.getFontName(), font.getStyle(), 20));
        labelLogin.setBounds(350, 200, 50, 30);
        labelHaslo.setBounds(350, 250, 100, 30);
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

        guiPaneluGlownego();
    }


    public boolean sprawdzCzyDobreHaslo() {
        {
            baza.utworzPolaczenie();
            String loginPanel = getUsername();
            String hasloPanel = getPassword();

            try {
                baza.myStm = baza.myCon.createStatement();
                baza.myRs = baza.myStm.executeQuery("select Uzytkownik.Login, uzytkownik.Haslo, silownia.Nazwa as Silnaz, miasto.Nazwa\n" +
                        "from miasto, uzytkownik, silownia\n" +
                        "where Miasto.IdMiasta = silownia.IdMiasta AND silownia.IdSilowni = uzytkownik.IdSilowni");
                while (baza.myRs.next()) {
                    loginBaza = baza.myRs.getString("Login");
                    hasloBaza = baza.myRs.getString("Haslo");
                    miasto = baza.myRs.getString("Nazwa");
                    silowniaNazwa = baza.myRs.getString("Silnaz");
                    if (sprawdzPoprawnosc(loginPanel, hasloPanel, loginBaza, hasloBaza, miasto))
                        return true;
                    System.out.println(loginBaza + hasloBaza + miasto);
                    //rozlaczyc z baza!!

                }

                baza.rozlaczBaze();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (PasswordException e) {
                System.out.print("Tutaj jest łapany wyjątek");
            }

            return false;


        }
    }


    public boolean sprawdzPoprawnosc(String loginPanel, String hasloPanel, String loginBaza, String hasloBaza, String miasto) throws PasswordException {
        if (loginPanel.equals(loginBaza) && hasloPanel.equals(hasloBaza)) {

            guiPaneluGlownego();
            panelLogowania.setVisible(false);
            return true;


        } else {

            tekstHaslo.setText("");
            tekstLogin.setText("");
            throw new PasswordException();


        }

    }

    public String getUsername() {
        return tekstLogin.getText().trim();
    }

    public String getPassword() {
        return new String(tekstHaslo.getText());
    }


    @Override
    public void aktualizacjaEtykiet() {
        bundle = ResourceBundle.getBundle("messages");
        setTitle(bundle.getString("app.title"));
        labelHaslo.setBounds(labelHaslo.getX(), labelHaslo.getY(), labelHaslo.getWidth() + 50, labelHaslo.getHeight());
        labelHaslo.setText(bundle.getString("label.password"));

    }
}
