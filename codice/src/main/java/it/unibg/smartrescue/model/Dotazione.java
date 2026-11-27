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
     * il metodo isConforme controlla se il valore supera la soglia minima e il suo stato è OK
     */
    public boolean isConforme() {
        if(this.valoreAttuale> this.sogliaMinima && this.stato== StatoDotazione.OK) {
        	return true;
        }
        return false;
    }

    /**
     * @return
     */
    public void simulaParametro() {
        // TODO implement here
    }

	public int getIdDotazione() {
		return idDotazione;
	}

	public void setIdDotazione(int idDotazione) {
		this.idDotazione = idDotazione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

}