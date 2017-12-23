import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-11-13.
 */
public class PanelProgram extends JPanel {
    String miasto;
    JLabel labelMiasto;

    JLabel labelTime;
    GridBagConstraints gbc;
    JPanel panelMenu;
    GridBagLayout gridBagLayout;
    Frame frame;
    ResourceBundle bundle;


    public PanelProgram(Frame frame, String miasto, ResourceBundle bundle) {

        this.miasto = miasto;
        setSize(frame.getSize());
        setVisible(true);
        this.bundle = bundle;
        setBackground(Color.RED);
        guiPanelu();

    }

    public void guiPanelu() {
        //zobaczyc czy frame sie dobrze dodal
        panelMenu = new PanelMenu(frame, miasto, bundle);

        setLayout(new BorderLayout());

        panelMenu.setPreferredSize(new Dimension(200, 700));

        add(panelMenu, BorderLayout.WEST);

        //tutaj drugi panel
        add(new Klienci());

        revalidate();
        repaint();

    }


}
