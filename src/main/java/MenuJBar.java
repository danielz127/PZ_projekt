import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuJBar extends JMenuBar {

    JMenu file;
    JMenu view;
    JMenuItem exit, language, plaf;
    Frame frame;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public MenuJBar(Frame frame) {
        file = new JMenu("Plik");
        view = new JMenu("Widok");
        add(file);
        add(view);
        this.frame = frame;

        exit = new JMenuItem("Zamknij");
        language = new JMenuItem(bundle.getString("language.button"));
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
        language.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Locale.setDefault(new Locale("en", "EN"));
                System.out.print(Locale.getDefault());
                bundle = ResourceBundle.getBundle("messages");
                language.setText(bundle.getString("language.button"));

            }
        });

    }

}
