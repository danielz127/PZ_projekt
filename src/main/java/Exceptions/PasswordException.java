package Exceptions;

import javax.swing.*;
import java.awt.*;

public class PasswordException extends Exception {
    JDialog dialog;

    public PasswordException() {

        ImageIcon icon = new ImageIcon("src/main/resources/ikony/warning.png");
        //lub blad
        JOptionPane optionPane = new JOptionPane();

        optionPane.showMessageDialog(null,
                "Nieprawidlowe login lub haslo\n" +
                        "Dane testowe:\n" +
                        "Login: User1, Haslo: User123.",
                "Error",
                JOptionPane.WARNING_MESSAGE);



    }

}
