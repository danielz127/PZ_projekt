package PaneleMenu.Stan;

public class Magazyn {

    String urzadzenie;
    int ilosc;

    public Magazyn(String urzadzenie, int ilosc) {
        this.urzadzenie = urzadzenie;
        this.ilosc = ilosc;
    }

    public String getUrzadzenie() {
        return urzadzenie;
    }

    public void setUrzadzenie(String urzadzenie) {
        this.urzadzenie = urzadzenie;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
    @Override
    public String toString() {
        return urzadzenie.toString();
    }
}
