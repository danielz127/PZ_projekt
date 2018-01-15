package CzasSieciowy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created by Daniel on 2017-11-13.
 */
public class PodawajGodzine {

    private static PodawajGodzine firstInstance = null;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private PodawajGodzine() {
    }

    public static PodawajGodzine getInstance() {
        if (firstInstance == null) {
            firstInstance = new PodawajGodzine();
        }
        return firstInstance;
    }

    public String podajGodzine() {
        try {
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return (sdf.format(CzasSieciowy.getAtomicTime().getTime()));
        } catch (IOException e) {
            logr.info("Blad pobierania czasu");

        }
        return "";
    }


}
