package Exceptions;

import javax.swing.*;
import java.util.logging.Logger;

public class FormatException extends Exception {
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );

    public FormatException() {
        JOptionPane optionPane = new JOptionPane();
        logr.info("Zly format wprowadzonych danych");

        optionPane.showMessageDialog(null,
                "Zly format!",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }

}
