import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 2017-11-14.
 */
public class PanelMenu extends JPanel {

    String miasto;
    JLabel czasSieciowy;
    PodawajGodzine sprawdzGodzine;
    Frame frame;

    public PanelMenu(String miasto, Frame frame) {
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

        JButton buttonKontrahenci = new JButton("Kontrahenci");

        JButton buttonWplaty = new JButton("Wplaty");

        JButton buttonSzatnie = new JButton(("Szatnie"));

        add(buttonSzatnie);
        add(buttonKontrahenci);
        add(buttonWplaty);

    }
}
