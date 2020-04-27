package cz.uhk.finance.data;

import java.time.LocalDate;

/**
 * Polozka osobnich financi
 */
public class Polozka {
    private LocalDate datum;
    private String nazev;
    private double castka;

    public Polozka() {}

    public Polozka(LocalDate datum, String nazev, double castka) {
        this.datum = datum;
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
