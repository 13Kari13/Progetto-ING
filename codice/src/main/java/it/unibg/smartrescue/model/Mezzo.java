package it.unibg.smartrescue.model;
import java.time.*;
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
	 * costruttore con parametri
	 */
    public Mezzo(String s, TipoMezzo tm, LocalDate pm, String t) {
    	this.stato= StatoMezzo.DISPONIBILE;
    	this.sigla=s;
    	this.tipo=tm;
    	this.prossimaManutenzione=pm;
    	this.targa=t;
    }

    /**
     * id incrementale 
     */
    private int idMezzo;

    /**
     * sigla del mezzo 
     */
    private String sigla;

    /**
     * tipo di mezzo (AMBULANZA ecc..)
     */
    private TipoMezzo tipo;

    /**
     * Stato del mezzo (in servizio, manutenzione, fuori servizio ecc...)
     */
    private StatoMezzo stato;

    /**
     * data della prossima manutenzione
     */
    private LocalDate prossimaManutenzione;

    /**
     * targa del mezzo 
     */
    private String targa;
    
    private List<Dotazione> dotazioni =new ArrayList<>();
    /**
     * il metodo isDisponibile controlla se il mezzo è disponibile per una determinata data
     */
    public boolean isDisponibile(LocalDate data, LocalTime inizio, LocalTime fine) {
       // TODO
        return false;
    }

    /**
     * il metodo cambiaStato cambia allo stato desiderato nuovoStato
     */
    public void cambiaStato(StatoMezzo nuovoStato) {
        this.stato=nuovoStato;
    }

    /**
     * il metodo verificaDotazioni verifica che tutte le dotazioni siano conformi, se anche solo 1 non è conferma allora restituisce false.
     */
    public boolean verificaDotazioni() {
        for(Dotazione d: dotazioni) {
        	if(d.isConforme()==false) {
        		return false;
        	}
        }
    	return true;
    }

	public StatoMezzo getStato() {
		return stato;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public TipoMezzo getTipo() {
		return tipo;
	}

	public void setTipo(TipoMezzo tipo) {
		this.tipo = tipo;
	}

	public LocalDate getProssimaManutenzione() {
		return prossimaManutenzione;
	}

	public void setProssimaManutenzione(LocalDate prossimaManutenzione) {
		this.prossimaManutenzione = prossimaManutenzione;
	}

	public int getIdMezzo() {
		return idMezzo;
	}

	public void setIdMezzo(int idMezzo) {
		this.idMezzo = idMezzo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}



}