package Exceptions;

import javax.swing.*;
import java.awt.*;

public class PasswordException extends Exception {

    public PasswordException() {

        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                "Nieprawidlowe login lub haslo\n" +
                        "Dane testowe:\n" +
                        "Login: User1, Haslo: User123.",
                "Error",
                JOptionPane.WARNING_MESSAGE);



    }

}
