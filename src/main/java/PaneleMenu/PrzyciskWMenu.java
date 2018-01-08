package PaneleMenu;

import Interfejsy.Observer;

import javax.swing.*;
import java.awt.*;

public class PrzyciskWMenu extends JButton implements Observer {
    public PrzyciskWMenu(String text, Icon icon) {
        super(text, icon);
        setPreferredSize(new Dimension(130, 35));
        setBorderPainted(true);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setOpaque(true);

    }

    public PrzyciskWMenu(Action a) {
        super(a);
    }



    @Override
    public void update() {
        setBackground(new Color(213, 237, 218));


    }

}
