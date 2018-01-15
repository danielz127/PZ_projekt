package CzasSieciowy;

import Exceptions.BrakInternetu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 * Created by Daniel on 2017-11-13.
 */
public class CzasSieciowy {

    public static final String ATOMICTIME_SERVER = "129.6.15.30";
    public static final int ATOMICTIME_PORT = 13;
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public final static GregorianCalendar getAtomicTime() {
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
            //e.printStackTrace();
            System.out.println("Blad");
            try {
                throw new BrakInternetu();
            } catch (BrakInternetu brakInternetu) {
                logr.info("Brak polaczenia z internetem, pobrano czas systemowy");
                return BrakInternetu.zwrocKalendarz();
            }


        }

    }
}
