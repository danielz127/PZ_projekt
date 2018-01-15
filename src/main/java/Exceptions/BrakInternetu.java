package Exceptions;

import PaneleMenu.PanelGl.PanelMenu;

import javax.swing.*;
import java.util.*;

public class BrakInternetu extends Exception {

    static GregorianCalendar calendar;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public BrakInternetu() {
        JOptionPane optionPane = new JOptionPane();

        optionPane.showMessageDialog(null,
                bundle.getString("internet.error"),
                bundle.getString("error.message"),
                JOptionPane.WARNING_MESSAGE);


    }

    public static GregorianCalendar zwrocKalendarz() {
        calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return calendar;
    }
}
