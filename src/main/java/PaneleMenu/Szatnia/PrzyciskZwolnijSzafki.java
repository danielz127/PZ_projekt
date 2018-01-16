package PaneleMenu.Szatnia;

import Dzialania.ZwolnijSzafki;

import javax.swing.*;
import java.awt.*;

public class PrzyciskZwolnijSzafki extends JButton   {
    Szatnia szatnia;

    public PrzyciskZwolnijSzafki(String tekst, Szatnia szatnia) {
        super(tekst);
        this.szatnia=szatnia;

        addActionListener(new ZwolnijSzafki(szatnia));
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
    }

}
