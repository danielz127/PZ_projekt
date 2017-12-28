import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZmienPanelListener implements ActionListener {
    PanelMenu panelMenu;
    JPanel panelWstaw;

    public ZmienPanelListener(PanelMenu panelMenu, JPanel panelWstaw) {
        this.panelMenu = panelMenu;
        this.panelWstaw = panelWstaw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panelMenu.wygasPanele();
        panelWstaw.setVisible(true);
        panelMenu.frame.add(panelWstaw);
        panelMenu.frame.pack();
        // panelMenu.frame.setSize(new Dimension(panelMenu.frame.getWidth(), panelMenu.frame.getHeight()));


    }
}
