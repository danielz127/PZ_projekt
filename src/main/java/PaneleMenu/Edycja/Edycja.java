package PaneleMenu.Edycja;

import Baza.Baza;
import Exceptions.BrakWynikow;
import Interfejsy.AktualizacjaEtykiet;
import Main.OknoProgramu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.SQLException;

public class Edycja extends JPanel implements AktualizacjaEtykiet {
    Baza baza;
    OknoProgramu oknoProgramu;
    JButton usunKlienta;
    JButton usunKarnet;
    JTextField tekstOsoba;
    JTextField tekstKarnet;
    JTable tableOsoby;
    DefaultTableModel modelOsob;
    JTable tabelaKarnety;
    DefaultTableModel modelKarnetow;
    JScrollPane paneOsoba, paneKarnet;
    JLabel labekKarnet, labelKlienta;
    JButton szukajKlienta, szukajKarnetu;
    String kolumny[] = {"Nr karnetu", "Nr klienta", "Nazwa karnetu", "Osoba", "Od", "Do"};

    public Edycja(OknoProgramu oknoProgramu) {
        setBackground(new Color(204, 255, 194));
        this.oknoProgramu = oknoProgramu;
        this.baza = oknoProgramu.baza;
        setPreferredSize(new Dimension(660, 500));
        utworzElementy();
        dodajElementy();
        listenery();
    }

    private void utworzElementy() {
        utworzTabeleOsob();
        utworzTabeleKarnetow();

        labelKlienta = new JLabel("Podaj nazwisko");
        szukajKlienta = new JButton("Szukaj klienta");

        labekKarnet = new JLabel("Podaj nr karnetu");
        szukajKarnetu = new JButton("Szukaj karnetu");

        tekstOsoba = new JTextField();
        tekstOsoba.setColumns(15);
        paneKarnet = new JScrollPane(tabelaKarnety);
        paneKarnet.setPreferredSize(new Dimension(600, 100));
        usunKlienta = new JButton("Usun klienta");
        paneOsoba = new JScrollPane(tableOsoby);

        paneOsoba.setPreferredSize(new Dimension(600, 400));
        utworzNaglowki();
        tekstKarnet = new JTextField();
        tekstKarnet.setColumns(15);
        usunKarnet = new JButton("Usun karnet");

    }

    private void dodajElementy() {


        add(labelKlienta);
        add(tekstOsoba);
        add(szukajKlienta);
        add(usunKlienta);
        add(paneOsoba);



        add(labekKarnet);
        add(tekstKarnet);
        add(szukajKarnetu);
        add(usunKarnet);
        add(paneKarnet);



    }

    private void listenery() {
        szukajKlienta.addActionListener(a -> szukajNazwiska());
        szukajKarnetu.addActionListener(a -> szukajKarnetu());
        usunKlienta.addActionListener(a -> usunKlienta());
        usunKarnet.addActionListener(a -> usunKarnet());
    }


    public void utworzNaglowki() {
        naglowkiKarnetow();
        naglowkiTabeliOsob();
    }

    private void usunKlienta() {
        if (tableOsoby.getSelectedRow() != -1) {
            int i = tableOsoby.getSelectedRow();
            int nrKlienta = Integer.parseInt(String.valueOf(tableOsoby.getValueAt(i, 0)));

            usunOsobeBaza(nrKlienta);

        }
    }

