package Listenery;

import Exceptions.UszkodzoneSzafki;
import PaneleMenu.Szatnia.Szafka;
import PaneleMenu.Szatnia.Szatnia;

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
        int uszkodzone=0;
        for (Szafka szafka : szatnia.szafkiMeskie) {
            //zmiana zajetosci - usunac osobe
            if (szafka.uszkodzona != true) {
                szafka.zajeta = false;
                szafka.NrKlienta=0;
                szafka.dodajIkone();
            }
            else uszkodzone++;
        }
        for (Szafka szafka : szatnia.szafkiDamskie) {
            if (szafka.uszkodzona != true) {
                szafka.zajeta = false;
                szafka.NrKlienta=0;
                szafka.dodajIkone();
            }
            else uszkodzone++;
        }
        if(uszkodzone!=0){
            try {
                throw new UszkodzoneSzafki(uszkodzone);
            } catch (UszkodzoneSzafki uszkodzoneSzafki) {

            }
        }

    }


}
