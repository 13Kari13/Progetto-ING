
import java.util.*;

/**
 * # Classe UtenteQualifica
 * 
 * Si tratta di un'association class, memorizza la data di conseguimento della qualifica 
 * (dataRilascio) e eventuale scadenza per verificare la validità nei turni.
 * I suoi metodi sono: 
 * 
 * - **isValidaAllaData(dataTurno: LocalDate)** che restituisce true se l'attributo
 * è NULL o se non è scaduta prima del turno
 * 
 * - **isInScadenza(giorniSoglia:int )** verifica che la scadenza
 * rientra nella finestra di preaviso configurabile consentendo di mostrare l'avviso in home e inviare
 * notifiche in caso
 * 
 * - **isScaduta()** controlla che la qualifica non sia scaduta al momento.
 */
public class UtenteQualifica {

    /**
     * Default constructor
     */
    public UtenteQualifica() {
    }

    /**
     * 
     */
    private LocalDate dataRilascio;

    /**
     * 
     */
    private LocalDate dataScadenza;

    /**
     * @param dataTurno 
     * @return
     */
    public boolean isValidaAllaData(LocalDate dataTurno) {
        // TODO implement here
        return false;
    }

    /**
     * @param giorniSoglia 
     * @return
     */
    public boolean isInScadenza(int giorniSoglia) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean isScaduta() {
        // TODO implement here
        return false;
    }

}