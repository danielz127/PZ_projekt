import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowCloseListener implements ActionListener {

    Frame frame;

    public WindowCloseListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //tutaj dodaj akcje przy zamknięciu okienka

        try {
            frame.dispose();
            System.exit(0);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
