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
import java.util.ResourceBundle;

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
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

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

        labelKlienta = new JLabel(bundle.getString("podaj.nazwisko"));
        szukajKlienta = new JButton(bundle.getString("szukaj.klienta"));

        labekKarnet = new JLabel(bundle.getString("podaj.nr.karnetu"));
        szukajKarnetu = new JButton(bundle.getString("szukaj.karnetu"));

        tekstOsoba = new JTextField();
        tekstOsoba.setColumns(15);
        paneKarnet = new JScrollPane(tabelaKarnety);
        paneKarnet.setPreferredSize(new Dimension(600, 100));
        usunKlienta = new JButton(bundle.getString("usun.klienta"));
        paneOsoba = new JScrollPane(tableOsoby);

        paneOsoba.setPreferredSize(new Dimension(600, 400));
        utworzNaglowki();
        tekstKarnet = new JTextField();
        tekstKarnet.setColumns(15);
        usunKarnet = new JButton(bundle.getString("usun.karnet"));

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
        tekstOsoba.setText("");
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
        tekstKarnet.setText("");
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
                //  System.out.println("Brak wynikow");
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
        modelOsob.setColumnIdentifiers(new Object[]{bundle.getString("klient.nr"),
                bundle.getString("imie"),
                bundle.getString("nazwisko"),
                bundle.getString("telefon")});
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
        modelKarnetow.setColumnIdentifiers(new Object[]{
                bundle.getString("nr.karnetu"),
                bundle.getString("klient.nr"),
                bundle.getString("nazwa.karnetu"),
                bundle.getString("osoba"),
                bundle.getString("od"),
                bundle.getString("do")
        });


        tabelaKarnety.setModel(modelKarnetow);

    }

    @Override
    public void aktualizacjaEtykiet() {
        bundle = ResourceBundle.getBundle("messages");
        usunKlienta.setText(bundle.getString("usun.klienta"));
        usunKarnet.setText(bundle.getString("usun.karnet"));
        labekKarnet.setText(bundle.getString("podaj.nr.karnetu"));
        labelKlienta.setText(bundle.getString("podaj.nazwisko"));
        szukajKarnetu.setText(bundle.getString("szukaj.karnetu"));
        szukajKlienta.setText(bundle.getString("szukaj.klienta"));
        naglowkiTabelTlumacz();


    }

    private void naglowkiTabelTlumacz() {
        modelOsob.setColumnIdentifiers(new Object[]{bundle.getString("klient.nr"),
                bundle.getString("imie"),
                bundle.getString("nazwisko"),
                bundle.getString("telefon")});
        modelKarnetow.setColumnIdentifiers(new Object[]{
                bundle.getString("nr.karnetu"),
                bundle.getString("klient.nr"),
                bundle.getString("nazwa.karnetu"),
                bundle.getString("osoba"),
                bundle.getString("od"),
                bundle.getString("do")
        });

    }
}
