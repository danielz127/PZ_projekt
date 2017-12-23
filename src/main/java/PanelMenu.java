import com.google.common.eventbus.Subscribe;
import com.sun.xml.internal.fastinfoset.stax.events.EventBase;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-14.
 */
public class PanelMenu extends JPanel {

    String miasto;
    JLabel czasSieciowy;
    PodawajGodzine sprawdzGodzine;
    Frame frame;
    JButton buttonWyloguj, buttonKlienci, buttonWplaty;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    JButton buttonSzatnie;



    public PanelMenu(Frame frame, String miasto, ResourceBundle bundle) {
        setVisible(true);
        sprawdzGodzine = new PodawajGodzine();
        this.frame = frame;


        setBackground(Color.green);
        this.miasto = miasto;
        stworzMenu();
    }

    public void stworzMenu() {
        GridLayout gridLayout = new GridLayout(20, 2, 0, 5);
        setLayout(gridLayout);
        czasSieciowy = new JLabel();
        SwingWorker<Void, Void> godzina = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                czasSieciowy.setText("Zalogowano: " + sprawdzGodzine.podajGodzine());
                return null;
            }
        };
        godzina.execute();

        JLabel labelMiasto = new JLabel();


        labelMiasto.setText(miasto);

        add(labelMiasto);
        add(czasSieciowy);

        stworzPrzyciski();
        dodajEtykiety();
        dodajPrzyciski();

    }
    @Subscribe
    public void onEtykietaEvent(EtykietaEvent event){
        dodajEtykiety();
    }

    public void stworzPrzyciski(){
        buttonSzatnie = new JButton(("Szatnie"));
        buttonKlienci = new JButton("Klienci");
        buttonWplaty = new JButton("Wplaty");
        buttonWyloguj = new JButton(bundle.getString("button.logowanie"));
    }

    public void dodajEtykiety() {
        bundle = ResourceBundle.getBundle("messages");
        System.out.print(Locale.getDefault());
        buttonWyloguj.setText(bundle.getString("button.logowanie"));

    }
    public void dodajPrzyciski() {
        add(buttonSzatnie);
        add(buttonKlienci);
        add(buttonWplaty);
        add(buttonWyloguj);

    }
}
