package cz.uhk.finance.data;

/**
 * Vyjimka pri ukladani a nacitani dat
 */
public class PersistenceException extends Exception {
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
