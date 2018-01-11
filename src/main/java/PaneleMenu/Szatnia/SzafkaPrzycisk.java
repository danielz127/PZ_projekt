package PaneleMenu.Szatnia;

import javax.swing.*;

public class SzafkaPrzycisk extends JButton {
    //brzydko ale szybko
    public static ImageIcon iconZajeta = new ImageIcon("src/main/resources/ikony/szatnia/lockerR4.png");
    public static ImageIcon iconWolna = new ImageIcon("src/main/resources/ikony/szatnia/lockerG3.png");


    public SzafkaPrzycisk() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public void zwolnijSzafkie() {
        setIcon(iconWolna); }

    ;

    public void zajmijSzafkie() {
        setIcon(iconZajeta);
    }

    ;
}
