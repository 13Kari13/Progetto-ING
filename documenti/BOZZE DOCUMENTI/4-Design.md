
# Modelling 

## Use Case Diagram



## Class Diagram

consigliato metterlo nella sezione Software Design

![[diagramma_classi.jpg]]

### Classi generate automaticamente da StarUML v5 e classi manuali

Dal Diagramma delle Classi (documenti/UML/Diagramma delle classi/diagramma_classi.mdj) sono state generate automaticamente da StarUML v5 le seguenti 19 entità Java nel package it.unibg.smartrescue.model (integrate con piccoli adattamenti manuali per gli import e i commenti Javadoc):

* **Classi di dominio generate (11):**
  AreaServizio, Configurazione, Dotazione, Mezzo, Notifica, PosizioneRequisito, PosizioneTurno, Qualifica, Turno, Utente, UtenteQualifica.
* **Enumerazioni generate (8):**
  CategoriaServizio, LivelloCopertura, RuoloUtente, StatoDotazione, StatoMezzo, StatoTurno, TipoMezzo, TipoNotifica.

#### Problemi emersi
Le classi generate contenevano però dei problemi:
* Generazione metodi void con return che davano errori nella compilazione (nell’immagine l’esempio del metodo simulaParametro() della classe Dotazione)
  ![[Metodi void.png]]
* Mancanza di importazione librerie per attributi: non sono state importate le librerie java.time.* per gli attributi LocalTime generando così errori nella compilazione (nell’immagine l’esempio della classe Mezzo)
  ![[Librerie time.png]]

Abbiamo risolto semplicemente togliendo i return null nei metodi void generati e importando, nelle classi che ne avevano bisogno, le librerie java.time.*.

#### Allineamento con il database SQLite (schema.sql)
Le 11 classi di dominio corrispondono 1-a-1 alle 11 tabelle definite in codice/src/main/resources/schema.sql, mantenendo gli stessi identificativi e nomi degli attributi in camelCase.

#### Classi sviluppate manualmente (non generate da StarUML)
* it.unibg.smartrescue.database.DatabaseManager: implementata manualmente per gestire la connessione Singleton al database SQLite embedded in modalità WAL e il logging tramite Log4j 2.
* Classi applicative di persistenza (DAO), logica di business/validazione (Service / Strategy), interfaccia grafica (Controller JavaFX) e suite di test (JUnit 5), i cui corpi dei metodi e le query SQL richiedono sviluppo manuale.

---

### State Machine Diagram

#### 1. Diagramma di Stato: Turno

Il ciclo di vita dell’entità Turno è modellato tramite una macchina a stati finiti (automa di Mealy) basata sull’enumerazione StatoTurno (INCOMPLETO, CONFERMATO, OPERATIVO, CONCLUSO, ANNULLATO). Le transizioni di stato sono governate da eventi applicativi o temporali, guardie di validazione sul dominio (Turno, PosizioneTurno, Utente, Mezzo) e invocazioni dei relativi metodi di business.

![[diagramma_stati_turno.jpg]]
##### Stati del Ciclo di Vita (StatoTurno)

| Stato | Tipo | Descrizione e Invarianti |
| :--- | :--- | :--- |
| **INCOMPLETO** | Iniziale / Operativo | Stato assunto alla creazione del turno. Almeno una posizione obbligatoria dell’equipaggio è scoperta oppure non sono ancora soddisfatti i requisiti minimi (isEquipaggioConforme() == false). Contribuisce a segnalare criticità nel calcolo di LivelloCopertura (GIALLO o ROSSO). |
| **CONFERMATO** | Operativo | Raggiunto tramite Turno.conferma() quando tutte le posizioni obbligatorie sono coperte da volontari idonei e la composizione dell’equipaggio risulta conforme (isEquipaggioConforme() == true). Il turno contribuisce positivamente alla copertura (LivelloCopertura.VERDE). |
| **OPERATIVO** | Attivo | Stato assunto all’orario di inizio del servizio tramite Turno.avvia(). Il turno è in corso di svolgimento: equipaggio e mezzo sono impegnati e non sono più ammesse defezioni o annullamenti. |
| **CONCLUSO** | Finale | Stato terminale raggiunto al termine regolare della fascia oraria tramite Turno.chiudi(). Consolida lo storico del servizio svolto e termina il ciclo di vita dell’istanza (Final State). |
| **ANNULLATO** | Finale | Stato terminale raggiunto tramite Turno.annulla() in caso di cancellazione del servizio prima dell’avvio (da INCOMPLETO o da CONFERMATO). Eventuali posizioni già assegnate vengono liberate (liberaPosizione()). |
##### Transizioni, Guardie e Azioni

