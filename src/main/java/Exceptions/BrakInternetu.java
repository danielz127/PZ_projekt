package Exceptions;

import PaneleMenu.PanelGl.PanelMenu;

import javax.swing.*;
import java.util.*;

public class BrakInternetu extends Exception {
    PanelMenu panelMenu;
    GregorianCalendar calendar;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public BrakInternetu() {
        JOptionPane optionPane = new JOptionPane();


        optionPane.showMessageDialog(null,
                bundle.getString("internet.error"),
                bundle.getString("error.message"),
                JOptionPane.WARNING_MESSAGE);

        this.panelMenu = panelMenu;

    }

    public GregorianCalendar zwrocKalendarz() {
        SimpleTimeZone pdt = (SimpleTimeZone) SimpleTimeZone.getDefault();
        calendar = new GregorianCalendar(pdt);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return calendar;
    }
}
