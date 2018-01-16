package Dzialania;

import Baza.Baza;
import PaneleMenu.Karnety.Karnety;
import PaneleMenu.Karnety.NowyKarnetDialog;
import PaneleMenu.Klient.Klient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class NowyKarnet implements ActionListener {
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Karnety karnety;
    Baza baza;
    ArrayList<Klient> klients;

    public NowyKarnet(Karnety karnety) {
        this.karnety = karnety;
        this.baza = karnety.oknoProgramu.baza;
        this.klients = karnety.klients;
    }


    private void otworzOkno() {
        new NowyKarnetDialog(karnety.oknoProgramu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pobierzKlientow();


    }

    private void pobierzKlientow() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected void done() {
                super.done();
                otworzOkno();

            }

            @Override
            protected Void doInBackground() throws Exception {

                baza.utworzPolaczenie();

                karnety.klients.clear();

                try {
                    baza.myStm = baza.myCon.createStatement();
                    baza.myRs = baza.myStm.executeQuery("select Klient.Imie, Klient.Nazwisko,Klient.Telefon, Klient.NrKlienta " +
                            "from klient, miasto, silownia " +
                            "WHERE klient.IdMiasta = miasto.IdMiasta and miasto.IdMiasta = silownia.IdMiasta " +
                            "ORDER BY klient.Nazwisko DESC ;");


                    while (baza.myRs.next()) {
                        String imie = baza.myRs.getString("Imie");
                        String nazwisko = baza.myRs.getString("Nazwisko");
                        String telefon = baza.myRs.getString("Telefon");
                        String nrKlienta = baza.myRs.getString("NrKlienta");
                        klients.add(new Klient(imie, nazwisko, Integer.parseInt(telefon), Integer.parseInt(nrKlienta)));


                    }
                    baza.rozlaczBaze();


                } catch (SQLException e) {
                    logr.info("Blad z polaczeniem z baza");
                    // e.printStackTrace();
                }


                return null;
            }
        };
        worker.execute();
    }
}
