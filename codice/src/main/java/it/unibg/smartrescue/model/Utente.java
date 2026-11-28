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
	private List<Turno> turni = new ArrayList<>();
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
     * i metodi rispettaRiposoMinimo verifica che i turni non siano sovrapposti. ci sono due metodi. uno è generato e prende come default riposo minimo di 8 ore. 
     * l'altro è un metodo che viene invocato solo se cambia il riposoMinimo configurato
     */
    
    public boolean rispettaRiposoMinimo(LocalDateTime inizioTurno) {
        return rispettaRiposoMinimo(inizioTurno, 8); // default 8 ore
    }
    public boolean rispettaRiposoMinimo(LocalDateTime inizioTurno, int oreRiposoMinime) {
    	if (inizioTurno == null || this.turni == null || this.turni.isEmpty()) {
            return true;
        }

        for (Turno t : this.turni) {
            if (t.getStato() == StatoTurno.ANNULLATO || t.getData() == null 
                    || t.getOraInizio() == null || t.getOraFine() == null) {
                continue;
            }

            LocalDateTime inizioEsistente = LocalDateTime.of(t.getData(), t.getOraInizio());
            LocalDateTime fineEsistente = LocalDateTime.of(t.getData(), t.getOraFine());
            // Gestione eventuale turno notturno che finisce il giorno dopo
            if (!t.getOraFine().isAfter(t.getOraInizio())) {
                fineEsistente = fineEsistente.plusDays(1);
            }

            // 1. Controllo sovrapposizione diretta con un turno già in corso
            if (!inizioEsistente.isAfter(inizioTurno) && fineEsistente.isAfter(inizioTurno)) {
                return false;
            }

            // 2. Controllo ore di riposo dopo un turno precedente
            if (!fineEsistente.isAfter(inizioTurno)) {
                long oreRiposo = Duration.between(fineEsistente, inizioTurno).toHours();
                if (oreRiposo < oreRiposoMinime) {
                    return false;
                }
            }
        }
        return true;
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

	public List<Turno> getTurni() {
		return turni;
	}

	public void setTurni(List<Turno> turni) {
		this.turni = turni;
	}

}