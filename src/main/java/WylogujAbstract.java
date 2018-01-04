import javax.swing.*;
import java.awt.event.ActionEvent;

public class WylogujAbstract extends AbstractAction {
    public WylogujAbstract(String tekst, ImageIcon icon) {
        super(tekst, icon);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("Wylogowuje");


    }
}
