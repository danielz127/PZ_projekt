import java.awt.*;

/**
 * Created by Daniel on 2017-11-10.
 */
public class Main {
    public static void main(String[] args) {
        System.out.print("Dziala");
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OknoProgramu();
            }
        });


    }
}
