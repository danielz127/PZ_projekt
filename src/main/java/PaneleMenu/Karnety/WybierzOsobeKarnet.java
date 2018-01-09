package PaneleMenu.Karnety;

import Interfejsy.WyborOsoby;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WybierzOsobeKarnet extends WyborOsoby implements WindowListener {

    public WybierzOsobeKarnet(Karnety karnety, NowyKarnetDialog dialog) {
        super(karnety, dialog);
        setBounds(dialog.getX(), dialog.getY(), 350, 400);
        addWindowListener(this);
        listener();
        setVisible(true);
    }

    public void listener() {
        zapisz.setSize(new Dimension(30, 40));
        add(zapisz, BorderLayout.PAGE_END);
        zapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jt.getSelectedRow() != -1) {
                    int i = jt.getSelectedRow();
                    dialog.textAreaOsoba.setText(jt.getValueAt(i, 1) + " " + jt.getValueAt(i, 2));
                    dialog.nrKlienta = (int) jt.getValueAt(i, 0);
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
        dispose();
        dialog.setEnabled(true);

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
