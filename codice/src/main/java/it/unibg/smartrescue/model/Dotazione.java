package it.unibg.smartrescue.model;

import java.util.*;

/**
 * # Classe Dotazione
 * 
 * Rappresenta tutti gli strumenti utili presenti in un mezzo usato da CRI (bombole di ossigeno, DAE ecc..)
 * 
 * I suoi metodi sono: 
 * 
 * - **isConforme()**: restituisce true se il valore è sopra la soglia minima e stato OK.
 * - **simulaParametro()**: aggiorna su richiesta il valore telemtrico simulato dal dispositivo e ricalcola
 * lo stato
 */
public class Dotazione {

    /**
     * Default constructor
     */
    public Dotazione() {
    }

    /**
     * 
     */
    private int idDotazione;

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private String unitaMisura;

    /**
     * 
     */
    private double sogliaMinima;

    /**
     * 
     */
    private StatoDotazione stato;

    /**
     * 
     */
    private double valoreAttuale;

    /**
     * @return
     */
    public boolean isConforme() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void simulaParametro() {
        // TODO implement here
        
    }

}