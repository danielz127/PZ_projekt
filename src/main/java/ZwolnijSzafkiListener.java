import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZwolnijSzafkiListener implements ActionListener {
    Szatnia szatnia;

    public ZwolnijSzafkiListener(Szatnia szatnia) {
        this.szatnia = szatnia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        zwolnijSzafki();

    }

    private void zwolnijSzafki() {
        for(Szafka szafka : szatnia.szafkiMeskie){
            //zmiana zajetosci - usunac osobe
            szafka.zajeta = false;
            szafka.dodajIkone();
        }
        for(Szafka szafka : szatnia.szafkiDamskie){
            szafka.zajeta = false;
            szafka.dodajIkone();
        }
    }


}
