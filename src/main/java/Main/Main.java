package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 2017-11-10.
 */
public class Main {
    public static void main(String[] args) {
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
