import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-10.
 */
// 1000 x 700
public class OknoProgramu extends JFrame {
    //wraz z logowaniem

    JPanel panelLogowania;
    JPanel panelProgram;
    PanelMenu panelMenu;

    String loginBaza = "";
    String hasloBaza = "";
    String miasto = "";

    Baza baza;


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
    Font font;
    WczytajProperties wczytajProperties;


    public OknoProgramu() {
        super();
        wczytajProperties = new WczytajProperties();

        setSize(wczytajProperties.getSzerokosc(), wczytajProperties.getWysokosc());
        //moze warto zmienic
        setResizable(false);
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
    }


    public void utworzElementy() {
        //baza byc moze w tle


        font = getFont();
        bundle = ResourceBundle.getBundle("messages");
        menuJBar = new MenuJBar(this, bundle);
        panelLogowania = new JPanel();

        iconImage = Toolkit.getDefaultToolkit().getImage("src/main/resources/ikony/dumbbell.png");


    }

    public void dodajElementy() {

        setTitle(bundle.getString("app.title"));

        // pamietac ze menu tu sie dodaje
        setJMenuBar(menuJBar);

        add(panelLogowania);

        setIconImage(iconImage);

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
        //  add(new Klienci(baza, this));


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


    public boolean sprawdzCzyDobreHaslo() {
        {
            baza.utworzPolaczenie();
            String loginPanel = getUsername();
            String hasloPanel = getPassword();

            try {
                baza.myStm = baza.myCon.createStatement();
                baza.myRs = baza.myStm.executeQuery("select Uzytkownik.Login, uzytkownik.Haslo, miasto.Nazwa\n" +
                        "from miasto, uzytkownik, silownia\n" +
                        "where Miasto.IdMiasta = silownia.IdMiasta AND silownia.IdSilowni = uzytkownik.IdSilowni");
                while (baza.myRs.next()) {
                    loginBaza = baza.myRs.getString("Login");
                    hasloBaza = baza.myRs.getString("Haslo");
                    miasto = baza.myRs.getString("Nazwa");

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


    public void aktualizujEtykiety() {

        bundle = ResourceBundle.getBundle("messages");
        labelHaslo.setBounds(labelHaslo.getX(), labelHaslo.getY(), labelHaslo.getWidth() + 50, labelHaslo.getHeight());
        labelHaslo.setText(bundle.getString("label.password"));


    }
}
