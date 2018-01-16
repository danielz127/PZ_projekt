package Dzialania;

import Baza.Baza;
import Main.OknoProgramu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ZamykanieOkna implements ActionListener, WindowListener {

    OknoProgramu frame;
    Baza baza;


    public ZamykanieOkna(OknoProgramu frame) {
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