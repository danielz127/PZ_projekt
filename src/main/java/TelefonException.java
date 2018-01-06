import javax.swing.*;

public class TelefonException extends Exception {
    NowyKlientEvent event;

    public TelefonException(NowyKlientEvent event) {
        this.event = event;
        event.telefon="";
        wywolajMenu();
    }

    private void wywolajMenu() {
        JOptionPane optionPane = new JOptionPane();

        optionPane.showMessageDialog(null,
                "Nieprawidlowy format i dlugosc telefonu",
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
