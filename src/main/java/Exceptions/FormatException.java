package Exceptions;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class FormatException extends Exception {
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    public FormatException() {
        logr.info("Zly format wprowadzonych danych");
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                bundle.getString("format.error"),
                bundle.getString("error.message"),
                JOptionPane.WARNING_MESSAGE);
    }

}
