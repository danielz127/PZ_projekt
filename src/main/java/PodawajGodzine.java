import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Daniel on 2017-11-13.
 */
public class PodawajGodzine implements Runnable {

    String godzina;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        godzina = podajGodzine();
    }

    public String podajGodzine() {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return (sdf.format(CzasSieciowy.getAtomicTime().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
