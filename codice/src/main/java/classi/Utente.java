
import java.util.*;

/**
 * # Classe Utente
 * 
 * La classe utente raggruppa all'interno di se tutti gli utenti: volontari, amministratori, capoturni. 
 * I ruoli vengono distinti tramite l'enum RuoloUtente (che distingue quindi Volontario, Amministratore, CapoTurno).
 * 
 * I suoi metodi sono: 
 * 
 * - **rispettaRiposoMinimo(..)** : verifica che venga effettuato il riposo minimo il cui valore dettato nella classe configurazione
 * 
 * - **haQualificaValida(..)**: verifica che la qualifica sia valida
 */
public class Utente {

    /**
     * Default constructor
     */
    public Utente() {
    }

    /**
     * 
     */
    private int idUtente;

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private String cognome;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String passwordHash;

    /**
     * 
     */
    private String telefono;

    /**
     * 
     */
    private RuoloUtente ruolo;

    /**
     * 
     */
    private boolean attivo;

    /**
     * @param codice 
     * @param data 
     * @return
     */
    public boolean haQualificaValida(String codice, LocalDate data) {
        // TODO implement here
        return false;
    }

    /**
     * @param inizioTurno 
     * @return
     */
    public boolean rispettaRiposoMinimo(LocalDateTime inizioTurno) {
        // TODO implement here
        return false;
    }

}