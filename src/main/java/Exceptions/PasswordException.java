package Exceptions;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class PasswordException extends Exception {
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    public PasswordException() {

        JOptionPane optionPane = new JOptionPane();

        logr.info("Nieprawidlowy login lub haslo");
        optionPane.showMessageDialog(null,
                "Nieprawidlowe login lub haslo\n" +
                        "Dane testowe:\n" +
                        "Login: User1, Haslo: User123.",
                "Error",
                JOptionPane.WARNING_MESSAGE);



    }

}
