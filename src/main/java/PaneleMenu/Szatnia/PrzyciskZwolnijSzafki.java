package PaneleMenu.Szatnia;

import Listenery.ZwolnijSzafkiListener;
import PaneleMenu.Szatnia.Szatnia;

import javax.swing.*;
import java.awt.*;

public class PrzyciskZwolnijSzafki extends JButton   {
    Szatnia szatnia;

    public PrzyciskZwolnijSzafki(String tekst, Szatnia szatnia) {
        super(tekst);
        this.szatnia=szatnia;

        addActionListener(new ZwolnijSzafkiListener(szatnia));
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
    }

}
