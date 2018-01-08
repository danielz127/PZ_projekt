package Exceptions;

import Listenery.NowyKlientEvent;

import javax.swing.*;
import java.util.logging.Logger;

public class TelefonException extends Exception {
    NowyKlientEvent event;
    private final static Logger logr = Logger.getLogger( Logger.GLOBAL_LOGGER_NAME );
    public TelefonException(NowyKlientEvent event) {
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
