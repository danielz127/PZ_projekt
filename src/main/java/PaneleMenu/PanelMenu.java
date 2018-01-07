package PaneleMenu;

import AbstractActions.WylogujAbstract;
import CzasSieciowy.PodawajGodzine;
import Interfejsy.AktualizacjaEtykiet;
import Interfejsy.Observer;
import Interfejsy.Subject;
import Listenery.ZmienPanelListener;
import Main.OknoProgramu;
import PaneleMenu.Karnety.Karnety;
import PaneleMenu.Klient.Klienci;
import PaneleMenu.Szatnia.Szatnia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-14.
 */
public class PanelMenu extends JPanel implements Subject, AktualizacjaEtykiet {

    public OknoProgramu frame;
    String miasto;
    JLabel czasSieciowy;
    PodawajGodzine sprawdzGodzine;
    PrzyciskWMenu buttonWyloguj, buttonKlienci, buttonWplaty, buttonSzatnie, buttonStatystyka, buttonMagazyn, buttonKarnety;
    ResourceBundle bundle;
    JLabel labelMiasto;
    ArrayList<PrzyciskWMenu> listaPrzyciskow;
    ArrayList<Observer> listaObserwatorow;
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
        bundle = ResourceBundle.getBundle("messages");
        sprawdzGodzine = new PodawajGodzine();
        this.frame = frame;
        setBackground(new Color(160, 255, 255));
        this.miasto = miasto;
        listaPaneli = new ArrayList<>();
        stworzMenu();
        workerSwingPaneli();
    }

    private void workerSwingPaneli() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                utworzPanele();
                paneleDoListy();
                listeneryPaneli();
                //wygasPanele();
                listeneryPrzyciskow();
                return null;
            }
        };
        worker.execute();
    }

    private void utworzPanele() {

        klienci = new Klienci(frame.baza, frame);
        szatnia = new Szatnia(frame.baza, frame);
    }

    public void listeneryPaneli() {
        listenerKlienci = new ZmienPanelListener(this, klienci, buttonKlienci);
        listenerSzatnie = new ZmienPanelListener(this, szatnia, buttonSzatnie);

    }


    public void stworzMenu() {
        GridLayout gridLayout = new GridLayout(20, 2, 0, 5);
        setLayout(gridLayout);
        czasSieciowy = new JLabel();
        listaPrzyciskow = new ArrayList<>();

        listaObserwatorow = new ArrayList<>();


        labelMiasto = new JLabel();

        stworzPrzyciski();
        dodajEtykiety();
        dodajPrzyciski();
        paneleDoListy();
        listeneryPrzyciskow();
        etykietaMiasta();

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

    private void listeneryPrzyciskow() {
//        buttonKlienci.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                //dodaj funkcje wygaszajaca wszystkie panele
//                wygasPanele();
//                klienci = new PaneleMenu.PaneleMenu.Klient.Klient.Klienci(frame.baza, frame);
//                frame.add(klienci);
//                frame.pack();
//                //cos zeby odwieżyć ramke
//            }
//        });
        buttonKlienci.addActionListener(listenerKlienci);
        buttonSzatnie.addActionListener(listenerSzatnie);

    }

    public void etykietaMiasta() {

        add(labelMiasto);
        SwingWorker<Void, Void> godzina = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                czasSieciowy.setText(bundle.getString("label.logged") + ": " + sprawdzGodzine.podajGodzine());
                return null;
            }
        };
        labelMiasto.setPreferredSize(new Dimension(100, 20));
        godzina.execute();
        add(czasSieciowy);

    }

    public void stworzPrzyciski() {
        //kolejnosc ma znaczenie
        buttonKlienci = new PrzyciskWMenu("Klienci", new ImageIcon("src/main/resources/ikony/klienci.png"));
        buttonSzatnie = new PrzyciskWMenu("Szatnia", new ImageIcon("src/main/resources/ikony/szatnia.png"));
        buttonKarnety = new PrzyciskWMenu("Karnety", new ImageIcon("src/main/resources/ikony/karnety.png"));
        buttonMagazyn = new PrzyciskWMenu("Magazyn", new ImageIcon("src/main/resources/ikony/magazyn.png"));
        buttonWplaty = new PrzyciskWMenu("Wplaty", new ImageIcon("src/main/resources/ikony/wplata.png"));
        buttonStatystyka = new PrzyciskWMenu("Statystyka", new ImageIcon("src/main/resources/ikony/statystyka.png"));
        buttonWyloguj = new PrzyciskWMenu(new WylogujAbstract(bundle.getString("button.wylogowanie"), new ImageIcon("src/main/resources/ikony/wyloguj.png"), frame));
        dodajDoListy();

    }

    private void dodajDoListy() {
        listaPrzyciskow.add(buttonKlienci);
        listaPrzyciskow.add(buttonSzatnie);
        listaPrzyciskow.add(buttonKarnety);
        listaPrzyciskow.add(buttonMagazyn);
        listaPrzyciskow.add(buttonWplaty);
        listaPrzyciskow.add(buttonStatystyka);
        listaPrzyciskow.add(buttonWyloguj);

        for (PrzyciskWMenu button : listaPrzyciskow) {
            register(button);
        }

    }

    public void dodajEtykiety() {


    }

    public void dodajPrzyciski() {
        setLayout(new FlowLayout());

        for (PrzyciskWMenu button : listaPrzyciskow) {
            button.setPreferredSize(new Dimension(120, 30));
            button.setBackground(new Color(213, 237, 218));
            add(button);
        }
    }

    @Override
    public void register(Observer o) {
        listaObserwatorow.add(o);
    }

    @Override
    public void unregister(Observer o) {
        listaObserwatorow.remove(o);

    }

    @Override
    public void notifyObservers() {
        for (Observer o : listaObserwatorow)
            o.update();
    }

    @Override
    public void aktualizacjaEtykiet() {
        bundle = ResourceBundle.getBundle("messages");
        labelMiasto.setText(miasto);
        System.out.print(Locale.getDefault());
        buttonWyloguj.setText(bundle.getString("button.wylogowanie"));

    }
}
