package Listenery;

import Baza.Baza;
import Main.OknoProgramu;
import PaneleMenu.Szatnia.Szafka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class WindowCloseListener implements ActionListener, WindowListener {

    OknoProgramu frame;
    Baza baza;
    ArrayList<Szafka> meskie;
    ArrayList<Szafka> damskie;

    public WindowCloseListener(OknoProgramu frame) {
        this.frame = frame;
        this.baza = frame.baza;

    }

    public void zamknijOkno() {


        frame.dispose();
        System.exit(0);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        zamknijOkno();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        zamknijOkno();
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