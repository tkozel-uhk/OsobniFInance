package cz.uhk.finance.data;

import java.util.ArrayList;
import java.util.List;

public class EvidenceImpl implements Evidence {
    private List<Polozka> polozky = new ArrayList<>();

    @Override
    public void pridatPolozku(Polozka p) {
        polozky.add(p);
    }

    @Override
    public List<Polozka> getPolozky() {
        return polozky;
    }

    @Override
    public double getStavUctu() {
        double suma = 0;

        for (Polozka p : polozky) {
            suma += p.getCastka();
        }

        return suma;
    }

    @Override
    public double getVydaje() {
        double suma = 0;

        for (Polozka p : polozky) {
            if (p.getCastka() < 0)
                suma += p.getCastka();
        }

        return suma;
    }

    @Override
    public double getPrijmy() {
        double suma = 0;

        for (Polozka p : polozky) {
            if (p.getCastka() >= 0) {
                suma += p.getCastka();
            }
        }

        return suma;
    }
}
