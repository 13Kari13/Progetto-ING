
# Modelling 

## Use Case Diagram

Consigliato metterlo nella sezione dei requisiti
[immagine-diagramma-casi-d'uso]

## Class Diagram

consigliato metterlo nella sezione Software Design

![[diagramma_classi.jpg]]

#### Classi generate automaticamente da StarUML v5 e classi manuali


Dal Diagramma delle Classi (`documenti/UML/Diagramma delle classi/diagramma_classi.mdj`) sono state generate automaticamente da **StarUML v5** le seguenti **19 entità Java** nel package `it.unibg.smartrescue.model` (integrate con piccoli adattamenti manuali per gli `import` e i commenti Javadoc):

- **Classi di dominio generate (11)**:
  `AreaServizio`, `Configurazione`, `Dotazione`, `Mezzo`, `Notifica`, `PosizioneRequisito`, `PosizioneTurno`, `Qualifica`, `Turno`, `Utente`, `UtenteQualifica`.
- **Enumerazioni generate (8)**:
  `CategoriaServizio`, `LivelloCopertura`, `RuoloUtente`, `StatoDotazione`, `StatoMezzo`, `StatoTurno`, `TipoMezzo`, `TipoNotifica`.

##### Problemi emersi

Le classi generate contenevano però dei problemi:

* **Generazione metodi void con return** che davano errori nella compilazione (nell'immagine l'esempio del metodo simulaParametro() della classe Dotazione)
 ![[Metodi void.png]]

* **Mancanza di importazione librerie per attributi**: non sono state importate le librerie java.time.* per gli attributi LocalTime generando così errori nella compilazione (nell'immagine l'esempio della classe Mezzo)
* ![[Librerie time.png]]

Abbiamo risolto semplicemente togliendo i return null nei metodi void generati e importando ,nelle classi di cui avevano bisogno, le librerie java.time.* . 

___ 

#### Allineamento con il database SQLite (`schema.sql`):  

Le 11 classi di dominio corrispondono 1-a-1 alle 11 tabelle definite in `codice/src/main/resources/schema.sql`, mantenendo gli stessi identificativi e nomi degli attributi in `camelCase`.
**Classi sviluppate manualmente (non generate da StarUML):**

- `it.unibg.smartrescue.database.DatabaseManager`: implementata manualmente per gestire la connessione Singleton al database SQLite embedded in modalità WAL e il logging tramite Log4j 2.
- Classi applicative di persistenza (**DAO**), logica di business/validazione (**Service / Strategy**), interfaccia grafica (**Controller JavaFX**) e suite di test (**JUnit 5**), i cui corpi dei metodi e le query SQL richiedono sviluppo manuale.


## State machine diagram

[immagine-diagramma-macchina-a-stati]

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
