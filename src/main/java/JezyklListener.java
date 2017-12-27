import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;


public class JezyklListener implements ActionListener {
    OknoProgramu oknoProgramu;
    boolean jezyk;



    public JezyklListener(OknoProgramu oknoProgramu) {

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
        oknoProgramu.aktualizujEtykiety();
        if(oknoProgramu.menuJBar!=null)oknoProgramu.menuJBar.zmienEtykiety();
        if(oknoProgramu.panelMenu!= null)oknoProgramu.panelMenu.dodajEtykiety();

    }
}
