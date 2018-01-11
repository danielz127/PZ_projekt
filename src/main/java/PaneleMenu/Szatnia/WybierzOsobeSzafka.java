package PaneleMenu.Szatnia;

import Interfejsy.WyborOsoby;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WybierzOsobeSzafka extends WyborOsoby implements WindowListener {


    public WybierzOsobeSzafka(Szatnia szatnia, SzafkaDialog szafkaDialog) {
        super(szatnia, szafkaDialog);

        addWindowListener(this);
        listener();
        setVisible(true);
        setBounds(szafkaDialog.getX(), szafkaDialog.getY(), 350, 400);
        repaint();
    }


    private void listener() {
        zapisz.setSize(new Dimension(30, 40));
        add(zapisz, BorderLayout.PAGE_END);
        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jt.getSelectedRow() != -1) {
                    int i = jt.getSelectedRow();
                    szafkaDialog.textArea.setText(jt.getValueAt(i, 1) + " " + jt.getValueAt(i, 2));
                    szafkaDialog.szafka.NrKlienta = (int) jt.getValueAt(i, 0);

                    szafkaDialog.setVisible(true);
                    dispose();
                }

            }
        });

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
