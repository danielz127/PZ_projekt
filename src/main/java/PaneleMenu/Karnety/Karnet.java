package PaneleMenu.Karnety;

public class Karnet {
    int nrKarnetu;
    int nrKlienta;
    String nazwaKarnetu;
    String dataOd;
    String dataDo;
    String osoba;

    public Karnet(int nrKarnetu, int nrKlienta, String nazwaKarnetu, String dataOd, String dataDo) {
        this.nrKarnetu = nrKarnetu;
        this.nrKlienta = nrKlienta;
        this.nazwaKarnetu = nazwaKarnetu;
        this.dataOd = dataOd;
        this.dataDo = dataDo;
    }

    public Karnet(int nrKarnetu, int nrKlienta, String osoba, String nazwaKarnetu, String dataOd, String dataDo) {
        this.nrKarnetu = nrKarnetu;
        this.nrKlienta = nrKlienta;
        this.nazwaKarnetu = nazwaKarnetu;
        this.osoba = osoba;
        this.dataOd = dataOd;
        this.dataDo = dataDo;

    }

    public int getNrKarnetu() {
        return nrKarnetu;
    }

    public void setNrKarnetu(int nrKarnetu) {
        this.nrKarnetu = nrKarnetu;
    }

    public int getNrKlienta() {
        return nrKlienta;
    }

    public void setNrKlienta(int nrKlienta) {
        this.nrKlienta = nrKlienta;
    }

    public String getNazwaKarnetu() {
        return nazwaKarnetu;
    }

    public void setNazwaKarnetu(String nazwaKarnetu) {
        this.nazwaKarnetu = nazwaKarnetu;
    }

    public String getDataOd() {
        return dataOd;
    }

    public void setDataOd(String dataOd) {
        this.dataOd = dataOd;
    }

    public String getDataDo() {
        return dataDo;
    }

    public void setDataDo(String dataDo) {
        this.dataDo = dataDo;
    }

    public String getOsoba() {
        return osoba;
    }

    public void setOsoba(String osoba) {
        this.osoba = osoba;
    }

    @Override
    public String toString() {
        return "Nr karnetu: "+ nrKarnetu + ", nr klienta: "+nrKlienta + ", nazwa karnetu: "+nazwaKarnetu + ", wlasciciel:  " +osoba + ", od: " + dataOd + ", do: "  + dataDo;
    }
}
