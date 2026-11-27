package it.unibg.smartrescue.model;
import java.time.*;
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
    
    private List<UtenteQualifica> qualifiche=new ArrayList<>(); //non era tra gli attributi generati da UML questo

    /**
     * il metodo haQualificaValida controlla (tra le diverse qualifiche) se è valida una determinata qualifica dato il codice e la data per cui si vuole sapere
     */
    public boolean haQualificaValida(String codice, LocalDate data) {
        for(UtenteQualifica q : qualifiche) {
        	if( q.getQualifica().getCodice().equals(codice)){
        		if(q.isValidaAllaData(data)) {
        			return true;
        		}
        	}
        }
        return false;
    }

    /**
     * @param inizioTurno 
     * @return
     */
    public boolean rispettaRiposoMinimo(LocalDateTime inizioTurno) {
        // TODO 
        return false;
    }

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public RuoloUtente getRuolo() {
		return ruolo;
	}

	public void setRuolo(RuoloUtente ruolo) {
		this.ruolo = ruolo;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

}