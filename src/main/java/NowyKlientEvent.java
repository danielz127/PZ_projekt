import com.mysql.jdbc.PreparedStatement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NowyKlientEvent implements ActionListener {
    JDialog dialog;
    Baza baza;
    String imie, nazwisko, telefon;
    JOptionPane optionPane;
    OknoProgramu okno;
    int Idmiasta;


    public NowyKlientEvent(Baza baza, OknoProgramu oknoProgramu) {
        this.okno = oknoProgramu;
        this.baza = baza;
        optionPane = new JOptionPane();

    }

    public void dodajKlienta() {
        imie = JOptionPane.showInputDialog(null, "Imie", "Imie", 1);
        if ((imie != null) && (!imie.equals(""))) {
            nazwisko = JOptionPane.showInputDialog(null, "Nazwisko", "Naziwsko", 1);
            if (nazwisko != null && !nazwisko.equals("")) {
                telefon = JOptionPane.showInputDialog(null, "Telefon", "Telefon", 1);
               sprawdzTelefon();
                if (telefon != null && !telefon.equals("")) {
                    System.out.println(imie + nazwisko + telefon);

                    if (zapytaj() == 0)
                        dodajDoBazy(imie, nazwisko, telefon);
                }
            }
        }
    }

    private void sprawdzTelefon()   {
        try {
            Integer.parseInt(telefon);
            if(telefon.length()==9)
                System.out.print("okej");
        } catch (NumberFormatException e) {
            try {
                throw new TelefonException(this);
            } catch (TelefonException e1) {

            }

        }
    }



    private int zapytaj() {

        Object[] options = {"Tak",
                "Nie"};
        int a = JOptionPane.showOptionDialog(null, "Dodać te dane:\n" +
                        "Imie: " + imie + ", nazwisko: " + nazwisko + " , telefon: " + telefon + "?", "Potwierdz",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        return a;
    }

    private void dodajDoBazy(String imie, String nazwisko, String telefon) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                baza.utworzPolaczenie();

                try {
                    int liczbaKlientow=0;
                    baza.myStm = baza.myCon.createStatement();
                    baza.myRs = baza.myStm.executeQuery("SELECT COUNT(klient.NrKlienta)+1 from klient");

                    while(baza.myRs.next()) {
                        liczbaKlientow = baza.myRs.getInt(1);

                    }
                    baza.myRs = baza.myStm.executeQuery("select miasto.IdMiasta from miasto WHERE miasto.Nazwa = '"+okno.miasto+ "'");

                    while(baza.myRs.next()) {
                        Idmiasta = baza.myRs.getInt(1);

                    }

                    java.sql.PreparedStatement stmt = baza.myCon.prepareStatement("INSERT INTO klient(NrKlienta,IdMiasta, Imie,  Nazwisko, Telefon) VALUES (?, ?, ?, ?, ?)");

                    stmt.setString(1, String.valueOf(liczbaKlientow));
                    stmt.setString(2, String.valueOf(Idmiasta));
                    stmt.setString(3, imie);
                    stmt.setString(4, nazwisko);
                    stmt.setString(5, telefon);

                    stmt.executeUpdate();


                    baza.rozlaczBaze();


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
        worker.execute();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dodajKlienta();
    }

}



