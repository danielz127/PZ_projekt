import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJBar extends JMenuBar {

    JMenu file;
    JMenu view;
    JMenuItem exit, language, plaf;
    Frame frame;

    public MenuJBar(Frame frame) {
        file = new JMenu("Plik");
        view = new JMenu("Widok");
        add(file);
        add(view);
        this.frame = frame;

        exit = new JMenuItem("Zamknij");
        language = new JMenuItem("Zmień język");
        plaf = new JMenuItem("Zmień skórkę");
        file.add(exit);
        //file.addSeparator();

        view.add(language);
        view.addSeparator();
        view.add(plaf);

        listenery();


    }

    public void listenery() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);

                frame.dispose();

            }
        });

    }

}
