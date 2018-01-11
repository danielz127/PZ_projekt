package PaneleMenu.Szatnia;

import Listenery.SzafkaListener;

import javax.swing.*;

public class Szafka {
    public Boolean zajeta;
    public int NrSzafki;
    public boolean plec;
    SzafkaPrzycisk button;
    public boolean uszkodzona;
    public Szatnia szatnia;
    public int NrKlienta=0;



    public Szafka(boolean zajeta, int nrSzafki, boolean plec, Szatnia szatnia) {
        this.szatnia = szatnia;
        this.zajeta = zajeta;
        NrSzafki = nrSzafki;
        this.plec = plec;
        uszkodzona = false;
        button = new SzafkaPrzycisk();
        dodajIkone();
        wlasciwosciIkony();
        listener();

    }

    private void listener() {
        button.addActionListener(new SzafkaListener(this));
    }


    private void wlasciwosciIkony() {


    }

    public void dodajIkone() {
        if (zajeta == false && uszkodzona== false) {
            button.zwolnijSzafkie();

        } else {
            button.zajmijSzafkie();

        }

    }

}

