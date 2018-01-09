package PaneleMenu.Edycja;

import Baza.Baza;
import Main.OknoProgramu;

import javax.swing.*;
import java.awt.*;

public class Edycja extends JPanel {
    Baza baza;
    OknoProgramu oknoProgramu;
    public Edycja(OknoProgramu oknoProgramu) {
        setBackground(Color.BLUE);
        this.oknoProgramu = oknoProgramu;
        this.baza = oknoProgramu.baza;
        setPreferredSize(new Dimension(600, 500));
    }



}
