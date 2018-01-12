package PaneleMenu.Szatnia;

import PaneleMenu.Klient.Klient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SzafkaDialog extends JDialog implements WindowListener {
    public Szafka szafka;
    public JTextArea textArea;
    JRadioButton wolna, zajeta, uszkodzona;
    JLabel nrSzafki, stan;
    ArrayList<JComponent> listaElementow;
    ButtonGroup group;
    JButton zapisz;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public SzafkaDialog(Szafka szafka) {
        szafka.szatnia.oknoProgramu.setEnabled(false);
        setAlwaysOnTop(true);
        this.szafka = szafka;
        setTitle(bundle.getString("szafka"));
        listaElementow = new ArrayList<>();
        setResizable(false);
        group = new ButtonGroup();
        setBounds(szafka.szatnia.oknoProgramu.getX() + 50, szafka.szatnia.oknoProgramu.getY() + 50, 350, 400);

        utworzElementy();
        dodajEtykiety();
        dodajDoOkna();
        znajdzKlienta();

        //na koniec
        setVisible(true);
    }

    private void dodajDoOkna() {
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        int y = 0;
        gbc.gridy = y;
        add(nrSzafki);

        for (JComponent rb : listaElementow) {
            gbc.gridy = ++y;
            add(rb, gbc);
        }
        gbc.gridy = ++y;
        gbc.gridx = 3;
        add(zapisz, gbc);

        gbc.gridy = 2;
        gbc.gridx = 3;
        gbc.gridwidth = 5;
        add(textArea, gbc);

    }

    private void utworzElementy() {
        nrSzafki = new JLabel();
        zapisz = new JButton();
        stan = new JLabel();

        wolna = new JRadioButton();
        wolna.setSelected((!szafka.zajeta) && !szafka.uszkodzona);

        zajeta = new JRadioButton();
        zajeta.setSelected(szafka.zajeta);

        uszkodzona = new JRadioButton();
        uszkodzona.setSelected(szafka.uszkodzona);

        textArea = new JTextArea("");
        textArea.setColumns(15);
        textArea.setSize(new Dimension(100, 20));


        listaElementow.add(wolna);
        listaElementow.add(zajeta);
        listaElementow.add(uszkodzona);

        for (JComponent component : listaElementow)
            group.add((AbstractButton) component);
        listenery();


    }

    private void znajdzKlienta() {
        if (szafka.NrKlienta != 0) {
            for (Klient klient : szafka.szatnia.klients) {
                if (klient.NrKlienta == szafka.NrKlienta) {
                    textArea.setText(klient.imie + " " + klient.nazwisko);
                }
            }
        } else textArea.setText("");

    }

    private void wybierzOsobe() {
        new WybierzOsobeSzafka(szafka.szatnia, this);

    }

    private void listenery() {
        addWindowListener(this);
        zajeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybierzOsobe();

            }
        });

        zapisz.addActionListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent e) {
                                         if (wolna.isSelected()) {
                                             szafka.NrKlienta = 0;
                                             textArea.setText("");
                                             szafka.zajeta = false;
                                             szafka.uszkodzona = false;
                                             szafka.dodajIkone();

                                         }

                                         if (zajeta.isSelected()) {
                                             if (!textArea.getText().equals("")) {
                                                 szafka.zajeta = true;
                                                 szafka.uszkodzona = false;
                                                 szafka.dodajIkone();
                                             } else {
                                                 szafka.NrKlienta = 0;
                                                 textArea.setText("");
                                                 szafka.zajeta = false;
                                                 szafka.uszkodzona = false;
                                             }

                                         }

                                         if (uszkodzona.isSelected()) {
                                             szafka.NrKlienta = 0;
                                             textArea.setText("");
                                             szafka.uszkodzona = true;
                                             szafka.zajeta = false;
                                             szafka.dodajIkone();

                                         }
                                         szafka.szatnia.oknoProgramu.setEnabled(true);
                                         dispose();

                                     }

                                 }
        );
    }

    private void dodajEtykiety() {
        czcionka();

        nrSzafki.setText(bundle.getString("szafka.nr") + " " + szafka.NrSzafki);
        zapisz.setText(bundle.getString("zapisz"));
        stan.setText(bundle.getString("stan"));
        wolna.setText(bundle.getString("wolna"));
        zajeta.setText(bundle.getString("zajeta"));
        uszkodzona.setText(bundle.getString("uszkodzona"));


    }

    private void czcionka() {
        Font font = new Font("Courier New", Font.ITALIC, 20);
        nrSzafki.setFont(font);
        zapisz.setFont(font);
        stan.setFont(font);
        for (JComponent button : listaElementow)
            button.setFont(font);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        szafka.szatnia.oknoProgramu.setEnabled(true);
        this.dispose();

    }

    @Override
    public void windowClosed(WindowEvent e) {


    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
