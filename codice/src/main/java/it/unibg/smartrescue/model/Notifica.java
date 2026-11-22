package it.unibg.smartrescue.model;
import java.time.*;
import java.util.*;

/**
 * # Classe Notifica
 * 
 * Modella le comunicazioni generate automaticamente verso l'utente per avvisarlo in caso 
 * di scadenze di qualifiche, conferme o annullamenti turni, ricerca sostituti o indisponibilità del mezzo.
 * Contiene dunque un messaggio e lo stato letta boolean che indica se è stata letta o no tramite il metodo
 * **segnaComeLetta()**.
 */
public class Notifica {

    /**
     * Default constructor
     */
    public Notifica() {
    }

    /**
     * 
     */
    private int idNotifica;

    /**
     * 
     */
    private TipoNotifica tipo;

    /**
     * 
     */
    private String messaggio;

    /**
     * 
     */
    private LocalDateTime dataOraCreazione;

    /**
     * 
     */
    private boolean letta;

    /**
     * @return
     */
    public void segnaComeLetta() {
        // TODO implement here
     
    }

}