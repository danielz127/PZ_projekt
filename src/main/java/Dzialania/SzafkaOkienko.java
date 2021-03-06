package Dzialania;

import PaneleMenu.Klient.Klient;
import PaneleMenu.Szatnia.Szafka;
import PaneleMenu.Szatnia.SzafkaDialog;
import Baza.Baza;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SzafkaOkienko implements ActionListener {
    Szafka szafka;
    Baza baza;
    public SzafkaOkienko(Szafka szafka) {
        this.szafka = szafka;
        this.baza = szafka.szatnia.baza;
    }


    private void otworzMenuSzafki() {
        new SzafkaDialog(szafka);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(szafka.NrSzafki + " " + String.valueOf(szafka.plec));
        pobierzKlientow();


    }

    private void pobierzKlientow() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected void done() {
                super.done();
                otworzMenuSzafki();

            }

            @Override
            protected Void doInBackground() throws Exception {

                baza.utworzPolaczenie();

                szafka.szatnia.klients.clear();

                try {
                    baza.myStm = baza.myCon.createStatement();
                    baza.myRs = baza.myStm.executeQuery("select Klient.Imie, Klient.Nazwisko,Klient.Telefon, Klient.NrKlienta " +
                            "from klient, miasto, silownia " +
                            "WHERE klient.IdMiasta = miasto.IdMiasta and miasto.IdMiasta = silownia.IdMiasta "+
                            "ORDER BY klient.Nazwisko DESC ;");

                    while (baza.myRs.next()) {
                        String imie = baza.myRs.getString("Imie");
                        String nazwisko = baza.myRs.getString("Nazwisko");
                        String telefon = baza.myRs.getString("Telefon");
                        String nrKlienta = baza.myRs.getString("NrKlienta");
                        szafka.szatnia.klients.add(new Klient(imie, nazwisko, Integer.parseInt(telefon), Integer.parseInt(nrKlienta)));


                        //System.out.println(country + " " + sum);
                        //
                    }



                } catch (SQLException e) {
                    e.printStackTrace();
                }
               baza.rozlaczBaze();


                return null;
            }
        };
        worker.execute();
    }
}
