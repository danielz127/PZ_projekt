import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class PasswordException extends Exception {

    public PasswordException() {
        JFrame ramka = new JFrame();
        ramka.setVisible(true);
        ramka.setSize(250, 100);
        ramka.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Nieprawidłowe hasło lub login");
        ramka.add(label);
        ramka.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
