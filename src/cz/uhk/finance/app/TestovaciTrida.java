package cz.uhk.finance.app;

import cz.uhk.finance.data.Evidence;
import cz.uhk.finance.data.EvidenceImpl;
import cz.uhk.finance.data.Polozka;

import java.time.LocalDate;

public class TestovaciTrida {

    public static void main(String[] args) {
        Evidence evidence = new EvidenceImpl();

        evidence.pridatPolozku(new Polozka(LocalDate.of(2020, 1, 14),"Vyplata", 20000));
        evidence.pridatPolozku(new Polozka(LocalDate.now(), "Rohliky", -20));
        evidence.pridatPolozku(new Polozka(LocalDate.now(), "Elektrina", -1400));
        evidence.pridatPolozku(new Polozka(LocalDate.now(), "Vraceni dani", 2500));
        evidence.pridatPolozku(new Polozka(LocalDate.now(), "Rum", -99));
        evidence.pridatPolozku(new Polozka(LocalDate.now(), "Uroky", 593));
        evidence.pridatPolozku(new Polozka(LocalDate.now(), "Zelenina", -235));

        System.out.printf("Aktualni stav uctu: %.2f\n", evidence.getStavUctu());
        System.out.printf("Prijmy: %.2f\n", evidence.getPrijmy());
        System.out.printf("Vydaje: %.2f\n", evidence.getVydaje());
    }
}
