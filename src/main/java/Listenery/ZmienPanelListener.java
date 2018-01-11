package Listenery;

import PaneleMenu.PanelGl.PanelMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZmienPanelListener implements ActionListener {
    PanelMenu panelMenu;
    JPanel panelWstaw;
    JButton button;

    public ZmienPanelListener(PanelMenu panelMenu, JPanel panelWstaw, JButton button) {
        this.panelMenu = panelMenu;
        this.button = button;
        this.panelWstaw = panelWstaw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panelMenu.wygasPanele();
        panelWstaw.setVisible(true);
        panelMenu.frame.add(panelWstaw);
        panelMenu.notifyObservers();
        button.setBackground(new Color(238, 225, 159));
        panelMenu.frame.pack();
        // panelMenu.frame.setSize(new Dimension(panelMenu.frame.getWidth(), panelMenu.frame.getHeight()));


    }
}
