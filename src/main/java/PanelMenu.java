import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-14.
 */
public class PanelMenu extends JPanel {

    String miasto;
    JLabel czasSieciowy;
    PodawajGodzine sprawdzGodzine;
    OknoProgramu frame;
    JButton buttonWyloguj, buttonKlienci, buttonWplaty, buttonSzatnie, buttonStatystyka, buttonMagazyn, buttonKarnety;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    JLabel labelMiasto;
    ArrayList<JButton> listaButtonow;
    //dodaj wszystkie panele do listy np;
    Klienci klienci;
    Szatnia szatnia;
    Karnety karnety;
    Magazyn magazyn;
    Statystyka statystyka;
    Wplaty wplaty;
    ArrayList<JPanel> listaPaneli;
    ZmienPanelListener listenerKlienci;
    ZmienPanelListener listenerSzatnie;


    public PanelMenu(OknoProgramu frame, String miasto) {
        setVisible(true);
        sprawdzGodzine = new PodawajGodzine();
        this.frame = frame;

        setBackground(Color.green);
        this.miasto = miasto;
        listaPaneli = new ArrayList<>();
        stworzMenu();
        utworzPanele();
        paneleDoListy();

        listeneryPaneli();
        //wygasPanele();
        listenery();
    }

    private void utworzPanele() {
        klienci = new Klienci(frame.baza, frame);
        szatnia = new Szatnia();
    }

    public void listeneryPaneli() {
        listenerKlienci = new ZmienPanelListener(this, klienci);
        listenerSzatnie = new ZmienPanelListener(this, szatnia);
    }


    public void stworzMenu() {
        GridLayout gridLayout = new GridLayout(20, 2, 0, 5);
        setLayout(gridLayout);
        czasSieciowy = new JLabel();
        listaButtonow = new ArrayList<>();
        SwingWorker<Void, Void> godzina = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                czasSieciowy.setText(bundle.getString("label.logged") + ": " + sprawdzGodzine.podajGodzine());
                return null;
            }
        };
        godzina.execute();

        labelMiasto = new JLabel();
        etykietaMiasta();
        stworzPrzyciski();
        dodajEtykiety();
        dodajPrzyciski();
        paneleDoListy();
        listenery();

    }

    public void wygasPanele() {
        for (JPanel panel : listaPaneli)
            if (panel != null)
                panel.setVisible(false);
    }

    private void paneleDoListy() {
        listaPaneli.add(klienci);
        listaPaneli.add(szatnia);
        listaPaneli.add(karnety);
        listaPaneli.add(wplaty);
        listaPaneli.add(statystyka);
        listaPaneli.add(magazyn);
    }

    private void listenery() {
//        buttonKlienci.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                //dodaj funkcje wygaszajaca wszystkie panele
//                wygasPanele();
//                klienci = new Klienci(frame.baza, frame);
//                frame.add(klienci);
//                frame.pack();
//                //cos zeby odwieżyć ramke
//            }
//        });
        buttonKlienci.addActionListener(listenerKlienci);
        buttonSzatnie.addActionListener(listenerSzatnie);

    }
        public void etykietaMiasta () {

            add(labelMiasto);
            add(czasSieciowy);

        }

        public void stworzPrzyciski () {
            //kolejnosc ma znaczenie
            buttonKlienci = new JButton("Klienci");
            buttonSzatnie = new JButton(("Szatnia"));
            buttonKarnety = new JButton("Karnety");
            buttonMagazyn = new JButton("Magazyn");
            buttonWplaty = new JButton("Wplaty");
            buttonStatystyka = new JButton("Statystyka");
            buttonWyloguj = new JButton(bundle.getString("button.wylogowanie"));
            dodajDoListy();

        }

        private void dodajDoListy () {
            listaButtonow.add(buttonKlienci);
            listaButtonow.add(buttonSzatnie);
            listaButtonow.add(buttonKarnety);
            listaButtonow.add(buttonMagazyn);
            listaButtonow.add(buttonWplaty);
            listaButtonow.add(buttonStatystyka);
            listaButtonow.add(buttonWyloguj);
        }

        public void dodajEtykiety () {
            bundle = ResourceBundle.getBundle("messages");
            labelMiasto.setText(miasto);
            System.out.print(Locale.getDefault());
            buttonWyloguj.setText(bundle.getString("button.wylogowanie"));

        }

        public void dodajPrzyciski () {
            for (JButton button : listaButtonow)
                add(button);
        }
    }