    private void usunOsobeBaza(int nrKlienta) {

        try {
            baza.utworzPolaczenie();
            baza.myStm = baza.myCon.createStatement();
            baza.myStm.executeUpdate("delete from karnety where Nrklienta =" + nrKlienta);
            baza.myStm.executeUpdate("delete from klient where NrKlienta=" + nrKlienta);
            baza.rozlaczBaze();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utworzNaglowki();


    }

    private void usunKarnet() {
        if (tabelaKarnety.getSelectedRow() != -1) {
            int i = tabelaKarnety.getSelectedRow();
            int nrkarnetu = Integer.parseInt(String.valueOf(tabelaKarnety.getValueAt(i, 0)));
            usunKarnetzBazy(nrkarnetu);

        }
    }

    private void usunKarnetzBazy(int nrkarnetu) {
        try {
            baza.utworzPolaczenie();
            baza.myStm = baza.myCon.createStatement();

            baza.myStm.executeUpdate("delete from Karnety where Idkarnetu=" + nrkarnetu);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        baza.rozlaczBaze();
        naglowkiKarnetow();
    }


    private void utworzTabeleKarnetow() {
        tabelaKarnety = new JTable() {
            @Override
            public void setSelectionBackground(Color selectionBackground) {
                super.setSelectionBackground(new Color(204, 255, 65));
            }
        };
    }

    private void utworzTabeleOsob() {
        tableOsoby = new JTable() {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            public Component prepareRenderer(
                    TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                if (isCellSelected(data, columns)) {
                    c.setBackground(new Color(200, 200, 233));

                }
                return c;
            }

        };
    }

    private void szukajNazwiska() {
        // table.add
        naglowkiTabeliOsob();


        boolean znaleziono = false;

        baza.utworzPolaczenie();
        String nazwiskoWejscie = tekstOsoba.getText();


        String kodSql = "select Klient.Nrklienta, Klient.Imie, Klient.Nazwisko,Klient.Telefon from klient, miasto, silownia " +
                "WHERE klient.IdMiasta = miasto.IdMiasta and miasto.IdMiasta = silownia.IdMiasta and Klient.Nazwisko =" + '"' + nazwiskoWejscie + '"' +
                " ORDER BY klient.Nazwisko DESC";
        try {
            baza.myStm = baza.myCon.createStatement();
            baza.myRs = baza.myStm.executeQuery(kodSql);

            while (baza.myRs.next()) {
                znaleziono = true;
                String nrKlienta = baza.myRs.getString("Nrklienta");
                String imie = baza.myRs.getString("Imie");
                String nazwisko = baza.myRs.getString("Nazwisko");
                String telefon = baza.myRs.getString("Telefon");
                modelOsob.addRow(new Object[]{nrKlienta, imie, nazwisko, telefon});

            }
            try {
                if (!znaleziono)
                    throw new BrakWynikow();
            } catch (BrakWynikow brakWynikow) {
                System.out.println("Brak wynikow");
            }
            znaleziono = false;
            baza.rozlaczBaze();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOsoby.setModel(modelOsob);

    }

    private void naglowkiTabeliOsob() {
        modelOsob = new DefaultTableModel();
        modelOsob.addColumn("Nr Klienta");
        modelOsob.addColumn("Imie");
        modelOsob.addColumn("Nazwisko");
        modelOsob.addColumn("Telefon");
        tableOsoby.setModel(modelOsob);
    }

    public void szukajKarnetu() {
        // table.add
        naglowkiKarnetow();


        boolean znaleziono = false;
        String nrkrt = tekstKarnet.getText();

        try {
            baza.utworzPolaczenie();
            baza.myStm = baza.myCon.createStatement();
            baza.myRs = baza.myStm.executeQuery("select karnety.Idkarnetu, karnety.NrKlienta, karnety.Nazwakarnetu, Klient.Imie, Klient.Nazwisko, Karnety.Od, Karnety.Do\n" +
                    "from karnety, klient\n" +
                    "where karnety.NrKlienta = klient.Nrklienta and karnety.Idkarnetu = '" + nrkrt + "'");

            while (baza.myRs.next()) {
                znaleziono = true;
                int nrKarnetu = baza.myRs.getInt("IdKarnetu");
                int nrKlienta = baza.myRs.getInt("Nrklienta");
                String imie = baza.myRs.getString("Imie");
                String nazwaKarnetu = baza.myRs.getString("Nazwakarnetu");
                String nazwisko = baza.myRs.getString("Nazwisko");
                String dataOd = baza.myRs.getString("Od");
                String dataDo = baza.myRs.getString("Do");

                modelKarnetow.addRow(new Object[]{nrKarnetu, nrKlienta, nazwaKarnetu, (imie + " " + nazwisko), dataOd, dataDo});

            }
            try {
                if (!znaleziono)
                    throw new BrakWynikow();
            } catch (BrakWynikow brakWynikow) {
                // System.out.println("Brak wynikow");
            }
            znaleziono = false;

            baza.rozlaczBaze();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabelaKarnety.setModel(modelKarnetow);


    }

    private void naglowkiKarnetow() {

        modelKarnetow = new DefaultTableModel();
        modelKarnetow.addColumn("Nr karnetu");
        modelKarnetow.addColumn("Nr klienta");
        modelKarnetow.addColumn("Nazwa karnetu");
        modelKarnetow.addColumn("Osoba");
        modelKarnetow.addColumn("Od");
        modelKarnetow.addColumn("Do");
        tabelaKarnety.setModel(modelKarnetow);

    }

    @Override
    public void aktualizacjaEtykiet() {

    }
}
