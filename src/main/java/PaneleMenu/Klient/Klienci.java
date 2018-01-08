package PaneleMenu.Klient;

import Baza.Baza;
import Exceptions.BrakWynikow;
import Interfejsy.PaneleInfo;
import Listenery.NowyKlientEvent;
import Main.OknoProgramu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Klienci extends JPanel implements PaneleInfo {

    JButton buttonDodajKlienta, buttonWyswietlKLienta;
    DefaultTableModel model;
    JTable table;
    Baza baza;
    JTextField textField;
    JLabel labelNazwisko;
    GridBagConstraints gbc;
    JButton buttonSzukajNazwiska;
    OknoProgramu oknoProgramu;


    JTextField textArea;

    public Klienci(Baza baza, OknoProgramu oknoProgramu) {
        //GridBagLayout od razu
        super();
        setBackground(Color.WHITE);
        this.oknoProgramu = oknoProgramu;
        this.baza = baza;
        model = new DefaultTableModel();

        utworzElementy();
        dodajElementy();

        listenery();

    }

    public void utworzElementy() {
        textField = new JTextField(15);
        buttonSzukajNazwiska = new JButton("Szukaj");
        labelNazwisko = new JLabel("Podaj nazwisko");

        gbc = new GridBagConstraints();

        table = new JTable();
        table.setEnabled(false);
        textArea = new JTextField();
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));

        buttonDodajKlienta = new JButton("Dodaj klienta");
        buttonWyswietlKLienta = new JButton("Wyswietl wszystkich klientow");


    }

    public void dodajElementy() {
        setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(table);

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(labelNazwisko, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 10;

        add(scrollPane, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        add(textField);

        gbc.gridx = 15;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        add(buttonSzukajNazwiska);


        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        add(buttonWyswietlKLienta, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridx = 8;
        gbc.gridy = 12;
        add(buttonDodajKlienta, gbc);


    }


    public void listenery() {
        buttonWyswietlKLienta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wypelnijTabele();
            }
        });
        buttonDodajKlienta.addActionListener(new NowyKlientEvent(baza, oknoProgramu));
        buttonSzukajNazwiska.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                szukajNazwiska();
            }
        });
    }

    private void szukajNazwiska() {
        // table.add
        model = new DefaultTableModel();
        model.addColumn("Imie");
        model.addColumn("Nazwisko");
        model.addColumn("Telefon");

        boolean znaleziono = false;

        baza.utworzPolaczenie();
        String nazwiskoWejscie = textField.getText();

        //powinno byc sql prepared
        String kodSql = "select Klient.Imie, Klient.Nazwisko,Klient.Telefon from klient, miasto, silownia " +
                "WHERE klient.IdMiasta = miasto.IdMiasta and miasto.IdMiasta = silownia.IdMiasta and Klient.Nazwisko =" + '"' + nazwiskoWejscie + '"' +
                " ORDER BY klient.Nazwisko DESC";
        try {
            baza.myStm = baza.myCon.createStatement();
            baza.myRs = baza.myStm.executeQuery(kodSql);

            while (baza.myRs.next()) {
                znaleziono = true;
                String imie = baza.myRs.getString("Imie");
                String nazwisko = baza.myRs.getString("Nazwisko");
                String telefon = baza.myRs.getString("Telefon");
                model.addRow(new Object[]{imie, nazwisko, telefon});

                //System.out.println(country + " " + sum);
                //
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

        table.setModel(model);

    }


    public void wypelnijTabele() {
        // table.add
        model = new DefaultTableModel();
        model.addColumn("Imie");
        model.addColumn("Nazwisko");
        model.addColumn("Telefon");
        baza.utworzPolaczenie();
        boolean znaleziono = false;
        try {
            baza.myStm = baza.myCon.createStatement();
            baza.myRs = baza.myStm.executeQuery("select Klient.Imie, Klient.Nazwisko,Klient.Telefon " +
                    "from klient, miasto, silownia " +
                    "WHERE klient.IdMiasta = miasto.IdMiasta and miasto.IdMiasta = silownia.IdMiasta " +
                    "ORDER BY klient.Nazwisko DESC ;");

            while (baza.myRs.next()) {
                znaleziono = true;
                String imie = baza.myRs.getString("Imie");
                String nazwisko = baza.myRs.getString("Nazwisko");
                String telefon = baza.myRs.getString("Telefon");
                model.addRow(new Object[]{imie, nazwisko, telefon});

                //System.out.println(country + " " + sum);
                //
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

        //model.addRow(new Object[]{null});
        table.setModel(model);


    }


}