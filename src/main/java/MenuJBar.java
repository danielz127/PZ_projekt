import javax.swing.*;
import java.util.ResourceBundle;

public class MenuJBar extends JMenuBar {

    JMenu file;
    JMenu view;
    JMenuItem exit, language, plaf;
    OknoProgramu frame;
    ResourceBundle bundle;
    WindowCloseListener windowCloseListener;

    public MenuJBar(OknoProgramu frame, ResourceBundle bundle) {

        this.bundle = bundle;
        this.frame = frame;


        utworzElementy();
        dodajElementy();
        listenery();


    }

    public void utworzElementy() {

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

        language.addActionListener(new JezyklListener(frame));
    }


    public void zmienEtykiety() {

        bundle = ResourceBundle.getBundle("messages");
        frame.setTitle(bundle.getString("app.title"));
        language.setText(bundle.getString("language.button"));
        view.setText(bundle.getString("view.text"));
        file.setText(bundle.getString("file.text"));

        //tutaj zmienic nazwy wszystkiego :oo
    }

}
