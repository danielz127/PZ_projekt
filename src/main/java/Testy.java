import javax.swing.*;

public class Testy extends JFrame {


    public Testy() {
        // Window and Picture Setup

        JPanel panelTlo = new PanelTlo();

        this.setTitle("Test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panelTlo);

        pack();  // **** added
        setVisible(true);
    }

    public static void main(String[] args) {
        Testy testy = new Testy();
    }

}
