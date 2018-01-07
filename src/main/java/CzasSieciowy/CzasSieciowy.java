package CzasSieciowy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Daniel on 2017-11-13.
 */
public class CzasSieciowy {

    public static final String ATOMICTIME_SERVER = "129.6.15.30";
    public static final int ATOMICTIME_PORT = 13;


    public final static GregorianCalendar getAtomicTime() throws IOException {
        BufferedReader in = null;
        Socket conn = null;

        try {
            conn = new Socket(ATOMICTIME_SERVER, ATOMICTIME_PORT);

            in = new BufferedReader
                    (new InputStreamReader(conn.getInputStream()));

            String atomicTime;
            while (true) {
                if ((atomicTime = in.readLine()).indexOf("*") > -1) {
                    break;
                }
            }

            String[] fields = atomicTime.split(" ");
            GregorianCalendar calendar = new GregorianCalendar();

            String[] date = fields[1].split("-");
            calendar.set(Calendar.YEAR, 2000 + Integer.parseInt(date[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
            calendar.set(Calendar.DATE, Integer.parseInt(date[2]));

            int gmt = 1;

            String[] time = fields[2].split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]) + gmt);
            calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));

            return calendar;
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
