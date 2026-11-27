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
	private Utente utenteAssegnato;
	private Turno turno;
	private List<PosizioneRequisito> requisiti= new ArrayList<>();

    /**
     * Default constructor
     */
    public PosizioneTurno() {
    }

    /**
     * 
     */
    private int idPosizione;

    /**
     * 
     */
    private String nomeRuolo;

    /**
     * 
     */
    private boolean obbligatoria;

    /**
     * 
     */
    private String descrizioneAttivita;

    /**
     * 
     */
    private LocalDate dataAssegnazione;

    /**
     * @return
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
        if(this.isLibera() && this.verificaRequisiti(u,this.turno.getData())) {
        	this.utenteAssegnato=u;
        	this.dataAssegnazione=LocalDate.now();
        }
    }

    /**
     * @return
     */
    public void liberaPosizione() {
       this.utenteAssegnato=null;
       this.dataAssegnazione=null;
    }

    /**
     * il metodo verificaRequisiti controlla se l'utente è attivo, se i requsiti non ci sono (quindi restituisce true senza controllare le qualifiche del'utente).
     * Poi controlla le qualifiche dell'utente per vedere se la qualifica richiesta in requisiti è valida (quindi se l'utente ce l'ha o non ce l'ha e se è valida alla data del turno)
     */
    public boolean verificaRequisiti(Utente u, LocalDate dataTurno) {
    	if (u == null || !u.isAttivo()) {
            return false;
        }
        if (this.requisiti == null || this.requisiti.isEmpty()) {
            return true;
        }
        for (PosizioneRequisito req : this.requisiti) {
            if (req.getQualifica() != null && !u.haQualificaValida(req.getQualifica().getCodice(), dataTurno)) {
                return false;
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
	
}