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
     * @return
     */
    public int getValoreInt() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public double getValoreDouble() {
        // TODO implement here
        return 0.0d;
    }

}