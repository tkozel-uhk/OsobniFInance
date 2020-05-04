package cz.uhk.finance.data;

/**
 * Rozhrani pro persistenci dat
 */
public interface PersistenceManager {
    /**
     * Ulozi vsechny polozky v evidenci
     * @param evidence
     * @throws PersistenceException
     */
    void ulozitVse(Evidence evidence) throws PersistenceException;

    /**
     * Nacte vsechny polozky ze zdroje do evidence
     * @return
     * @throws PersistenceException
     */
    Evidence nacistVse() throws PersistenceException;
}
