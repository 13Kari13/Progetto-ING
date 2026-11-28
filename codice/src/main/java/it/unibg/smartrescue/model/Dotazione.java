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
	 *costruttore con parametri
	 */
    public Dotazione(String n, String um, double s, StatoDotazione st, double va) {
    	this.nome=n;
    	this.unitaMisura=um;
    	this.stato=st;
    	this.sogliaMinima=s;
    	this.valoreAttuale=va;
    }

    /**
     * id incrementale
     */
    private int idDotazione;

    /**
     * nome della dotazione (es. bombola ossigeno)
     */
    private String nome;

    /**
     * unità di misura usata per indicare quel valore 
     */
    private String unitaMisura;

    /**
     * soglia che deve superare per essere considerato a norma
     */
    private double sogliaMinima;

    /**
     * stato della dotazione (ok, carente ecc..)
     */
    private StatoDotazione stato;

    /**
     * valore a cui si trova attualmente la dotazione 
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