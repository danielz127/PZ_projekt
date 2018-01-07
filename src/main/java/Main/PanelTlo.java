package Main;

import javax.swing.*;
import java.awt.*;

public class PanelTlo extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image image;


    public PanelTlo() {
        image = Toolkit.getDefaultToolkit().getImage("src/main/resources/silownia_tlo.png");

        repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}