package Exceptions;

import javax.swing.*;

public class UszkodzoneSzafki extends Exception{
    public UszkodzoneSzafki(int ile) {
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                "Nie mozna zwolnic szafek w liczbie: "+ile + ", z powodu uszkodzen. ",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
