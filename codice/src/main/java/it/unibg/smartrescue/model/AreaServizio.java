package it.unibg.smartrescue.model;

import java.util.*;
import java.time.*;

/**
 * # Classe AreaServizio
 * 
 * Rappresenta le attività operative della sede CRI di Bergamo raggruppate nelle rispettive 
 * macro-aree. 
 * Contiene anche una descrizione in modo da poter far capire all'utente che cosa farà in quel 
 * servizio. 
 * C'è un attributo molto importante che è isContinuativo che indica che se il servizio
 * richiede copertura continua senza scoperture tra i cambi turno. 
 * I suoi metodi sono: - **necessitaMezzo()** che restituisce true se richiede un veicolo operativo 
 * assegnato ai propri turni, - **pianificaTurno(...)** che crea una nuova istanza di Turno associata 
 * all'area di servizio.
 */
public class AreaServizio {

    /**
     * Default constructor
     */
    public AreaServizio(String n, CategoriaServizio c, String d, boolean m, boolean co) {
    	this.nome=n;
    	this.categoria=c;
    	this.descrizione=d;
    	this.richiedeMezzo=m;
    	this.isContinuativo=co;
    }
    /**
     * 
     */
    private int idArea;

    /**
     * 
     */
    private String nome;

    /**
     * 
     */
    private CategoriaServizio categoria;

    /**
     * 
     */
    private String descrizione;

    /**
     * 
     */
    private boolean richiedeMezzo;

    /**
     * 
     */
    private boolean isContinuativo;

    /**
     * il metodo ritorna true se questo servizio richiede anche l'uso di mezzi
     */
    public boolean necessitaMezzo() {
        return this.richiedeMezzo;
    }

    /**
     * il metodo pianificaTurno crea un nuovo turno
     */
    public Turno pianificaTurno(LocalDate data, LocalTime inizio, LocalTime fine) {
        Turno t=new Turno(data, inizio, fine, this);
        return t;
    }

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaServizio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaServizio categoria) {
		this.categoria = categoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isContinuativo() {
		return isContinuativo;
	}

	public void setContinuativo(boolean isContinuativo) {
		this.isContinuativo = isContinuativo;
	}

}