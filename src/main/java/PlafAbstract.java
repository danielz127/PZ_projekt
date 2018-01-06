import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class PlafAbstract extends AbstractAction {
    int wersja;

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
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;

            case 2:
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    odswierzOkna();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    odswierzOkna();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
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
