package it.unibg.smartrescue.model;
import java.time.*;
import java.util.*;

/**
 * # Classe Qualifica
 * 
 * La classe qualifica serve a rappresentare qualsiasi brevetto, corso formativo
 * CRI, abilitazione , idoneità sanitaria o patente di guida (civile o di servizio CRI) 
 * che un volontario può avere. 
 * Tipologia qualifica serve proprio a distinguere se si tratta di una qualifica o di 
 * una patente di guida. 
 * I suoi metodi sono: - **isPermanente () ** se la qualifica non ha scadenza restituisce true
 * - **calcolaDataScadenza(dataRilascio: LocalDate)** calcola la data di scadenza sommando 
 * durataMesi alla data di rilascio - **isPatente()** verifica se la qualifica appartiene alla categoria delle abilitazioni
 * di guida.
 */
public class Qualifica {

    /**
     * Default constructor
     */
    public Qualifica() {
    }

    /**
     * 
     */
    private String codice;

    /**
     * 
     */
    private String denominazione;

    /**
     * 
     */
    private String tipologia;

    /**
     * 
     */
    private Integer mesiValidita;

    /**
     * @return
     */
    public boolean isPermanente() {
        // TODO implement here
        return false;
    }

    /**
     * @param dataRilascio 
     * @return
     */
    public LocalDate calcolaDataScadenza(LocalDate dataRilascio) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean isPatente() {
        // TODO implement here
        return false;
    }

}