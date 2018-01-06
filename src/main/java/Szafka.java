import javax.swing.*;

public class Szafka {
    Boolean zajeta;
    int NrSzafki;
    boolean plec;
    JButton button;
    boolean uszkodzona;
    Szatnia szatnia;
    int NrKlienta;

    //brzydko ale szybko
    ImageIcon iconZajeta = new ImageIcon("src/main/resources/ikony/szatnia/lockerR4.png");
    ImageIcon iconWolna = new ImageIcon("src/main/resources/ikony/szatnia/lockerG3.png");


    public Szafka(boolean zajeta, int nrSzafki, boolean plec, Szatnia szatnia) {
        this.szatnia = szatnia;
        this.zajeta = zajeta;
        NrSzafki = nrSzafki;
        this.plec = plec;
        uszkodzona = false;
        button = new JButton();
        dodajIkone();
        wlasciwosciIkony();

    }


    private void wlasciwosciIkony() {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(new SzafkaListener(this));
    }

    public void dodajIkone() {
        if (zajeta == false) {
            button.setIcon(iconWolna);
        } else {
            button.setIcon(iconZajeta);
        }

    }

}

