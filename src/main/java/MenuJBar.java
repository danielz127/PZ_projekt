import com.google.common.eventbus.EventBus;

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
    ResourceBundle bundle;
    EventBus eventBus;
    WindowCloseListener windowCloseListener;
    public MenuJBar(Frame frame, EventBus eventBus) {


        this.frame = frame;
        this.eventBus = eventBus;


        utworzElementy();
        dodajElementy();
        listenery();


    }

    public void utworzElementy() {
        bundle = ResourceBundle.getBundle("messages");
        windowCloseListener = new WindowCloseListener(frame);
        file = new JMenu(bundle.getString("file.text"));
        view = new JMenu(bundle.getString("view.text"));
        language = new JMenuItem(bundle.getString("language.button"));
        plaf = new JMenuItem("Zmień skórkę");
        exit = new JMenuItem("Zamknij");
    }

    public void dodajElementy() {
        add(file);
        add(view);
        file.add(exit);
        //file.addSeparator();

        view.add(language);
        view.addSeparator();
        view.add(plaf);


    }

    public void listenery() {
        exit.addActionListener(windowCloseListener);

        language.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zmienJezyk();
            }
        });

    }

    public void zmienJezyk() {
        if (jezyk) {
            jezyk = false;
            Locale.setDefault(new Locale("en", "EN"));
            bundle = ResourceBundle.getBundle("messages");
            zmienEtykiety();
        } else {
            jezyk = true;
            Locale.setDefault(new Locale("pl", "Poland"));
            bundle = ResourceBundle.getBundle("messages");
            zmienEtykiety();
        }
        // System.out.print(Locale.getDefault());
        //
    }

    public void zmienEtykiety() {


        frame.setTitle(bundle.getString("app.title"));
        language.setText(bundle.getString("language.button"));
        view.setText(bundle.getString("view.text"));
        file.setText(bundle.getString("file.text"));

        eventBus.post(new EtykietaEvent());


        //tutaj zmienic nazwy wszystkiego :oo
    }

}
