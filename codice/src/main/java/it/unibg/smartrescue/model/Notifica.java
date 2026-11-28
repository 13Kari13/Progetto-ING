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
     * assegno letta di default come falsa
     */
    public Notifica(TipoNotifica ti, String m, LocalDateTime doc) {
    	this.setLetta(false); 
    	this.dataOraCreazione=doc;
    	this.messaggio=m;
    	this.tipo=ti;
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
     * il metodo segnaComeLetta cambia letta in true 
     */
    public void segnaComeLetta() {
        if(this.letta==false) {
        	this.letta=true;
        }
    }

	public int getIdNotifica() {
		return idNotifica;
	}

	public void setIdNotifica(int idNotifica) {
		this.idNotifica = idNotifica;
	}

	public TipoNotifica getTipo() {
		return tipo;
	}

	public void setTipo(TipoNotifica tipo) {
		this.tipo = tipo;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public LocalDateTime getDataOraCreazione() {
		return dataOraCreazione;
	}

	public void setDataOraCreazione(LocalDateTime dataOraCreazione) {
		this.dataOraCreazione = dataOraCreazione;
	}

	public boolean isLetta() {
		return letta;
	}

	public void setLetta(boolean letta) {
		this.letta = letta;
	}

}