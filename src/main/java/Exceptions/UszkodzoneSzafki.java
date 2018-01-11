package Exceptions;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class UszkodzoneSzafki extends Exception{
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    public UszkodzoneSzafki(int ile) {
        JOptionPane optionPane = new JOptionPane();

        logr.warning("Proba zwolnienia uszkodzonych szafek");
        optionPane.showMessageDialog(null,
                "Nie mozna zwolnic szafek w liczbie: "+ile + ", z powodu uszkodzen. ",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
