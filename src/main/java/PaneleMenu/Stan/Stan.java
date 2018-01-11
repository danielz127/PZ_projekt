package PaneleMenu.Stan;

import Baza.Baza;
import Interfejsy.AktualizacjaEtykiet;
import Main.OknoProgramu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Stan extends JPanel implements AktualizacjaEtykiet {
    OknoProgramu oknoProgramu;
    Baza baza;
    JButton zaktualizuj;
    JList<Magazyn> listaMagazyn;
    JList<Wplata> listaWplat;
    JLabel nazwaSilowni;
    JLabel wplataLbl;
    JLabel magazynLbl;
    JTextArea tekstMagazyn, tekstWplata;
    JScrollPane pane1, pane2;
    DefaultListModel<Wplata> modelWplat;
    DefaultListModel<Magazyn> modelMagazyn;

    public Stan(OknoProgramu oknoProgramu) {
        this.oknoProgramu = oknoProgramu;
        this.baza = oknoProgramu.baza;
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(300, 400));

        utworzElementy();
        dodajElementy();
        listenery();

    }

    private void listenery() {
        zaktualizuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaMagazyn.clearSelection();
                listaWplat.clearSelection();
                pobierzWartosci();
            }
        });
        listaWplat.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Wplata w = listaWplat.getSelectedValue();
                if (w != null)
                    tekstWplata.setText("Kowta: " + w.kwotaWplaty + ", data: " + w.dataWplaty);
                else tekstWplata.setText("");
            }
        });

        listaMagazyn.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                Magazyn m = listaMagazyn.getSelectedValue();
                if (m != null)
                    tekstMagazyn.setText("Ilosc: " + m.getIlosc());
                else
                    tekstMagazyn.setText("");
            }
        });
    }

    private void pobierzWartosci() {
        modelWplat = new DefaultListModel<>();
        modelMagazyn = new DefaultListModel<>();

        try {
            baza.utworzPolaczenie();


            baza.myStm = baza.myCon.createStatement();


            baza.myRs = baza.myStm.executeQuery("select magazyn.Urzadzenie, magazyn.Ilosc from magazyn");

            while (baza.myRs.next()) {
                String urzadzenie = baza.myRs.getString("Urzadzenie");
                int ilosc = baza.myRs.getInt("Ilosc");
                modelMagazyn.addElement(new Magazyn(urzadzenie, ilosc));

            }

            baza.myRs = baza.myStm.executeQuery("select wplaty.tytul, wplaty.Kwota, wplaty.Data\n" +
                    "FROM wplaty");
            while (baza.myRs.next()) {
                String tytul = baza.myRs.getString("Tytul");
                int kwota = baza.myRs.getInt("Kwota");
                String data = baza.myRs.getString("Data");

                modelWplat.addElement(new Wplata(tytul, kwota, data));
            }
            listaMagazyn.setModel(modelMagazyn);
            listaWplat.setModel(modelWplat);
            baza.rozlaczBaze();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void utworzElementy() {

        zaktualizuj = new JButton("Aktualizuj");
        Font font = new Font(getFont().getName(), getFont().getStyle(), 20);
        nazwaSilowni = new JLabel();
        nazwaSilowni.setFont(font);
        nazwaSilowni.setText(oknoProgramu.silowniaNazwa);
        magazynLbl = new JLabel("Magazyn");
        wplataLbl = new JLabel("Wplaty");

        tekstMagazyn = new JTextArea();
        tekstMagazyn.setEnabled(false);
        tekstMagazyn.setColumns(20);
        tekstWplata = new JTextArea();
        tekstWplata.setColumns(20);
        tekstWplata.setEnabled(false);

        listaMagazyn = new JList(){
            @Override
            public void setSelectionBackground(Color selectionBackground) {
                super.setSelectionBackground(new Color(200, 200, 233));
            }
        };
        listaWplat = new JList(){
            @Override
            public void setSelectionBackground(Color selectionBackground) {
                super.setSelectionBackground(new Color(204, 221, 65));
            }


        };
        zmianaCzcionki();

    }

    private void zmianaCzcionki() {
        Font font = new Font(getFont().getName(), getFont().getStyle(), 17);
        wplataLbl.setFont(font);
        magazynLbl.setFont(font);
    }

    private void dodajElementy() {
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        add(nazwaSilowni, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 10;
        add(magazynLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        add(tekstWplata, gbc);

        pane1 = new JScrollPane(listaMagazyn);
        pane1.setPreferredSize(new Dimension(250, 150));
        add(new JScrollPane(pane1), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 10;
        add(tekstMagazyn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 10;
        add(wplataLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 10;
        add(tekstWplata, gbc);
        pane2 = new JScrollPane(listaWplat);
        pane2.setPreferredSize(new Dimension(250, 150));
        add(new JScrollPane(pane2), gbc);


        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 10;
        add(tekstWplata, gbc);


        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 10;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        add(zaktualizuj, gbc);

    }


    @Override
    public void aktualizacjaEtykiet() {

    }
}
