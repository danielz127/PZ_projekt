import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Szafka {
    boolean zajeta;
    int NrSzafki;
    boolean plec;
    JButton button;



    public Szafka(boolean zajeta, int nrSzafki, boolean plec) {
        this.zajeta = zajeta;
        NrSzafki = nrSzafki;
        this.plec = plec;
        dodajIkone();
    }

    public void dodajIkone() {

        if (zajeta == false) {

            button = new JButton(new ImageIcon("src/main/resources/ikony/szatnia/lockerG3.png"));
        } else {
            button = new JButton(new ImageIcon("src/main/resources/ikony/szatnia/lockerR4.png"));
        }
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(NrSzafki+" " +String.valueOf(plec));
            }
        });
    }

}

