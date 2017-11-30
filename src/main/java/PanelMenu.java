import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 2017-11-14.
 */
public class PanelMenu extends JPanel {

    String miasto;
    public PanelMenu(String miasto) {
        setVisible(true);

        setBackground(Color.green);
        this.miasto = miasto;
        stworzMenu();
    }
    public void stworzMenu(){
        GridLayout gridLayout = new GridLayout(20, 2, 0, 5);
        setLayout(gridLayout);

        JLabel labelMiasto = new JLabel();

        labelMiasto.setText(miasto);

        add(labelMiasto);
        JButton buttonKontrahenci = new JButton("Kontrahenci");

        JButton buttonWplaty = new JButton("Wplaty");

        add(buttonKontrahenci);
        add(buttonWplaty);

    }
}
