package cz.uhk.finance.data;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CsvPersistenceManager implements PersistenceManager {
    private String fileName;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public CsvPersistenceManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ulozitVse(Evidence evidence) throws PersistenceException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(fileName))) {

            for(Polozka p : evidence.getPolozky()) {
                out.printf("%s;%s;%f\n", p.getDatum().format(formatter), p.getNazev(), p.getCastka());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Chyba pri zapisu souboru "+fileName, e);
        }
    }

    @Override
    public Evidence nacistVse() throws PersistenceException {
        Evidence evidence = new EvidenceImpl();

        try (BufferedReader inp = new BufferedReader(new FileReader(fileName))) {
            String radek;

            while ((radek = inp.readLine()) != null) {
                String[] pole = radek.split(";");
                LocalDate dat = LocalDate.parse(pole[0], formatter);
                double castka = NumberFormat.getInstance().parse(pole[2]).doubleValue();
                evidence.pridatPolozku(new Polozka(dat, pole[1], castka));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Soubor se nepodarilo oterit", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Chyba pri cteni souboru", e);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new PersistenceException("Chybny format data", e);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new PersistenceException("Chybny format cisla", e);
        }

        return evidence;
    }
}
