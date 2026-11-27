package it.unibg.smartrescue.model;
import java.time.*;
import java.util.*;

/**
 * # Classe UtenteQualifica
 * 
 * Si tratta di un'association class, memorizza la data di conseguimento della qualifica 
 * (dataRilascio) e eventuale scadenza per verificare la validità nei turni.
 * I suoi metodi sono: 
 * 
 * - **isValidaAllaData(dataTurno: LocalDate)** che restituisce true se l'attributo
 * è NULL o se non è scaduta prima del turno
 * 
 * - **isInScadenza(giorniSoglia:int )** verifica che la scadenza
 * rientra nella finestra di preaviso configurabile consentendo di mostrare l'avviso in home e inviare
 * notifiche in caso
 * 
 * - **isScaduta()** controlla che la qualifica non sia scaduta al momento.
 */
public class UtenteQualifica {
	
	private Utente utente;
	private Qualifica qualifica;

    /**
     * Default constructor
     */
	
    public UtenteQualifica() {
    }

    /**
     * 
     */
    private LocalDate dataRilascio;

    /**
     * 
     */
    private LocalDate dataScadenza;

    /**
     * Il metodo isValidaAllaData controlla se la qualifica è valida alla data del turno e restituisce true se non scade entro quel giorno o se non ha data di scadeza quella qualifica
     * 
     */
    public boolean isValidaAllaData(LocalDate dataTurno) {
        if(this.dataScadenza==null || this.dataScadenza.isAfter(dataTurno)) {
        	return true;
        }
        return false;
    }

    /**
     * il metodo isInScadenza calcola quanti giorni mancano alla soglia dei giorni per inviare l'avviso all'utente che sta scadendo la qualifica
     */
    public boolean isInScadenza(int giorniSoglia) {
        if (this.dataScadenza == null || this.isScaduta()) {
            return false;
        }
        long giorniMancanti = LocalDate.now().datesUntil(this.dataScadenza).count();
        return giorniMancanti <= giorniSoglia;
    }

    /**
     * Il metodo isScaduta fa il check sulla data di oggi e la data di scadenza indicata nella qualifica ma se la scadenza è null (quindi una di quelle sempre valide)--> retituisc false
     * 
     */
    public boolean isScaduta() {
       if(this.dataScadenza==null || this.dataScadenza.isAfter(LocalDate.now()) ){
    	   return false;
       }
       return true;
    }

	public LocalDate getDataRilascio() {
		return dataRilascio;
	}

	public void setDataRilascio(LocalDate dataRilascio) {
		this.dataRilascio=dataRilascio;
	}
	
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza=dataScadenza;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Qualifica getQualifica() {
		return qualifica;
	}

	public void setQualifica(Qualifica qualifica) {
		this.qualifica = qualifica;
	}
	
}