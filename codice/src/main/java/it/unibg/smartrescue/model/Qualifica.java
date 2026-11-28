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
    public Qualifica(String d, String t, Integer mv) {
    	this.denominazione=d;
    	this.tipologia=t;
    	this.mesiValidita=mv;
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
     * il metodo isPermanente controlla se i mesivalidia non sono segnati e se non sono segnati vuol dire che la qualifica non ha scadenze
     */
    public boolean isPermanente() {
        if(mesiValidita==null) {
        	return true;
        }
        return false;
    }

    /**
     * il metodo calcolaDataScadenza calcola la datadiascandeza aggiungendo i mesi di validita alla data di rilascio. Effettua prima un controllo per vedere se non è permanente la qualifica
     */
    public LocalDate calcolaDataScadenza(LocalDate dataRilascio) {
    	if(this.isPermanente()) {
    		return null;
    	}
        LocalDate dataScadenza=dataRilascio.plusMonths(mesiValidita);
        return dataScadenza;
    }

    /**
     * il metodo isPatente restituisce true se la tipologia è patente. In questo metodo ho dei dubbi se cambiare l'attributo tipologia in una enum 
     */
    public boolean isPatente() {
        if(this.tipologia=="PATENTE") {
        	return true;
        }
        return false;
    }

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Integer getMesiValidita() {
		return mesiValidita;
	}

	public void setMesiValidita(Integer mesiValidita) {
		this.mesiValidita = mesiValidita;
	}

}