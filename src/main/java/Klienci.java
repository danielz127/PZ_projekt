import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.*;

public class Klienci extends JPanel {
    protected JButton button;
    static final long serialVersionUID = 1L;
    DefaultTableModel model = new DefaultTableModel();
    JTable table;

    double wartoscProd = 0;
    int iloscProd = 0;
    JLabel ilosc, wartosc;

    public Klienci() {
        //GridBagLayout od razu
        super(new GridLayout(4, 0));

        setVisible(true);
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));


        add(scrollPane);

        final JTextField textArea = new JTextField();
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));


        table = new JTable();
        wypelnijTabele();
        JScrollPane scrollPane2 = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        add(textArea);
        add(scrollPane2);
        button = new JButton("Add item");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              //  sc = new ShoppingCart(Integer.parseInt(textArea.getText()));
                dodajPustyWiersz();
                policzWartosci();

                //
            }
        });

        add(button);
        wartosc = new JLabel();
        wartosc.setText("Wartosc: " + wartoscProd);
        JButton eksport = new JButton("Eksport");
        eksport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Writer output = null;
                try {
                    File file = new File("wyniki.csv");
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    output = new BufferedWriter(new FileWriter(file, true));
                    output.append("Name;Price;Quantity");
                    for (int i = 0; i < model.getRowCount(); i++) {
                        output.append("\r\n" + model.getValueAt(i, 0) + ";" + model.getValueAt(i, 1) + ";" + model.getValueAt(i, 2));

                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    assert output != null;
                    output.close();
                } catch (IOException ec) {
                    ec.printStackTrace();
                }
            }


        });

        add(eksport);

        ilosc = new JLabel();
        ilosc.setText("Ilosc: " + iloscProd);

        add(wartosc);

        add(ilosc);


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

        ilosc.setText("Ilosc: " + iloscProd);
        wartosc.setText("Wartosc: " + wartoscProd);
//        try {
//            sc.setTotalValue(wartoscProd);
//        } catch (PropertyVetoException e) {
//            System.out.print("Blad");
//            table.setEnabled(false);
//            button.setEnabled(false);
//        }
        //


    }

    public void wypelnijTabele() {
        // table.add


        model.addColumn("Name");
        model.addColumn("Price (USD)");
        model.addColumn("Quantity");
        model.addRow(new Object[]{null});
        //model.addRow(new Object[]{null});
        table.setModel(model);


    }


}