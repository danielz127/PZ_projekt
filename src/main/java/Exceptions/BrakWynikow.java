package Exceptions;

import javax.swing.*;

public class BrakWynikow extends Exception {
    public BrakWynikow() {
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                "Brak wynikow!",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
