package cz.uhk.finance.data;

/**
 * Polozka osobnich financi
 */
public class Polozka {
    private String nazev;
    private double castka;

    public Polozka() {}

    public Polozka(String nazev, double castka) {
        this.nazev = nazev;
        this.castka = castka;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public void setCastka(double castka) {
        this.castka = castka;
    }

    public double getCastka() {
        return castka;
    }
}
