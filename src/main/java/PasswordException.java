import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class PasswordException extends Exception {

    public PasswordException() {
        JFrame ramka = new JFrame();
        ramka.setVisible(true);
        ramka.setSize(250, 100);
        ramka.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Nieprawidłowe hasło lub login");
        ramka.add(label);
        ramka.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                ramka.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

    }

}
