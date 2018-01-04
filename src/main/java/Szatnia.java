import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Szatnia extends JPanel {
    Baza baza;
    OknoProgramu oknoProgramu;
    ArrayList<Szafka> szafkiMeskie;
    ArrayList<Szafka> szafkiDamskie;
    ArrayList<JButton> przyciskiSzafek;

    public Szatnia(Baza baza, OknoProgramu oknoProgramu) {
        super();
        this.oknoProgramu = oknoProgramu;
        this.baza = baza;

        pobierzSzafki();
        utworzPanel();
    }

    private void utworzLIsty() {
        szafkiMeskie = new ArrayList<>();
        szafkiDamskie = new ArrayList<>();
        przyciskiSzafek = new ArrayList<>();
    }

    private void pobierzSzafki() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected void done() {
                super.done();
//                for (Szafka szafka : szafkiDamskie) {
//                    System.out.println(szafka.NrSzafki + String.valueOf(szafka.zajeta) + String.valueOf(szafka.plec));
//                }
//
//                for (Szafka szafka : szafkiMeskie) {
//                    System.out.println(szafka.NrSzafki + String.valueOf(szafka.zajeta) + String.valueOf(szafka.plec));
//                }
                dodajPrzyciskiSzafek();

            }

            @Override
            protected Void doInBackground() throws Exception {
                utworzLIsty();
                baza.utworzPolaczenie();

                try {
                    baza.myStm = baza.myCon.createStatement();
                    //TODO Zmienic Warszawa na miasto.nazwa = okno.miasto

                    baza.myRs = baza.myStm.executeQuery("select szafki.NrSzafki, szafki.Zajetosc, szatnia.Plec\n" +
                            "FROM szafki, szatnia, silownia, miasto\n" +
                            "WHERE szatnia.IdSzatni=szafki.IdSzatni AND\n" +
                            "  szatnia.IdSilowni = silownia.IdSilowni and silownia.IdMiasta = miasto.IdMiasta\n" +
                            "AND miasto.Nazwa = \"Warszawa\"");
                    while (baza.myRs.next()) {
                        String nrSzafki = baza.myRs.getString("NrSzafki");
                        String zajetosc = baza.myRs.getString("Zajetosc");
                        String plec = baza.myRs.getString("Plec");

                        //true - meskie
                        //false - damskie

                        if (plec.equals("1")) {
                            szafkiMeskie.add(new Szafka(Boolean.parseBoolean(zajetosc), Integer.parseInt(nrSzafki), true));
                        } else {
                            szafkiDamskie.add(new Szafka(Boolean.parseBoolean(zajetosc), Integer.parseInt(nrSzafki), false));
                        }

                        //System.out.println(country + " " + sum);
                        //
                    }

                    baza.rozlaczBaze();


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                baza.rozlaczBaze();
                return null;
            }
        };
        worker.execute();
    }

    private void dodajPrzyciskiSzafek() {
        for (Szafka szafka : szafkiDamskie) {
            add(szafka.button);
        }

        for (Szafka szafka : szafkiMeskie) {
            add(szafka.button);

        }

    }


    public void utworzPanel() {
        setVisible(true);
        setBackground(Color.RED);
        setPreferredSize(new Dimension(400, 500));

    }
}
