package PaneleMenu.Karnety;

import Baza.Baza;
import Main.OknoProgramu;
import PaneleMenu.Szatnia.Szatnia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class NowyKarnetDialog extends JDialog implements WindowListener {
    public Szatnia szatnia;
    public JTextArea textArea;
    JComboBox listaNazw;
    JFormattedTextField txtDate;
    JTextArea dataDo;
    JLabel osoba, dlugosc, dataOd, dataDoLbl;

    TimeZone pdt = TimeZone.getDefault();
    Calendar calendar = new GregorianCalendar(pdt);

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
        setBounds(oknoProgramu.getX() + 50, oknoProgramu.getY() + 50, 550, 105);
        addWindowListener(this);
        utworzElementy();
        dodajEtykiety();
        dodajDoOkna();
        listenery();


        //na koniec
        setVisible(true);
    }

    private void listenery() {
        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setEnabled(false);
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
                zmienKoncowaDate();
            }
        });
    }

    private void utworzElementy() {
        osoba = new JLabel("Wybierz osobe");
        dlugosc = new JLabel("Wybierz dlugosc");
        dataOd = new JLabel("Poczatek");
        dataDoLbl = new JLabel("Koniec");

        textArea = new JTextArea();
        textArea.setAlignmentY(2);
        textArea.setColumns(15);
        listaNazw = new JComboBox(new Object[]{"Tygodniowy", "30 dniowy", "90 dniowy"});
        dataDo = new JTextArea("aa");
        dataDo.setEnabled(false);

        dataDo.setColumns(10);
        wybierz = new JButton("Zapisz");
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        txtDate = new JFormattedTextField(df);
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        txtDate.setColumns(10);

    }

    public void zmienKoncowaDate() {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(txtDate.getText());
        } catch (ParseException e) {
           //TODO format daty
        }

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
        Date now = calendar.getTime();
        String strDate = format.format(now);
        System.out.println(strDate);
        dataDo.setText(strDate);
    }

    private void dodajDoOkna() {

        setLayout(new FlowLayout());

        add(textArea);
        add(listaNazw);
        add(txtDate);
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
        dispose();
        oknoProgramu.setEnabled(true);

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
