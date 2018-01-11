package PaneleMenu.Karnety;

import Baza.Baza;
import Main.OknoProgramu;
import PaneleMenu.Szatnia.Szatnia;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class NowyKarnetDialog extends JDialog implements WindowListener {
    public Szatnia szatnia;
    public JTextArea textAreaOsoba;
    JComboBox listaNazw;
    JTextArea dataDo;
    JLabel osoba, dlugosc, dataOd, dataDoLbl;
    JDateChooser dateChooser;
    Date now;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    TimeZone pdt = TimeZone.getDefault();
    Calendar calendar = new GregorianCalendar(pdt);

    int nrKlienta;
    int nrKarnetu;

    OknoProgramu oknoProgramu;

    JButton wybierz;
    Baza baza;

    public NowyKarnetDialog(OknoProgramu oknoProgramu) {
        this.oknoProgramu = oknoProgramu;
        this.baza = oknoProgramu.baza;
        oknoProgramu.setEnabled(false);
        setAlwaysOnTop(true);

        setTitle("Karnet");
        setResizable(false);
        setBounds(oknoProgramu.getX() + 50, oknoProgramu.getY() + 50, 600, 105);
        addWindowListener(this);
        utworzElementy();
        dodajEtykiety();
        dodajDoOkna();
        listenery();


        //na koniec
        setVisible(true);
    }

    private void listenery() {
        textAreaOsoba.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                wybierzOsobe();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        wybierz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textAreaOsoba.getText().equals(""))
                    zapiszNowyKarnet();
            }
        });
        dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                zmienKoncowaDate();
            }
        });
        listaNazw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zmienKoncowaDate();

            }
        });
    }

    private void zapiszNowyKarnet() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                baza.utworzPolaczenie();
                try {
                    baza.myStm = baza.myCon.createStatement();
                    baza.myRs = baza.myStm.executeQuery("select max(karnety.Idkarnetu) as NrKarnetu FROM karnety");

                    while (baza.myRs.next()) {
                        nrKarnetu = Integer.parseInt(baza.myRs.getString("NrKarnetu")) + 1;
                    }

                    baza.myStm.executeQuery("SET FOREIGN_KEY_CHECKS=0");

                    String insertTableSQL = "INSERT INTO karnety"
                            + "(Idkarnetu, NrKlienta, Nazwakarnetu, Od, Do) VALUES"
                            + "(?,?,?,?,?)";

                    String nazwakarnetuTekst = listaNazw.getSelectedItem().toString();
                    String dataOdTekst = format.format(dateChooser.getDate());
                    String dataDoText = dataDo.getText();

                    PreparedStatement preparedStatement = baza.myCon.prepareStatement(insertTableSQL);
                    preparedStatement.setInt(1, nrKarnetu);
                    preparedStatement.setInt(2, nrKlienta);
                    preparedStatement.setString(3, nazwakarnetuTekst);
                    preparedStatement.setDate(4, java.sql.Date.valueOf(dataOdTekst));
                    preparedStatement.setDate(5, java.sql.Date.valueOf(dataDoText));
                    preparedStatement.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }


                //System.out.println(nazwakarnetuTekst + dataOdTekst + dataDoText);

                baza.rozlaczBaze();
                oknoProgramu.setEnabled(true);
                dispose();
                return null;
            }
        };
        worker.execute();

    }

    private void utworzElementy() {

        dateChooser = new JDateChooser();
        dateChooser.setSize(dateChooser.getWidth() + 20, dateChooser.getHeight());
        dateChooser.setDate(calendar.getTime());
        dateChooser.setPreferredSize(new Dimension(150, 30));
        textAreaOsoba = new JTextArea("");
        textAreaOsoba.setColumns(15);

        listaNazw = new JComboBox(new Object[]{"Tygodniowy", "30 dniowy", "90 dniowy"});
        dataDo = new JTextArea("");
        dataDo.setEnabled(false);
        dataDo.setColumns(10);

        wybierz = new JButton("Zapisz");


    }

    public void zmienKoncowaDate() {


        Date date = null;
        date = dateChooser.getDate();

        calendar.setTime(date);


        //0 - 7 dni, 1, 30, 2, 90
        if (listaNazw.getSelectedIndex() == 0) {
            calendar.add(Calendar.DATE, 7);


        }
        if (listaNazw.getSelectedIndex() == 1) {
            calendar.add(Calendar.DATE, 30);


        }
        if (listaNazw.getSelectedIndex() == 2) {
            calendar.add(Calendar.DATE, 90);


        }
        now = calendar.getTime();
        String strDate = format.format(now);
        dataDo.setText(strDate);
    }

    private void dodajDoOkna() {

        setLayout(new FlowLayout());

        add(textAreaOsoba);
        add(listaNazw);
        add(dateChooser);
        add(dataDo);
        add(wybierz);


    }


    private void wybierzOsobe() {
        new WybierzOsobeKarnet(oknoProgramu.panelMenu.karnety, this);

    }


    private void dodajEtykiety() {
        czcionka();


    }

    private void czcionka() {
        Font font = new Font("Courier New", Font.ITALIC, 20);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        oknoProgramu.setEnabled(true);
        dispose();


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
