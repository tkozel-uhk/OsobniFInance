package cz.uhk.finance.data;

import java.util.List;

/**
 * Rozhrani pro evidenci osobnich financi
 */
public interface Evidence {
    /**
     * Metoda pro pridani polozky do evidence
     * @param p nova polozka
     */
    void pridatPolozku(Polozka p);

    /**
     * Vraci seznam vsech polozek v evidenci
     * @return seznam polozek
     */
    List<Polozka> getPolozky();

    /**
     * Vraci aktualni stav osobnich financi
     * @return aktualni stav k Kc
     */
    double getStavUctu();

    /**
     * Vraci sumu vydaju
     * @return soucet vydaju
     */
    double getVydaje();

    /**
     * Vraci sumu prijmu
     * @return soucet prijmu
     */
    double getPrijmy();
}
