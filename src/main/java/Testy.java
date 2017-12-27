import javax.swing.*;
import java.awt.*;

public class Testy extends JFrame {


    public static void main(String[] args) {
        Testy testy = new Testy();
    }
    public Testy(){
        // Window and Picture Setup

        JPanel panelTlo = new PanelTlo();

        this.setTitle("Test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panelTlo);

        pack();  // **** added
        setVisible(true);
    }

}
