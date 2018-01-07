package Main;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class WczytajProperties {

    InputStream inputStream;
    Properties p;
    int szerokosc, wysokosc;

    public WczytajProperties() {
        p = new Properties();

        try {
            inputStream = new FileInputStream("src/main/resources/okno.properties");
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSzerokosc();
        setWysokosc();


    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc() {
        szerokosc = Integer.parseInt(p.getProperty("width"));
    }

    public int getWysokosc() {

        return wysokosc;
    }

    public void setWysokosc() {
        wysokosc = Integer.parseInt(p.getProperty("height"));
    }
}
