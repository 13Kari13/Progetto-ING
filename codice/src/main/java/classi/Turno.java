
import java.util.*;

/**
 * # Classe Turno
 * 
 * Rappresenta i turni pianificati per una specifica AreaServizio, in una determina data e fascia oraria, composta
 * da un insieme di posizioni da coprire e da un eventuale Mezzo associato. 
 * 
 * I  suoi metodi sono: 
 * 
 * - **isEquipaggioCOnforme()** che verifica che tutte le posizioni siano coperte da
 * volontari idonei
 * 
 * - **conferma ()** che porta il turno da INCOMPLETO a CONFERMATO solo se equipaggio conforme e mezzo
 * disponibile (se serve)
 * 
 * - **avvia()** che porta il turno nello stato OPERATIVO all'inizio dell'orario di servizio, **chiudi()** 
 * porta il turno a CONCLUSO al termine del servizio
 * 
 * - **annulla()** porta il turno in ANNULLATO (se non è ancora iniziato) e 
 * libera il mezzo associato (se associato)
 * 
 * - **calcolaCopertura()** restituisce il livello visivo di riempimento per il calendario
 * a semafori.
 */
public class Turno {

    /**
     * Default constructor
     */
    public Turno() {
    }

    /**
     * 
     */
    private int idTurno;

    /**
     * 
     */
    private LocalDate data;

    /**
     * 
     */
    private LocalTime oraInizio;

    /**
     * 
     */
    private LocalTime oraFine;

    /**
     * 
     */
    private String fasciaOraria;

    /**
     * 
     */
    private StatoTurno stato;

    /**
     * 
     */
    private String note;

    /**
     * @return
     */
    public boolean isEquipaggioConforme() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void conferma() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void avvia() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void chiudi() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void annulla() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public LivelloCopertura calcolaCopertura() {
        // TODO implement here
        return null;
    }

}