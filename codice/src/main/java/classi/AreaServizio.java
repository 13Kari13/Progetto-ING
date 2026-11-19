
import java.util.*;

/**
 * # Classe AreaServizio
 * 
 * Rappresenta le attività operative della sede CRI di Bergamo raggruppate nelle rispettive 
 * macro-aree. 
 * Contiene anche una descrizione in modo da poter far capire all'utente che cosa farà in quel 
 * servizio. 
 * C'è un attributo molto importante che è isContinuativo che indica che se il servizio
 * richiede copertura continua senza scoperture tra i cambi turno. 
 * I suoi metodi sono: - **necessitaMezzo()** che restituisce true se richiede un veicolo operativo 
 * assegnato ai propri turni, - **pianificaTurno(...)** che crea una nuova istanza di Turno associata 
 * all'area di servizio.
 */
public class AreaServizio {

    /**
     * Default constructor
     */
    public AreaServizio() {
    }

    /**
     * 
     */
    private int idArea;

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private CategoriaServizio categoria;

    /**
     * 
     */
    private String descrizione;

    /**
     * 
     */
    private boolean richiedeMezzo;

    /**
     * 
     */
    private boolean isContinuativo;

    /**
     * @return
     */
    public boolean necessitaMezzo() {
        // TODO implement here
        return false;
    }

    /**
     * @param data 
     * @param inizio 
     * @param fine 
     * @return
     */
    public Turno pianificaTurno(LocalDate data, LocalTime inizio, LocalTime fine) {
        // TODO implement here
        return null;
    }

}