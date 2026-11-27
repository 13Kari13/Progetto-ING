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
        // TODO implement here
        return false;
    }

    /**
     * @param u 
     * @return
     */
    public void assegnaVolontario(Utente u) {
        // TODO implement here
        
    }

    /**
     * @return
     */
    public void liberaPosizione() {
        // TODO implement here
        
    }

    /**
     * @param u 
     * @param dataTurno 
     * @return
     */
    public boolean verificaRequisiti(Utente u, LocalDate dataTurno) {
        // TODO implement here
        return false;
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

}