package LoggerPck;

import java.util.logging.*;

public class LoggerExmpl {
    private static Logger logr=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);



    public static void setupLogger() {

        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("src/main/resources/Logs/myLogs.log",1000, 10, true);
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        } catch (java.io.IOException e) {
            // don't stop my program but log out to console.
            logr.log(Level.SEVERE, "File logger error.");
        }

    }


}



