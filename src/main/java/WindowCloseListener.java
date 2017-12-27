import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class WindowCloseListener implements ActionListener {

    Frame frame;

    public WindowCloseListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //tutaj dodaj akcje przy zamknięciu okienka

        System.out.print("Zamykam okienko");

        try {
            frame.dispose();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
