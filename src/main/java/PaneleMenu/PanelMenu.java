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
    PrzyciskWMenu buttonWyloguj, buttonKlienci, buttonWplaty, buttonSzatnie, buttonZestawienie, buttonMagazyn, buttonKarnety;
    ResourceBundle bundle;
    JLabel labelMiasto;
    ArrayList<PrzyciskWMenu> listaPrzyciskow;
    ArrayList<Observer> listaObserwatorow;

    //wszystkie panele do listy np;
    Klienci klienci;
    public Szatnia szatnia;
    public Karnety karnety;
    Magazyn magazyn;
    Zestawienie zestawienie;
    Wplaty wplaty;
    ArrayList<JPanel> listaPaneli;
    ZmienPanelListener listenerKlienci, listenerKarnety, listenerSzatnie, listenerZestawienie, listenerMagazyn, listenerWplaty;



    public PanelMenu(OknoProgramu frame, String miasto) {
        setVisible(true);
        bundle = ResourceBundle.getBundle("messages");
        sprawdzGodzine = PodawajGodzine.getInstance();
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
        karnety = new Karnety(frame.baza, frame);
        wplaty = new Wplaty();
        zestawienie = new Zestawienie();
        magazyn = new Magazyn();
    }

    public void listeneryPaneli() {
        listenerKlienci = new ZmienPanelListener(this, klienci, buttonKlienci);
        listenerSzatnie = new ZmienPanelListener(this, szatnia, buttonSzatnie);
        listenerKarnety = new ZmienPanelListener(this, karnety, buttonKarnety);
        listenerZestawienie = new ZmienPanelListener(this, zestawienie, buttonZestawienie);
        listenerWplaty = new ZmienPanelListener(this, wplaty, buttonWplaty);
        listenerMagazyn = new ZmienPanelListener(this, magazyn, buttonMagazyn);


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
        listaPaneli.add(zestawienie);
        listaPaneli.add(magazyn);
    }

    private void listeneryPrzyciskow() {

        buttonKlienci.addActionListener(listenerKlienci);
        buttonSzatnie.addActionListener(listenerSzatnie);
        buttonKarnety.addActionListener(listenerKarnety);
        buttonMagazyn.addActionListener(listenerMagazyn);
        buttonWplaty.addActionListener(listenerWplaty);
        buttonZestawienie.addActionListener(listenerZestawienie);

    }

    public void etykietaMiasta() {


        SwingWorker<Void, Void> godzina = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                czasSieciowy.setText(bundle.getString("label.logged") + ": " + sprawdzGodzine.podajGodzine());
                return null;
            }
        };
        labelMiasto.setPreferredSize(new Dimension(100, 20));
        labelMiasto.setText(miasto);
        godzina.execute();
        add(labelMiasto);
        add(czasSieciowy);

    }

    public void stworzPrzyciski() {
        //kolejnosc ma znaczenie
        buttonKlienci = new PrzyciskWMenu("Klienci", new ImageIcon("src/main/resources/ikony/klienci.png"));
        buttonSzatnie = new PrzyciskWMenu("Szatnia", new ImageIcon("src/main/resources/ikony/szatnia.png"));
        buttonKarnety = new PrzyciskWMenu("Karnety", new ImageIcon("src/main/resources/ikony/karnety.png"));
        buttonMagazyn = new PrzyciskWMenu("Magazyn", new ImageIcon("src/main/resources/ikony/magazyn.png"));
        buttonWplaty = new PrzyciskWMenu("Wplaty", new ImageIcon("src/main/resources/ikony/wplata.png"));
        buttonZestawienie = new PrzyciskWMenu("Zestawienie", new ImageIcon("src/main/resources/ikony/zestawienie.png"));
        buttonWyloguj = new PrzyciskWMenu(new WylogujAbstract(bundle.getString("button.wylogowanie"), new ImageIcon("src/main/resources/ikony/wyloguj.png"), frame));
        dodajDoListy();

    }

    private void dodajDoListy() {
        listaPrzyciskow.add(buttonKlienci);
        listaPrzyciskow.add(buttonSzatnie);
        listaPrzyciskow.add(buttonKarnety);
        listaPrzyciskow.add(buttonMagazyn);
        listaPrzyciskow.add(buttonWplaty);
        listaPrzyciskow.add(buttonZestawienie);
        listaPrzyciskow.add(buttonWyloguj);
        buttonWyloguj.setPreferredSize(new Dimension(130, 35));

        for (PrzyciskWMenu button : listaPrzyciskow) {
            register(button);
        }

    }

    public void dodajEtykiety() {


    }

    public void dodajPrzyciski() {
        setLayout(new FlowLayout());
        for (PrzyciskWMenu button : listaPrzyciskow) {
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
        System.out.print(Locale.getDefault());
        buttonWyloguj.setText(bundle.getString("button.wylogowanie"));

    }
}
