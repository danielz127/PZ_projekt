package Exceptions;

import javax.swing.*;

public class FormatException extends Exception {

    public FormatException() {
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                "Zly format!",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }

}
