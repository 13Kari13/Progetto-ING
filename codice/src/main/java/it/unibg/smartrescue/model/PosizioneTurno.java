package it.unibg.smartrescue.model;
import java.time.*;
import java.util.*;

/**
 * # Classe PosizioneTurno
 * 
 * Rappresenta il ruolo operativo previsto all'interno di un turno con anche una breve descrizione
 * di cosa dovrà fare. 
 * 
 * 
 * I suoi metodi sono: 
 * 
 * - **isLibera()** che verifica che sia ancora libera la posizione (se ad esempio
 * un utente vuole iscriversi viene fatta questa verifica prima di permettergli di farlo altrimeni 
 * gli viene dato il messaggio di errore)
 * 
 * - **assegnaVolontario(u: Utente)** che assegna ad un utente questa 
 * posizone
 * 
 * - **liberaPosizione()** che libera questo slot quando si cancella qualcuno, **verificaRequisiti(...)**
 * che invece verifica che l'utente abbia i requisiti per poter ricoprire questo ruolo nel turno
 */
public class PosizioneTurno {
    /**
     * Default constructor
     */
	
	public PosizioneTurno() {
	}
	
	/**
	 * costruttore con parametri
	 */
    public PosizioneTurno(String nr, boolean o, String da) {
    	this.nomeRuolo=nr;
    	this.obbligatoria=o;
    	this.descrizioneAttivita=da;
    	this.dataAssegnazione=null;
    	this.utenteAssegnato=null;
    }

    /**
     * id incrementale
     */
    private int idPosizione;

    /**
     * Il nome del ruolo all'interno del turno
     */
    private String nomeRuolo;

    /**
     * Indica se questa persona è obbligatoria da avere nel turno
     */
    private boolean obbligatoria;

    /**
     * Descrive le attività svolte da questo ruolo
     */
    private String descrizioneAttivita;

    /**
     * Registra la data di assegnazione di questo ruolo
     */
    private LocalDate dataAssegnazione;
    
    private Utente utenteAssegnato;
	private Turno turno;
	private List<PosizioneRequisito> requisiti= new ArrayList<>();


    /**
     * il metodo isLibera verifica se l'utente assegnato è nullo
     */
    public boolean isLibera() {
        return this.utenteAssegnato==null;
    }

    /**
     *il metodo assegnaVolontario fa un controllo per vedere se il turno c'è o no e poi in caso fa la verifica se è libera la posizione 
     *e poi fa la verifica dei requisiti sull'utente
     */
    public void assegnaVolontario(Utente u) {
    	LocalDate dataTurno= (this.turno!= null) ? this.turno.getData() : LocalDate.now();
        if(this.isLibera() && this.verificaRequisiti(u,dataTurno)) {
        	this.utenteAssegnato=u;
        	this.dataAssegnazione=LocalDate.now();
        }
    }

    /**
     * il metodo liberaPosizione toglie l'utente assegnato e rimette i valori a quelli iniziali
     */
    public void liberaPosizione() {
       this.utenteAssegnato=null;
       this.dataAssegnazione=null;
    }

    /**
     * controlla i requisiti obbligatori e poi quelli alternativi 
     */
    public boolean verificaRequisiti(Utente u, LocalDate dataTurno) {
    	if (u == null || !u.isAttivo()) {
            return false;
        }
        if (this.requisiti == null || this.requisiti.isEmpty()) {
            return true;
        }

        for (PosizioneRequisito req : this.requisiti) {
            if (req.appartieneAGruppo(0)) {
                if (req.getQualifica() == null 
                        || !u.haQualificaValida(req.getQualifica().getCodice(), dataTurno)) {
                    return false;
                }
            }
        }

        Set<Integer> gruppiControllati = new HashSet<>();
        for (PosizioneRequisito req : this.requisiti) {
            int gruppo = req.getGruppoAlternativa();
            if (gruppo > 0 && !gruppiControllati.contains(gruppo)) {
                gruppiControllati.add(gruppo);
                
                boolean almenoUnaValida = false;
                for (PosizioneRequisito r : this.requisiti) {
                    if (r.appartieneAGruppo(gruppo) && r.getQualifica() != null
                            && u.haQualificaValida(r.getQualifica().getCodice(), dataTurno)) {
                        almenoUnaValida = true;
                        break;
                    }
                }
                if (!almenoUnaValida) {
                    return false;
                }
            }
        }

        return true;
    }

	public int getIdPosizione() {
		return idPosizione;
	}

	public void setIdPosizione(int idPosizione) {
		this.idPosizione = idPosizione;
	}

	public String getNomeRuolo() {
		return nomeRuolo;
	}

	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	public boolean isObbligatoria() {
		return obbligatoria;
	}

	public void setObbligatoria(boolean obbligatoria) {
		this.obbligatoria = obbligatoria;
	}

	public String getDescrizioneAttivita() {
		return descrizioneAttivita;
	}

	public void setDescrizioneAttivita(String descrizioneAttivita) {
		this.descrizioneAttivita = descrizioneAttivita;
	}

	public LocalDate getDataAssegnazione() {
		return dataAssegnazione;
	}

	public void setDataAssegnazione(LocalDate dataAssegnazione) {
		this.dataAssegnazione = dataAssegnazione;
	}

	public Utente getUtenteAssegnato() {
		return utenteAssegnato;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public List<PosizioneRequisito> getRequisiti() {
	    return requisiti;
	}

	public void setRequisiti(List<PosizioneRequisito> requisiti) {
	    this.requisiti = requisiti;
	}
	public void setUtenteAssegnato(Utente u) {
		this.utenteAssegnato=u;
	}
	
}