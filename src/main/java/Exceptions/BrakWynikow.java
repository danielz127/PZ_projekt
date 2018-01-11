package Exceptions;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BrakWynikow extends Exception {
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public BrakWynikow() {
        logr.info("Brak wynikow wyszukiwania");
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                bundle.getString("exeption.brakWynikow"),
                bundle.getString("error.message"),
                JOptionPane.WARNING_MESSAGE);
    }
}
