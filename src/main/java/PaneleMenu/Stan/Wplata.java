package PaneleMenu.Stan;

public class Wplata {

    String tytulWplaty;
    int kwotaWplaty;
    String dataWplaty;

    public Wplata(String tytulWplaty, int kwotaWplaty, String dataWplaty) {

        this.tytulWplaty = tytulWplaty;
        this.kwotaWplaty = kwotaWplaty;
        this.dataWplaty = dataWplaty;
    }

    public String getTytulWplaty() {
        return tytulWplaty;
    }

    public void setTytulWplaty(String tytulWplaty) {
        this.tytulWplaty = tytulWplaty;
    }

    public int getKwotaWplaty() {
        return kwotaWplaty;
    }

    public void setKwotaWplaty(int kwotaWplaty) {
        this.kwotaWplaty = kwotaWplaty;
    }

    public String getDataWplaty() {
        return dataWplaty;
    }

    public void setDataWplaty(String dataWplaty) {
        this.dataWplaty = dataWplaty;
    }

    @Override
    public String toString() {
        return tytulWplaty.toString();
    }
}
