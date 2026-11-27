package it.unibg.smartrescue.model;
import java.time.*;
import java.util.*;

/**
 * # Classe PosizioneRequisito
 * 
 * Questa classe specifica quali qualifiche servono per ricoprire una determinata posizione in un turno
 * 
 * 
 * - ** appartieneAGruppo()**: verifica se il requisito fa parte dello specifico gruppo di valutazione
 */
public class PosizioneRequisito {

    /**
     * Default constructor
     */
    public PosizioneRequisito() {
    }

    /**
     * 
     */
    private int idRequisito;

    /**
     * 
     */
    private int gruppoAlternativa;
    private Qualifica qualifica;

    /**
     *restituisce se appartiene al gruppo delle qualifiche alternative
     */
    public boolean appartieneAGruppo(int gruppo) {
    	return this.gruppoAlternativa == gruppo;
    }

	public int getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(int idRequisito) {
		this.idRequisito = idRequisito;
	}

	public int getGruppoAlternativa() {
		return gruppoAlternativa;
	}

	public void setGruppoAlternativa(int gruppoAlternativa) {
		this.gruppoAlternativa = gruppoAlternativa;
	}

	public Qualifica getQualifica() {
		return qualifica;
	}

	public void setQualifica(Qualifica qualifica) {
		this.qualifica = qualifica;
	}
}