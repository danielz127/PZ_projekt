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
    boolean jezyk = true;
    ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public MenuJBar(Frame frame) {
        file = new JMenu(bundle.getString("file.text"));
        view = new JMenu(bundle.getString("view.text"));
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
                if (jezyk) {
                    jezyk = false;
                    Locale.setDefault(new Locale("en", "EN"));
                    bundle = ResourceBundle.getBundle("messages");
                    zmienJezyk();
                } else {
                    jezyk = true;
                    Locale.setDefault(new Locale("pl", "Poland"));
                    bundle = ResourceBundle.getBundle("messages");
                    zmienJezyk();
                }
                System.out.print(Locale.getDefault());
                //

            }
        });

    }

    public void zmienJezyk() {

        frame.setTitle(bundle.getString("app.title"));
        language.setText(bundle.getString("language.button"));
        view.setText(bundle.getString("view.text"));
        file.setText(bundle.getString("file.text"));
        //tutaj zmienic nazwy wszystkiego :oo
    }

}
