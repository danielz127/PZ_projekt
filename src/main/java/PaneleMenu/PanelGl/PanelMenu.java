package PaneleMenu.PanelGl;

import AbstractActions.WylogujAbstract;
import CzasSieciowy.PodawajGodzine;
import Interfejsy.AktualizacjaEtykiet;
import Interfejsy.Observer;
import Interfejsy.Subject;
import Dzialania.ZmienPanel;
import Main.OknoProgramu;
import PaneleMenu.Edycja.Edycja;
import PaneleMenu.Karnety.Karnety;
import PaneleMenu.Klient.Klienci;
import PaneleMenu.Stan.Stan;
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
    public Klienci klienci;
    public Szatnia szatnia;
    public Karnety karnety;
    String miasto;
    JLabel czasSieciowy;
    PodawajGodzine sprawdzGodzine;
    PrzyciskWMenu buttonWyloguj, buttonKlienci, buttonStan, buttonSzatnie, buttonEdycja, buttonKarnety;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    JLabel labelMiasto;
    ArrayList<PrzyciskWMenu> listaPrzyciskow;
    ArrayList<Observer> listaObserwatorow;
    String godzinka;
    Edycja edycja;
    Stan stan;
    ArrayList<JPanel> listaPaneli;
    ZmienPanel listenerKlienci, listenerKarnety, listenerSzatnie, listenerMagazyn, listenerWplaty;


    public PanelMenu(OknoProgramu frame, String miasto) {
        setVisible(true);

        sprawdzGodzine = PodawajGodzine.getInstance();
        this.frame = frame;
        setBackground(new Color(160, 255, 255));
        this.miasto = miasto;
        listaPaneli = new ArrayList<>();
        stworzMenu();

    }


    private void utworzPanele() {

        klienci = new Klienci(frame.baza, frame);
        szatnia = new Szatnia(frame.baza, frame);
        karnety = new Karnety(frame.baza, frame);
        stan = new Stan(frame);
        edycja = new Edycja(frame);

    }

    public void listeneryPaneli() {
        listenerKlienci = new ZmienPanel(this, klienci, buttonKlienci);
        listenerSzatnie = new ZmienPanel(this, szatnia, buttonSzatnie);
        listenerKarnety = new ZmienPanel(this, karnety, buttonKarnety);
        listenerWplaty = new ZmienPanel(this, stan, buttonStan);
        listenerMagazyn = new ZmienPanel(this, edycja, buttonEdycja);
    }


    public void stworzMenu() {
        GridLayout gridLayout = new GridLayout(20, 2, 0, 5);
        setLayout(gridLayout);
        czasSieciowy = new JLabel();
        listaPrzyciskow = new ArrayList<>();
        listaObserwatorow = new ArrayList<>();
        labelMiasto = new JLabel();
        stworzPrzyciski();

        dodajPrzyciski();
        etykietaMiasta();
        workerSwingPaneli();

    }

    private void workerSwingPaneli() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected void done() {
                super.done();
                listeneryPrzyciskow();
            }

            @Override
            protected Void doInBackground() throws Exception {
                utworzPanele();
                listeneryPaneli();
                paneleDoListy();

                return null;
            }
        };
        worker.execute();
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
        listaPaneli.add(stan);
        listaPaneli.add(edycja);
    }

    private void listeneryPrzyciskow() {

        buttonKlienci.addActionListener(listenerKlienci);
        buttonSzatnie.addActionListener(listenerSzatnie);
        buttonKarnety.addActionListener(listenerKarnety);
        buttonEdycja.addActionListener(listenerMagazyn);
        buttonStan.addActionListener(listenerWplaty);

    }

    public void etykietaMiasta() {


        SwingWorker<Void, Void> godzina = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                godzinka = sprawdzGodzine.podajGodzine();
                czasSieciowy.setText(bundle.getString("label.logged") + ": " + godzinka);
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
        buttonKlienci = new PrzyciskWMenu(bundle.getString("button.klienci"), new ImageIcon("src/main/resources/ikony/klienci.png"));
        buttonSzatnie = new PrzyciskWMenu(bundle.getString("button.szatnia"), new ImageIcon("src/main/resources/ikony/szatnia.png"));
        buttonKarnety = new PrzyciskWMenu(bundle.getString("button.karnety"), new ImageIcon("src/main/resources/ikony/karnety.png"));
        buttonEdycja = new PrzyciskWMenu(bundle.getString("button.edycja"), new ImageIcon("src/main/resources/ikony/magazyn.png"));
        buttonStan = new PrzyciskWMenu(bundle.getString("button.stan"), new ImageIcon("src/main/resources/ikony/statystyka.png"));
        buttonWyloguj = new PrzyciskWMenu(new WylogujAbstract(bundle.getString("button.wylogowanie"), new ImageIcon("src/main/resources/ikony/wyloguj.png"), frame));
        dodajDoListy();

    }

    private void dodajDoListy() {
        listaPrzyciskow.add(buttonKlienci);
        listaPrzyciskow.add(buttonSzatnie);
        listaPrzyciskow.add(buttonKarnety);
        listaPrzyciskow.add(buttonEdycja);
        listaPrzyciskow.add(buttonStan);
        listaPrzyciskow.add(buttonWyloguj);
        buttonWyloguj.setPreferredSize(new Dimension(130, 35));

        for (PrzyciskWMenu button : listaPrzyciskow) {
            register(button);
        }

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
        buttonKlienci.setText(bundle.getString("button.klienci"));
        buttonSzatnie.setText(bundle.getString("button.szatnia"));
        buttonKarnety.setText(bundle.getString("button.karnety"));
        buttonEdycja.setText(bundle.getString("button.edycja"));
        buttonStan.setText(bundle.getString("button.stan"));
        czasSieciowy.setText(bundle.getString("label.logged") + ": " + godzinka);
        szatnia.aktualizacjaEtykiet();
        klienci.aktualizacjaEtykiet();
        stan.aktualizacjaEtykiet();
        edycja.aktualizacjaEtykiet();
        karnety.aktualizacjaEtykiet();

    }
}
