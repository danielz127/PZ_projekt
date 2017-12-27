import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Klienci extends JPanel {
    static final long serialVersionUID = 1L;
    protected JButton button;
    DefaultTableModel model = new DefaultTableModel();
    JTable table;
    Baza baza;

    double wartoscProd = 0;
    int iloscProd = 0;

    JTextField textArea;

    public void utworzElementy(){
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        add(scrollPane);
        textArea = new JTextField();
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        button = new JButton("Add item");
        add(button);

    }
    public Klienci(Baza baza, OknoProgramu oknoProgramu) {
        //GridBagLayout od razu
        super(new GridLayout(4, 0));
        this.baza = baza;

        setVisible(true);
        utworzElementy();
        wypelnijTabele();
        listenery();

    }

    private void listenery() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //  sc = new ShoppingCart(Integer.parseInt(textArea.getText()));
                dodajPustyWiersz();
                policzWartosci();

                //
            }
        });
    }

    public void dodajPustyWiersz() {
        //model = new DefaultTableModel();
        model.addRow(new Object[]{null});

    }

    public void policzWartosci() {
        iloscProd = 0;
        wartoscProd = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 2) != null) iloscProd += Integer.parseInt(model.getValueAt(i, 2).toString());
            if (model.getValueAt(i, 1) != null)
                wartoscProd = wartoscProd + Integer.parseInt(model.getValueAt(i, 1).toString()) * Integer.parseInt(model.getValueAt(i, 2).toString());

        }

    }

    public void wypelnijTabele() {
        // table.add

        model.addColumn("Imie");
        model.addColumn("Nazwisko");
        model.addColumn("Telefon");
        model.addRow(new Object[]{null});
        //model.addRow(new Object[]{null});
        table.setModel(model);


    }


}