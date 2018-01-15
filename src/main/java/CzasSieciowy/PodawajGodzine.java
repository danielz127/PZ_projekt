package CzasSieciowy;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created by Daniel on 2017-11-13.
 */
public class PodawajGodzine {

    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static PodawajGodzine firstInstance = null;

    private PodawajGodzine() {
    }

    public static PodawajGodzine getInstance() {
        if (firstInstance == null) {
            firstInstance = new PodawajGodzine();
        }
        return firstInstance;
    }

    public String podajGodzine() {

        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return (sdf.format(CzasSieciowy.getAtomicTime().getTime()));


    }


}
