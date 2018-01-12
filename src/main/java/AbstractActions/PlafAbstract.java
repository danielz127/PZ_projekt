package AbstractActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;


public class PlafAbstract extends AbstractAction {
    int wersja;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public PlafAbstract(java.lang.String tekst, ImageIcon icon, int wersja) {
        super(tekst, icon);
        this.wersja = wersja;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        switch (wersja) {
            case 1:
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    odswierzOkna();
                } catch (ClassNotFoundException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    odswierzOkna();
                } catch (ClassNotFoundException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                }
                break;

            case 3:
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    odswierzOkna();
                } catch (ClassNotFoundException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    logr.info("Blad");
                    e.printStackTrace();
                }
                break;
        }





    }

    private void odswierzOkna() {
        for (Window window : JFrame.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
    }
}
