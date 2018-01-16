package Exceptions;

import Dzialania.NowyKlient;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class TelefonException extends Exception {
    NowyKlient event;
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    public TelefonException(NowyKlient event) {
        this.event = event;
        event.telefon="";
        logr.warning("Nieprawidlowy format telefonu");
        wywolajMenu();
    }

    private void wywolajMenu() {
        JOptionPane optionPane = new JOptionPane();

        optionPane.showMessageDialog(null,
                "Nieprawidlowy format/dlugosc telefonu",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
