import javax.swing.*;
import java.util.ResourceBundle;

public class MenuJBar extends JMenuBar {

    JMenu file;
    JMenu view;
    JMenuItem exit, language, plaf, plaf1, plaf2, plaf3, wyloguj;
    OknoProgramu frame;
    ResourceBundle bundle;
    WindowCloseListener windowCloseListener;
    Action changeLanguageAction;

    public MenuJBar(OknoProgramu frame, ResourceBundle bundle) {

        this.bundle = bundle;
        this.frame = frame;


        utworzElementy();
        dodajElementy();
        utworzAkcje();
        listenery();


    }

    private void utworzAkcje() {
       // changeLanguageAction = new JezykAbstractAction(frame);
    }



    public void utworzElementy() {

        windowCloseListener = new WindowCloseListener(frame);
        file = new JMenu(bundle.getString("file.text"));
        view = new JMenu(bundle.getString("view.text"));
        language = new JMenuItem(new JezykAbstractAction(bundle.getString("language.button"), new ImageIcon("src/main/resources/ikony/languageIcon.png"), frame));
//        plaf = new JMenu("Zmień skórkę", new ImageIcon("src/main/resources/ikony/zmiana1.png"));
        plaf = new JMenu("Zmien widok");
        wyloguj = new JMenuItem(new WylogujAbstract(bundle.getString("button.wylogowanie"), new ImageIcon("src/main/resources/ikony/wyloguj.png")));
        plaf1 = new JMenuItem(new PlafAbstract(bundle.getString("plaf.tekst1"),new ImageIcon("src/main/resources/ikony/widok.png" ),1));
        plaf2 = new JMenuItem(new PlafAbstract(bundle.getString("plaf.tekst2"),new ImageIcon("src/main/resources/ikony/widok.png" ),2));
        plaf3 = new JMenuItem(new PlafAbstract(bundle.getString("plaf.tekst3"),new ImageIcon("src/main/resources/ikony/widok.png" ),3));
        exit = new JMenuItem("Zamknij", new ImageIcon("src/main/resources/ikony/zamknij.png"));
    }

    public void dodajElementy() {
        add(file);
        add(view);
        file.add(wyloguj);
        file.addSeparator();
        file.add(exit);


        view.add(language);
        view.addSeparator();
        view.add(plaf);


        plaf.add(plaf1);

        plaf.add(plaf2);
        plaf.add(plaf3);


    }

    public void listenery() {
        exit.addActionListener(windowCloseListener);

        //language.addActionListener(new JezykAbstractAction(frame));


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
