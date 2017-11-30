

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

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

    public PanelProgram(Frame parent, String miasto) {

        setSize(parent.getSize());
        setVisible(true);
        this.parent = parent;
        this.miasto = miasto;

        panelMenu = new PanelMenu(miasto);
        System.out.println(podajGodzine());

        setBackground(Color.RED);
        guiPanelu();

    }

    public void guiPanelu() {

    setLayout(new BorderLayout( ));




        panelMenu.setPreferredSize(new Dimension(200, 700));



        add(panelMenu, BorderLayout.WEST);

        add(new Kontrahenci());
        revalidate();
        repaint();

    }

    public String podajGodzine() {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return (sdf.format(CzasSieciowy.getAtomicTime().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
