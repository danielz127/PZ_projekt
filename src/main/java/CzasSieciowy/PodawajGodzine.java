package CzasSieciowy;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Daniel on 2017-11-13.
 */
public class PodawajGodzine {

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
