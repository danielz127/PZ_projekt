package AbstractActions;

import Main.OknoProgramu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class WylogujAbstract extends AbstractAction {
    OknoProgramu oknoProgramu;
    ResourceBundle bundle;
    public WylogujAbstract(String tekst, ImageIcon icon, OknoProgramu oknoProgramu) {
        super(tekst, icon);
        this.oknoProgramu = oknoProgramu;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("Wylogowuje");
        oknoProgramu.dispose();

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OknoProgramu();
            }
        });

    }
}
