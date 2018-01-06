import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PrzyciskZwolnijSzafki extends JButton   {
    Szatnia szatnia;

    public PrzyciskZwolnijSzafki(String tekst, Szatnia szatnia) {
        super(tekst);
        this.szatnia=szatnia;
        addActionListener(new ZwolnijSzafkiListener(szatnia));
    }

    @Override
    public void print(Graphics g) {
        super.print(g);
    }

}
