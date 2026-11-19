
import java.util.*;

/**
 * # Classe Mezzo
 * 
 * Identifica ogni mezzo utilizzato (doblò, H24, H12 ecc..) con le targhe relative. 
 * 
 * I suoi metodi sono: 
 * 
 * - **isDisponibile(...)**: verifica che lo stato sia DISPONIBILE e che il mezzo non sia 
 * già stato assegnato a un altro turno nella stessa fascia oraria
 * 
 * - **cambiaStato(..)**: aggiorna lo stato del mezzo segnalando anche se è possibile che vada in 
 * manutenzione o fuoriservizio
 * 
 * - **verificaDotazioni()**: verifica che tutte le dotazioni di bordo siano conformi
 */
public class Mezzo {

    /**
     * Default constructor
     */
    public Mezzo() {
    }

    /**
     * 
     */
    private int idMezzo;

    /**
     * 
     */
    private String sigla;

    /**
     * 
     */
    private TipoMezzo tipo;

    /**
     * 
     */
    private StatoMezzo stato;

    /**
     * 
     */
    private LocalDate prossimaManutenzione;

    /**
     * 
     */
    private String targa;

    /**
     * @param data 
     * @param inizio 
     * @param fine 
     * @return
     */
    public boolean isDisponibile(LocalDate data, LocalTime inizio, LocalTime fine) {
        // TODO implement here
        return false;
    }

    /**
     * @param nuovoStato 
     * @return
     */
    public void cambiaStato(StatoMezzo nuovoStato) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean verificaDotazioni() {
        // TODO implement here
        return false;
    }

}