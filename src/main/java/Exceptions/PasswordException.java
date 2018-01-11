package Exceptions;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PasswordException extends Exception {
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    public PasswordException() {

        JOptionPane optionPane = new JOptionPane();

        logr.info("Nieprawidlowy login lub haslo");
        optionPane.showMessageDialog(null,
                bundle.getString("password.wrong"),
                bundle.getString("error.message"),
                JOptionPane.WARNING_MESSAGE);



    }

}
