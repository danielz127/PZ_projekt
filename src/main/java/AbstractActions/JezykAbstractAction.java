package AbstractActions;

import Main.OknoProgramu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;


public class JezykAbstractAction extends AbstractAction {
    OknoProgramu oknoProgramu;
    boolean jezyk;



    public JezykAbstractAction(String text, ImageIcon icon, OknoProgramu oknoProgramu) {
        super(text, icon);

        jezyk = true;
        this.oknoProgramu = oknoProgramu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("Zmien jezyk");
        zmienJezyk();
        zmienEtykiety();


    }
    public void zmienJezyk() {
        if (jezyk) {
            jezyk = false;
            Locale.setDefault(new Locale("en", "EN"));


        } else {
            jezyk = true;
            Locale.setDefault(new Locale("pl", "Poland"));


        }
         System.out.print(Locale.getDefault());
        //
    }


    public void zmienEtykiety() {
        oknoProgramu.aktualizacjaEtykiet();
        if(oknoProgramu.menuJBar!=null)oknoProgramu.menuJBar.aktualizacjaEtykiet();
        if(oknoProgramu.panelMenu!= null)oknoProgramu.panelMenu.dodajEtykiety();

    }
}
