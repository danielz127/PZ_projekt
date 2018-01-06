import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WybierzOsobe extends JDialog implements WindowListener {
    Szatnia szatnia;
    SzafkaDialog szafkaDialog;
    JTable jt;
    Image image;
    DefaultTableModel model;
    JButton zapisz;

    public WybierzOsobe(Szatnia szatnia, SzafkaDialog szafkaDialog) {
        this.szatnia = szatnia;
        this.szafkaDialog = szafkaDialog;
        setResizable(false);
        addWindowListener(this);
        setAlwaysOnTop(true);
        jt = new JTable();

        model = new DefaultTableModel();
        zapisz = new JButton("Wybierz");

        setBounds(szafkaDialog.getBounds());
        image = Toolkit.getDefaultToolkit().getImage("src/main/resources/ikony/select.png");

        setIconImage(image);
        utworzTabele();
        wypelnijTabele();

        listener();
        //wyborOsoby.setSize(300, 300);
        setTitle("Wybierz osobe");
        setVisible(true);
    }

    private int zamykanie() {
        dispose();
        return 0;
    }

    private void listener() {
        zapisz.addActionListener(zapisz -> {
            if (jt.getSelectedRow()!=-1) {
                int i = jt.getSelectedRow();
                szafkaDialog.textArea.setText(jt.getValueAt(i, 1) + " " + jt.getValueAt(i, 2));
                szafkaDialog.szafka.NrKlienta = (int) jt.getValueAt(i, 0);
                this.dispose();
                szafkaDialog.setVisible(true);
            }
        });

    }

    private void wypelnijTabele() {
        model.setColumnIdentifiers(new Object[]{"Nr Klienta", "Imie", "Nazwisko", "Telefon",});
        for (Klient klient : szatnia.klients)
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


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        szafkaDialog.setVisible(true);
        this.dispose();



    }

    @Override
    public void windowClosed(WindowEvent e) {




    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
