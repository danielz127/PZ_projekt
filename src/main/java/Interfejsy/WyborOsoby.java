package Interfejsy;

import PaneleMenu.Karnety.Karnety;
import PaneleMenu.Karnety.NowyKarnetDialog;
import PaneleMenu.Klient.Klient;
import PaneleMenu.Szatnia.SzafkaDialog;
import PaneleMenu.Szatnia.Szatnia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public abstract class  WyborOsoby extends JDialog {
    Szatnia szatnia;
    public SzafkaDialog szafkaDialog;
    public JTable jt;
    Image image;
    DefaultTableModel model;
    public JButton zapisz;
    ArrayList<Klient> klients;
    Karnety karnety;
    public NowyKarnetDialog dialog;

    public WyborOsoby(Szatnia szatnia, SzafkaDialog szafkaDialog) {
        this.klients = szatnia.klients;
        this.szatnia = szatnia;
        this.szafkaDialog = szafkaDialog;
        setBounds(szafkaDialog.getBounds());
        wspolneCzesc();

    }
    public WyborOsoby(Karnety karnety, NowyKarnetDialog dialog){
        this.klients = karnety.klients;
        this.karnety = karnety;
        this.dialog = dialog;
        setBounds(dialog.getBounds());
        wspolneCzesc();
    }


    public void wspolneCzesc(){
        setResizable(false);
        setAlwaysOnTop(true);
        jt = new JTable();
        model = new DefaultTableModel();
        zapisz = new JButton("Wybierz");

        image = Toolkit.getDefaultToolkit().getImage("src/main/resources/ikony/select.png");
        setIconImage(image);
        utworzTabele();
        wypelnijTabele();
        //wyborOsoby.setSize(300, 300);
        setTitle("Wybierz osobe");
    }

    private void wypelnijTabele() {
        model.setColumnIdentifiers(new Object[]{"Nr Klienta", "Imie", "Nazwisko", "Telefon",});
        for (Klient klient : klients)
            model.addRow(new Object[]{klient.NrKlienta, klient.imie, klient.nazwisko, klient.telefon});
        jt.setModel(model);
        jt.getTableHeader().setReorderingAllowed(false);
    }

    private void utworzTabele() {
        // Columns for table

        // Creates Table
        jt = new JTable() {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            public Component prepareRenderer(
                    TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);

                // Every even numbers
                if (data % 2 == 0)
                    c.setBackground(Color.WHITE);

                else
                    c.setBackground(Color.LIGHT_GRAY);
                if (isCellSelected(data, columns)) {
                    c.setBackground(new Color(170, 243, 2));

                }
                return c;
            }

        };

        // Set size of table
        jt.setPreferredScrollableViewportSize(new Dimension(300, 60));

        // This will resize the height of the table automatically
        // to all data without scrolling.
        jt.setFillsViewportHeight(true);

        JScrollPane jps = new JScrollPane(jt);
        add(jps);
        zapisz.setSize(new Dimension(30, 40));
        add(zapisz, BorderLayout.PAGE_END);
    }



}
