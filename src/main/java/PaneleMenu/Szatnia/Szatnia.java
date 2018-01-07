package PaneleMenu.Szatnia;

import Baza.Baza;
import Main.OknoProgramu;
import PaneleMenu.Klient.Klient;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Szatnia extends JPanel  {
    public Baza baza;
    public OknoProgramu oknoProgramu;
    public ArrayList<Szafka> szafkiMeskie;
    public ArrayList<Szafka> szafkiDamskie;
    public ArrayList<Klient> klients;
    ArrayList<JButton> przyciskiSzafek;
    GridBagConstraints gbc;
    JLabel meskie, damskie;
    JButton zwolnijSzafki;
    Szatnia szatnia;

    public Szatnia(Baza baza, OknoProgramu oknoProgramu) {
        super();
        this.szatnia = this;
        this.oknoProgramu = oknoProgramu;
        this.baza = baza;
        zwolnijSzafki = new PrzyciskZwolnijSzafki("Zwolnij wszystkie szafki", this);

        pobierzSzafki();
        utworzPanel();
    }

    private void utworzLIsty() {
        szafkiMeskie = new ArrayList<>();
        szafkiDamskie = new ArrayList<>();
        przyciskiSzafek = new ArrayList<>();
        klients = new ArrayList<>();
    }

    private void pobierzSzafki() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected void done() {
                super.done();
                dodajPrzyciskiSzafek();

            }

            @Override
            protected Void doInBackground() throws Exception {
                utworzLIsty();
                baza.utworzPolaczenie();

                try {
                    baza.myStm = baza.myCon.createStatement();
                    //TODO Zmienic Warszawa na miasto.nazwa = okno.miasto

                    baza.myRs = baza.myStm.executeQuery("select szafki.NrSzafki, szafki.Zajetosc, szatnia.Plec\n" +
                            "FROM szafki, szatnia, silownia, miasto\n" +
                            "WHERE szatnia.IdSzatni=szafki.IdSzatni AND\n" +
                            "  szatnia.IdSilowni = silownia.IdSilowni and silownia.IdMiasta = miasto.IdMiasta\n" +
                            "AND miasto.Nazwa = '"+ oknoProgramu.miasto.toString()+"'");
                    while (baza.myRs.next()) {
                        String nrSzafki = baza.myRs.getString("NrSzafki");
                        String zajetosc = baza.myRs.getString("Zajetosc");
                        String plec = baza.myRs.getString("Plec");

                        //true - meskie
                        //false - damskie

                        if (plec.equals("1")) {
                            szafkiMeskie.add(new Szafka(Boolean.parseBoolean(zajetosc), Integer.parseInt(nrSzafki), true, szatnia));
                        } else {
                            szafkiDamskie.add(new Szafka(Boolean.parseBoolean(zajetosc), Integer.parseInt(nrSzafki), false, szatnia));
                        }

                        //System.out.println(country + " " + sum);
                        //
                    }

                    baza.rozlaczBaze();


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                baza.rozlaczBaze();
                return null;
            }
        };
        worker.execute();
    }

    private void dodajPrzyciskiSzafek() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        meskie = new JLabel("Meskie");
        meskie.setFont(new Font("Serif", Font.PLAIN, 24));
        int x = 0;
        int y = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth=4;
        add(meskie, gbc);
        gbc.gridwidth=1;
        y++;

        for (Szafka szafka : szafkiMeskie) {
            gbc.gridx = x;
            gbc.gridy = y;
            add(szafka.button, gbc);
            x = (x + 1);
            if (x == 4) {
                y++;
                x = 0;
            }
        }
        y=y+2;
        x=0;
        damskie = new JLabel("Damskie");
        damskie.setFont(new Font("Serif", Font.PLAIN, 24));
        gbc.gridy = y;
        gbc.gridx = x;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth=4;
        add(damskie, gbc);
        y++;
        gbc.gridwidth=1;
        y++;
        for (Szafka szafka : szafkiDamskie) {

            gbc.gridy = y;
            gbc.gridx = x;
            add(szafka.button, gbc);
            x = (x + 1);
            if (x == 4) {
                y++;
                x = 0;
            }
        }
        gbc.gridy = ++y;
        gbc.gridx = 0;
        gbc.gridwidth=2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(zwolnijSzafki, gbc);

    }


    public void utworzPanel() {

        setBackground(new Color(223, 219, 235));


    }
}