* **Inizializzazione (Initial $\rightarrow$ INCOMPLETO):** all'evento creaTurno, l'attributo stato viene inizializzato a INCOMPLETO.
* **Assegnazione parziale (INCOMPLETO $\rightarrow$ INCOMPLETO):** all'evento assegnaVolontario, verificati posizione libera (isLibera), qualifiche valide (verificaRequisiti) e riposo minimo di 8 ore (rispettaRiposoMinimo), se l'equipaggio non è ancora completo (NOT isEquipaggioConforme) viene eseguita l'azione assegnaVolontario lasciando il turno incompleto.
* **Completamento equipaggio (INCOMPLETO $\rightarrow$ CONFERMATO):** al soddisfacimento delle stesse guardie di idoneità e al raggiungimento dell'equipaggio minimo (isEquipaggioConforme), esegue assegnaVolontario e conferma().
* **Gestione assenze (CONFERMATO $\rightarrow$ INCOMPLETO):** all'evento segnalaAssenza, se viene meno la conformità dell'equipaggio (NOT isEquipaggioConforme), invoca liberaPosizione() e riporta il turno allo stato INCOMPLETO.
* **Avvio del servizio (CONFERMATO $\rightarrow$ OPERATIVO):** allo scattare di raggiuntaOraInizio, verificati equipaggio conforme (isEquipaggioConforme), disponibilità del mezzo (isDisponibile) e integrità delle dotazioni (verificaDotazione), avvia il servizio tramite avvia().
* **Chiusura del servizio (OPERATIVO $\rightarrow$ CONCLUSO):** all'evento raggiuntaOraFine, chiude il turno tramite chiudi() conducendolo allo stato finale.
* **Annullamento (INCOMPLETO / CONFERMATO $\rightarrow$ ANNULLATO):** all'evento annullaTurno prima dell'avvio, cancella il servizio tramite annulla() e, se il turno era già confermato, esegue anche liberaPosizione().


## Sequence Diagram

[immagine-diagramma-sequenza]

## Communication Diagram / Timing Diagram

[immagine-diagramma-timing/comunicazione]

## Activity Diagram

[immagine-diagramma-attività]

## Component Diagram

[immagine-diagramma-componenti]
consigliato metterlo nella software architecture
## Package Diagram
consigliato o metterlo nella software architecture o insieme al class diagram
[immagine-diagramma-pacchetti]


# Software Architecture

[ ] DEVE contenere la descrizione dell’architettura con almeno un paio di architectural views (per differenti punti di vista) 
[ ] DOVREBBE avere almeno una vista con connettori e componenti con la descrizione dello stile architetturale (11.4) 
[ ] DEVE utilizzare almeno una libreria esterna con maven. Ad esempio l’uso di log4j è molto consigliata.

# Software Design pattern 

[ ]DEVE contenere una descrizione del design (mediante i diagrammi UML va bene) 

[ ] POTREBBE contenere un calcolo di complessità (ad esempio con McCabe) di una piccola parte 
[ ]DOVREBBE contenere qualche misurazione del codice, (con qualche metrica che abbiamo visto). Alcuni tools che vedremo a lezione: stanide, jdepend, struture101, sonarlint, PMD … 
[ ] DEVE applicare un paio di design pattern visti a lezione
