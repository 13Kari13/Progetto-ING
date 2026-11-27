package it.unibg.smartrescue.model;

import java.util.*;

/**
 * # Classe Configurazione
 * 
 * Questa classe memorizza i parametri e le soglie operative configurabili dal sistema( es. ripos, preavviso ecc..)
 * 
 * I suoi metodi sono: 
 * 
 * - ** getValoreDouble()**: converte il valore in numero deciamle per il confronto con le soglie telemetriche
 * 
 * - ** getValoreInt()** : converte il valore in numero intero
 */
public class Configurazione {

    /**
     * Default constructor
     */
    public Configurazione() {
    }

    /**
     * 
     */
    private String chiave;

    /**
     * 
     */
    private String valore;

    /**
     * 
     */
    private String descrizione;

    /**
     * il metodo getValoreInt restiruisce il numero intero del valore scritto 
     */
    public int getValoreInt() {
    	if (this.valore==null) {
            return 0;
        }
        return Integer.parseInt(this.valore.trim());
    }

    /**
     * il metodo getValoreDouble restituisce il valore double
     */
    public double getValoreDouble() {
    	if (this.valore == null) {
            return 0;
        }
        return Double.parseDouble(valore);
    }

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}