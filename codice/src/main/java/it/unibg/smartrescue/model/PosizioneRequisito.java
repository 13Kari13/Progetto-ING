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

    /**
     * @param gruppo 
     * @return
     */
    public boolean appartieneAGruppo(int gruppo) {
        // TODO implement here
        return false;
    }

}