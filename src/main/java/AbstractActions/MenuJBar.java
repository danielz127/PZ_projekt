package AbstractActions;

import AbstractActions.JezykAbstractAction;
import AbstractActions.PlafAbstract;
import AbstractActions.WylogujAbstract;
import Interfejsy.AktualizacjaEtykiet;
import Listenery.WindowCloseListener;
import Main.OknoProgramu;

import javax.swing.*;
import java.util.ResourceBundle;

public class MenuJBar extends JMenuBar implements AktualizacjaEtykiet {

    JMenu file;
    JMenu view;
    JMenuItem exit, language, plaf, plaf1, plaf2, plaf3, wyloguj;
    OknoProgramu frame;
    ResourceBundle bundle;
    WindowCloseListener windowCloseListener;


    public MenuJBar(OknoProgramu frame, ResourceBundle bundle) {

        this.bundle = bundle;
        this.frame = frame;
        windowCloseListener = new WindowCloseListener(frame);

        utworzElementy();
        dodajElementy();
        utworzAkcje();
        listenery();


    }

    private void utworzAkcje() {
       // changeLanguageAction = new AbstractActions.JezykAbstractAction(frame);
    }



    public void utworzElementy() {


        file = new JMenu(bundle.getString("file.text"));
        view = new JMenu(bundle.getString("view.text"));
        language = new JMenuItem(new JezykAbstractAction(bundle.getString("language.button"), new ImageIcon("src/main/resources/ikony/languageIcon.png"), frame));
//        plaf = new JMenu("Zmień skórkę", new ImageIcon("src/main/resources/ikony/zmiana1.png"));
        plaf = new JMenu(bundle.getString("zmien.widok"));
        wyloguj = new JMenuItem(new WylogujAbstract(bundle.getString("button.wylogowanie"), new ImageIcon("src/main/resources/ikony/wyloguj.png"), frame) );
        plaf1 = new JMenuItem(new PlafAbstract(bundle.getString("plaf.tekst1"),new ImageIcon("src/main/resources/ikony/widok.png" ),1));
        plaf2 = new JMenuItem(new PlafAbstract(bundle.getString("plaf.tekst2"),new ImageIcon("src/main/resources/ikony/widok.png" ),2));
        plaf3 = new JMenuItem(new PlafAbstract(bundle.getString("plaf.tekst3"),new ImageIcon("src/main/resources/ikony/widok.png" ),3));
        exit = new JMenuItem(bundle.getString("close"), new ImageIcon("src/main/resources/ikony/zamknij.png"));
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

        //language.addActionListener(new AbstractActions.JezykAbstractAction(frame));


    }


    @Override
    public void aktualizacjaEtykiet() {
        bundle = ResourceBundle.getBundle("messages");
        frame.setTitle(bundle.getString("app.title"));
        language.setText(bundle.getString("language.button"));
        view.setText(bundle.getString("view.text"));
        file.setText(bundle.getString("file.text"));
        wyloguj.setText(bundle.getString("button.wylogowanie"));
        exit.setText(bundle.getString("close"));
        plaf.setText(bundle.getString("zmien.widok"));
        plaf1.setText(bundle.getString("plaf.tekst1"));
        plaf2.setText(bundle.getString("plaf.tekst2"));
        plaf3.setText(bundle.getString("plaf.tekst3"));


    }
}
