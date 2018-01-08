package Main;

import LoggerPck.LoggerExmpl;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by Daniel on 2017-11-10.
 */
public class Main {

    public static void main(String[] args) {

        LoggerExmpl.setupLogger();


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OknoProgramu();
            }
        });


    }
}
