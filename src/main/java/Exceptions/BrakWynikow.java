package Exceptions;

import javax.swing.*;
import java.util.logging.Logger;

public class BrakWynikow extends Exception {
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );

    public BrakWynikow() {
        logr.info("Brak wynikow wyszukiwania");
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                "Brak wynikow!",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
