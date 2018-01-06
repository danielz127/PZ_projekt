import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrzyciskWMenu extends JButton implements Observer  {
    public PrzyciskWMenu(String text, Icon icon) {
        super(text, icon);

    }

    public PrzyciskWMenu(Action a) {
        super(a);
    }

    @Override
    public void update() {

        setBackground(new Color(213, 237, 218));
    }

}
