package it.unibg.smartrescue.model;
import java.time.*;
import java.util.*;

/**
 * # Classe Turno
 * 
 * Rappresenta i turni pianificati per una specifica AreaServizio, in una determina data e fascia oraria, composta
 * da un insieme di posizioni da coprire e da un eventuale Mezzo associato. 
 * 
 * I  suoi metodi sono: 
 * 
 * - **isEquipaggioCOnforme()** che verifica che tutte le posizioni siano coperte da
 * volontari idonei
 * 
 * - **conferma ()** che porta il turno da INCOMPLETO a CONFERMATO solo se equipaggio conforme e mezzo
 * disponibile (se serve)
 * 
 * - **avvia()** che porta il turno nello stato OPERATIVO all'inizio dell'orario di servizio, **chiudi()** 
 * porta il turno a CONCLUSO al termine del servizio
 * 
 * - **annulla()** porta il turno in ANNULLATO (se non è ancora iniziato) e 
 * libera il mezzo associato (se associato)
 * 
 * - **calcolaCopertura()** restituisce il livello visivo di riempimento per il calendario
 * a semafori.
 */
public class Turno {
	
	private AreaServizio areaServizio;
	private Mezzo mezzo;
	private List<PosizioneTurno> posizioni=new ArrayList<>();

    /**
     * Default constructor
     */
    public Turno() {
    	this.stato=StatoTurno.INCOMPLETO; // impostiamo di default che sia incompleto
    }
    public Turno(LocalDate d, LocalTime i, LocalTime f, AreaServizio a) {
    	this.data=d;
    	this.oraInizio=i;
    	this.oraFine=f;
    	this.areaServizio=a;
    }

    /**
     * 
     */
    private int idTurno;

    /**
     * 
     */
    private LocalDate data;

    /**
     * 
     */
    private LocalTime oraInizio;

    /**
     * 
     */
    private LocalTime oraFine;

    /**
     * 
     */
    private String fasciaOraria;

    /**
     * 
     */
    private StatoTurno stato;

    /**
     * 
     */
    private String note;

    /**
     * il metodo isEquipaggioConforme controlla che tutte le posizioni siano coperte e se non l osono ritorna FALSE
     */
    public boolean isEquipaggioConforme() {
        if(posizioni==null || posizioni.isEmpty()) {
        	return false;
        }
        
        for(PosizioneTurno p: posizioni) {
        	if(p.isLibera()) {
        		return false;
        	}
        }
        return true;
    }

    /**
     *il metodo conferma cambia lo stato del turno a confermato dopo aver visto se il mezzo è assegnato e se l'equipaggio è conforme
     */
    public void conferma() {
    	boolean mezzoOk= (mezzo==null) || (mezzo.isDisponibile(this.data, this.oraInizio, this.oraFine) && mezzo.verificaDotazioni());
    	if(this.stato== StatoTurno.INCOMPLETO && isEquipaggioConforme() && mezzoOk) {
    		 this.stato=StatoTurno.CONFERMATO; 
    	}
    }

    /**
     * il metodo avvia cambia lo stato del turno a OPERATIVO e cambia stato al mezzo
     */
    public void avvia() {
       if (this.stato == StatoTurno.CONFERMATO) {
           this.stato = StatoTurno.OPERATIVO;
           if (mezzo != null) {
               mezzo.cambiaStato(StatoMezzo.IN_SERVIZIO);
           }
       }
    }

    /**
     * il metodo chiudi cambia lo stato del turno a CONCLUSO e cambia lo stato del mezzo a DISPONIBILE 
     */
    public void chiudi() {
       if(this.stato== StatoTurno.OPERATIVO) {
    	   this.stato= StatoTurno.CONCLUSO;
    	   if(mezzo!= null) {
    		   mezzo.cambiaStato(StatoMezzo.DISPONIBILE);
    	   }
       }
    }

    /**
     *il metodo annulla cambia lo stato del turno a ANNULLATO
     */
    public void annulla() {
        if(this.stato == StatoTurno.INCOMPLETO || this.stato==StatoTurno.CONFERMATO){
        	this.stato=StatoTurno.ANNULLATO;
        	if(posizioni!=null) {
        		for(PosizioneTurno p: posizioni) {
        			p.liberaPosizione();
        		}
        	}
        	if(mezzo!=null && mezzo.getStato()==StatoMezzo.IN_SERVIZIO) {
        		mezzo.cambiaStato(StatoMezzo.DISPONIBILE);
        	}
        }
    }

    /**
     * il metodo calcolaCopertura calcola il livello di copertura del turno 
     * Se le posizioni sono completamente vuote (quindi non c'è nessuno) il livello è ROSSO
     * se le posizioni sono tutte coperte--> VERDE
     * se le posizioni sono maggiori di 0 --> GIALLO
     */
    public LivelloCopertura calcolaCopertura() {
    	if (posizioni==null || posizioni.isEmpty()) {
            return LivelloCopertura.ROSSO;
        }
        int coperte = 0;
        for (PosizioneTurno p: posizioni) {
            if (!p.isLibera()) {
                coperte++;
            }
        }
        if (coperte==posizioni.size()) {
            return LivelloCopertura.VERDE;
        } else if (coperte>0) {
            return LivelloCopertura.GIALLO;
        } else {
            return LivelloCopertura.ROSSO;
        }
    }

	public AreaServizio getAreaServizio() {
		return areaServizio;
	}

	public void setAreaServizio(AreaServizio areaServizio) {
		this.areaServizio = areaServizio;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public String getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
    
    public LocalDate getData() {
    	return data;
    }
    public LocalTime getOraInizio() {
    	return oraInizio;
    }
    public LocalTime getOraFine() {
    	return oraFine;
    }
	public StatoTurno getStato() {
		return stato;
	}
}