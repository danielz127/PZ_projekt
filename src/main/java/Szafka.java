import javax.swing.*;

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

            button = new JButton(new ImageIcon("src/main/resources/ikony/szatnia/lockerG1.png"));
        } else {
            button = new JButton(new ImageIcon("src/main/resources/ikony/szatnia/lockerR1.png"));
        }
    }
}

