package PaneleMenu.Karnety;

import Baza.Baza;
import Exceptions.BrakWynikow;
import Interfejsy.AktualizacjaEtykiet;
import Interfejsy.PaneleInfo;
import Listenery.NowyKarnetListener;
import Main.OknoProgramu;
import PaneleMenu.Klient.Klient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Karnety extends JPanel implements AktualizacjaEtykiet, PaneleInfo {

    public ArrayList<Klient> klients;
    public OknoProgramu oknoProgramu;
    JButton buttonDodajKarnet, buttonWyswietlKarnety;
    DefaultTableModel model;
    JTable table;
    Baza baza;
    JLabel karnetyLabel;
    GridBagConstraints gbc;
    ArrayList<Karnet> listaKarnetow;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");


    public Karnety(Baza baza, OknoProgramu oknoProgramu) {
        super();
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(550, 700));
        this.oknoProgramu = oknoProgramu;
        this.baza = baza;

        utworzElementy();
        dodajElementy();
        listenery();

    }

    public void utworzElementy() {
        gbc = new GridBagConstraints();
        klients = new ArrayList<>();
        model = new DefaultTableModel();
        table = new JTable() {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            public Component prepareRenderer(
                    TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                // Every even numbers
                if (data % 2 == 0)
                    c.setBackground(new Color(255, 216, 255));

                else
                    c.setBackground(new Color(229, 255, 255));

                return c;
            }

        };
        table.setSize((table.getWidth() + 200), table.getHeight());
        table.setEnabled(false);
        listaKarnetow = new ArrayList<>();
        karnetyLabel = new JLabel(bundle.getString("karnety"));
        karnetyLabel.setFont(new Font("Serif", Font.ITALIC, 24));

        buttonDodajKarnet = new JButton(bundle.getString("dodaj.karnet"));
        buttonWyswietlKarnety = new JButton(bundle.getString("wyswietl.karnety"));


    }

    public void dodajElementy() {
        setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(karnetyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        add(scrollPane, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        add(buttonWyswietlKarnety, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridx = 9;
        gbc.gridy = 12;
        add(buttonDodajKarnet, gbc);

    }


    public void listenery() {

        buttonWyswietlKarnety.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wypelnijTabele();
            }
        });
        buttonDodajKarnet.addActionListener(new NowyKarnetListener(this));

    }


    public void wypelnijTabele() {
        // table.add
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
                bundle.getString("nr.karnetu"),
                bundle.getString("nazwa.karnetu"),
                bundle.getString("osoba"),
                bundle.getString("od"),
                bundle.getString("do"),
        });


        baza.utworzPolaczenie();
        listaKarnetow.clear();
        boolean znaleziono = false;

        try {
            baza.myStm = baza.myCon.createStatement();
            baza.myRs = baza.myStm.executeQuery("select karnety.Idkarnetu,karnety.NrKlienta,karnety.Nazwakarnetu, klient.Imie, klient.Nazwisko, karnety.Od, karnety.Do\n" +
                    "from karnety, klient\n" +
                    "where karnety.NrKlienta = klient.NrKlienta");

            while (baza.myRs.next()) {
                znaleziono = true;
                String nrKarnetu = baza.myRs.getString("IdKarnetu");
                String nrKlienta = baza.myRs.getString("NrKlienta");
                String imie = baza.myRs.getString("Imie");
                String nazwaKarnetu = baza.myRs.getString("Nazwakarnetu");
                String nazwisko = baza.myRs.getString("Nazwisko");
                String dataOd = baza.myRs.getString("Od");
                String dataDo = baza.myRs.getString("Do");

                model.addRow(new Object[]{nrKarnetu, nazwaKarnetu, (imie + " " + nazwisko), dataOd, dataDo});
                listaKarnetow.add(new Karnet(Integer.parseInt(nrKarnetu), Integer.parseInt(nrKlienta), nazwaKarnetu, dataOd, dataDo));

                //System.out.println(country + " " + sum);
                //
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
        table.setModel(model);
        table.getColumnModel().getColumn(1).setPreferredWidth(table.getColumnModel().getColumn(1).getWidth() + 30);


    }


    @Override
    public void aktualizacjaEtykiet() {
        bundle = ResourceBundle.getBundle("messages");
        buttonDodajKarnet.setText(bundle.getString("dodaj.karnet"));
        buttonWyswietlKarnety.setText(bundle.getString("wyswietl.karnety"));
        karnetyLabel.setText(bundle.getString("karnety"));
        model.setColumnIdentifiers(new Object[]{
                bundle.getString("nr.karnetu"),
                bundle.getString("nazwa.karnetu"),
                bundle.getString("osoba"),
                bundle.getString("od"),
                bundle.getString("do"),
        });
    }
}