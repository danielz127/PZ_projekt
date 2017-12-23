import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 2017-11-13.
 */
public class PanelProgram extends JPanel {
    String miasto;
    JLabel labelMiasto;
    Frame parent;
    JLabel labelTime;
    GridBagConstraints gbc;
    JPanel panelMenu;
    GridBagLayout gridBagLayout;
    Frame frame;


    public PanelProgram(Frame frame, String miasto) {

        this.miasto = miasto;
        setSize(frame.getSize());
        setVisible(true);

        setBackground(Color.RED);
        guiPanelu();

    }

    public void guiPanelu() {
        //zobaczyc czy frame sie dobrze dodal
        panelMenu = new PanelMenu(miasto);

        setLayout(new BorderLayout());

        panelMenu.setPreferredSize(new Dimension(200, 700));

        add(panelMenu, BorderLayout.WEST);

        //tutaj drugi panel
        add(new Klienci());

        revalidate();
        repaint();

    }


}
