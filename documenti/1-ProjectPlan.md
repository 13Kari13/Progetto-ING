# PROJECT PLAN

#  SMART RESCUE SYSTEM 

Project Plan di sviluppo del software: **Smart Rescue System**, un’applicazione per la gestione operativa e telemetrica della Croce Rossa Italiana, che integra in tempo reale: turni dei volontari, qualifiche salvavita e dello stato dei mezzi attraverso un’interfaccia intelligente e sicura.

|  |  |
| :---- | :---- |
| Titolo | Smart Rescue System – Project Plan  |
| Autori | Derouich Sukaina, Frimane Karima, Laklaa Marwa |
| Data creazione  | 9 ottobre 2026 |
| Data ultima modifica | 10 novembre 2026 |
| Versione | 1.1 |

# 1\. Introduzione 

Durante l’A.A. 2026/2027, un team di tre studentesse di ingegneria informatica decide di sviluppare un’applicazione per la gestione integrata dei turni, della flotta e dei servizi per la Croce Rossa Italiana(CRI), denominata **Smart Rescue System.**

## 1.1 Background e motivazione {#1.1-background-e-motivazione}

L’idea del progetto nasce dall’esperienza diretta di un membro del nostro gruppo di lavoro come volontaria presso la Croce Rossa Italiana. Analizzando la piattaforma in uso per la gestione dei turni dei volontari (*Turnapp*), abbiamo individuato quattro problemi principali: 

* **Blocco senza spiegazione:** Se il volontario non ha i requisiti per un turno, il sistema impedisce l’iscrizione senza indicare quale qualifica manca o è scaduta, e il volontario non sa come risolvere  
* **Assenze gestite a mano:** Il volontario non può annullare da solo il proprio turno: deve chiederlo ad un amministratore. Non esiste un flusso che segnali l’assenza e cerchi un sostituto disponibile e qualificato per quella posizione   
* **Controllo limitato sui mezzi:** Il sistema non verifica che l’ambulanza sia disponibile, cioè non guasta, non in manutenzione e non già impegnata, quando il turno viene confermato.  
* **Servizi poco distinguibili:** L’interfaccia presenta in un unico elenco attività molto diverse (emergenza 118, mensa, Bimbambulanza, sportello sociale) , e questo rende difficile orientarsi e capire la copertura delle giornate.

Nei turni di ambulanza i volontari sono distinti per posizione in equipaggio, e ciascuna posizione richiede qualifiche diverse. Smart Rescue System modella questa distinzione in modo esplicito. 

## 1.2 Obiettivi del progetto

L'obiettivo principale è sviluppare un'applicazione a oggetti con tre ruoli utente: 

* **Volontario (Operatore):** Visualizza i turni, si candida alle posizioni compatibili con le proprie qualifiche e segnala una propria assenza.   
* **Referente di Servizio / Capoturno:** supervisiona la composizione delle squadre, valida i turni e gestisce le assenze impreviste attivando la ricerca e l'assegnazione automatica dei sostituti idonei.  
* **Amministratore di Comitato:** gestisce la pianificazione generale delle attività e monitora lo stato della flotta veicoli tramite una simulazione di telemetria IoT (es. verifica a bordo delle bombole di ossigeno e dei dispositivi elettromedicali).

I nostri obiettivi funzionali invece includono: 

* Verificare i requisiti per posizione (patente, brevetti, riposo minimo) e , in caso di blocco, indicare quali qualifiche mancano o sono scadute e quando  
* Gestire l’assenza con una procedura guidata e con proposta di sostituti idonei (Smart Dispatch)   
* Confermare un turno solo se il mezzo associato è disponibile. 

## 1.3 Risultati attesi

Attraverso lo sviluppo di Smart Rescue System ci proponiamo di azzerare gli errori di assegnazione nei turni rendendo impossibili le formazioni non a norma. Quando un volontario non può iscriversi a un turno, il sistema indicherà in modo specifico quale qualifica manca o è scaduta e la relativa data di scadenza, e la segnalazione di un’assenza avvierà la proposta di sostituti idonei per la posizione scoperta.

Dal punto di vista dell’usabilità, i servizi saranno riorganizzati in una dashboard chiara con calendario a semafori visivi (verde, giallo, rosso) per monitorare la copertura delle giornate, includendo anche attività speciali come la Bimbambulanza.

Infine, sul piano didattico e ingegneristico, l’obiettivo è applicare le best practice viste a lezione realizzando un’architettura a tre layer (MVC) supportata dai principali Design Pattern (DAO, Factory, Singleton, Strategy) e validata tramite test unitari con JUnit 5\. Dal punto di vista tecnico e dell’ambiente di sviluppo, il software sarà realizzato interamente come progetto Java gestito tramite Maven e sviluppato nell’IDE Eclipse, garantendo la totale portabilità del codice.

# 2\. Modello di Processo

## 2.1 Selezione del Modello di Processo

Per lo sviluppo di **Smart Rescue System** abbiamo scelto un **modello di processo iterativo e incrementale**, prendendo spunto da alcuni principi delle metodologie Agile ( in particolare la Kanban Board e la prioritizzazione MoSCoW):

* **Iterativo:** Ci permette di dividere il progetto in cicli di lavoro più piccoli. In ogni ciclo progettiamo, scriviamo il codice e testiamo subito la singola parte con JUnit, prima di passare a quella successiva.  
* **Incrementale:** Consente di avere fin da subito una prima versione funzionante della logica principale del sistema, evitando di dover integrare tutto il codice solo alla fine del lavoro.  
* **Motivazione:** Il nostro programma deve mettere insieme parti diverse tra loro (il controllo delle regole dei turni, il database locale e la simulazione dei parametri delle ambulanze), quindi procedere per passi successivi è la scelta più sicura per non commettere errori.

Non abbiamo scelto il modello a cascata perché le regole del dominio CRI sono complesse e vogliamo verificarle presto con i test. Non abbiamo scelto Scrum completo perché il team è composto da tre persone, non ha Product Owner e ha una scadenza fissa.

## 2.2 Attività Principali e Moduli di Sviluppo

Il lavoro è stato suddiviso in quattro fasi principali, in parte sovrapposte tra loro:  
**Fase 1 – Analisi dei Requisiti e Modellazione Dati (Settimane 1-2):**

* Definizione dei requisiti secondo lo standard IEEE 830 e prioritizzazione MoSCoW;  
* Modellazione dei casi d'uso e degli scenari operativi CRI tramite StarUML v5;  
* Progettazione concettuale della base dati e redazione dello script schema.sql per SQLite;  
* Setup del repository GitHub e configurazione dell'ambiente Maven.

**Fase 2 – Sviluppo del Core Backend e Validazione JUnit (Settimane 3-4):**

* Implementazione delle entità di Dominio e del layer DAO per la persistenza locale;  
* Sviluppo della Business Logic: motore di validazione turni (Pattern Strategy per brevetti e riposo);  
* Implementazione dei moduli specialistici di backend: algoritmo di Smart Dispatch e gestione telemetrica della flotta;  
* Scrittura e superamento al 100% della suite di test unitari con JUnit 5 (copertura target \> 80%);

**Fase 3 – Sviluppo Interfaccia Grafica JavaFX e Integrazione MVC (Settimane 5-6):**

* Disegno visuale delle viste dichiarative (.fxml) con Scene Builder e stilizzazione CSS;  
* Scrittura dei Controller JavaFX e collegamento con il Service Layer;  
* Esecuzione delle query asincrone tramite javafx.concurrent.Task per mantenere fluida la UI;  
* Realizzazione della dashboard a schede per aree di servizio e del calendario a semafori.

**Fase 4 – Testing di Sistema, Collaudo e Rilascio Finale (Settimane 7-8):**

* Feature freeze: blocco dell'aggiunta di nuove funzionalità per concentrarsi sulla stabilità;  
* Esecuzione dei test end-to-end e verifica manuale dell'eseguibile sui tre OS del team (Windows, Linux, macOS);  
* Allineamento finale della documentazione tecnica, diagrammi UML e tag di rilascio v1.0.

## 2.3 Pietre Miliari e Criteri di Verifica {#2.3-pietre-miliari-e-criteri-di-verifica}

Per controllare che il lavoro stia procedendo nei tempi stabiliti abbiamo fissato quattro traguardi principali (milestone), ciascuno legato a un obiettivo preciso:

* **MS1 – Requisiti e database pronti:** Approvazione dei diagrammi UML iniziali e creazione del file del database SQLite con le tabelle funzionanti.  
* **MS2 – Logica di controllo funzionante:** Completamento delle regole di assegnazione dei turni e superamento di tutti i test unitari scritti in JUnit 5 e del Service Layer (login, turni, controllo del mezzo, assenze e Smart Dispatch base)  
* **MS3 – Integrazione dei moduli:** Verifica del corretto collegamento tra viste JavaFX, Service Layer e database, inclusi i moduli per le sostituzioni e per la flotta;  
* **MS4 – Consegna del progetto:** Applicazione completa con interfaccia grafica JavaFX funzionante, verificata tramite prove manuali e pronta per la valutazione d'esame.

Alle milestone tecniche si aggiungono due scadenze di consegna esterne:

* **E1- ProjectPlan:** consegna del documento tramite Issue GitHub ai docenti, almeno 1 mese prima dell’appello d’esame  
* **E2- Repository finale:** invio definitivo 5 giorni prima della prova orale. 

## 2.4 Percorso Critico {#2.4-percorso-critico}

Analizzando le dipendenze tra le varie parti del programma, il percorso critico è rappresentato dalla seguente sequenza:

**Classi di Dominio e DAO (SQLite) → Regole di validazione turni e Test (JUnit 5\) → Service Layer (Login e Gestione Turni) → Controller e Interfaccia JavaFX → Collaudo e Rilascio**

*Perché è critico:* Se tardiamo a definire la logica di controllo sui turni e sulle qualifiche dei volontari, non possiamo implementare l'algoritmo per le sostituzioni e resterà bloccato anche lo sviluppo dei controller dell'interfaccia JavaFX.

*Come gestiamo il rischio:* Per evitare blocchi, scriviamo i test unitari JUnit insieme al codice della logica di business, assicurandoci che funzioni tutto correttamente prima di iniziare a disegnare le schermate grafiche.

# 3\. Organizzazione del progetto

## 3.1 Relazioni con Enti ed Utenti

L'organizzazione delle relazioni esterne tiene conto del contesto accademico in cui si inserisce il progetto:

* **Docenti di riferimento:** Il team si interfaccia con i docenti del corso (Prof. Angelo Gargantini e Dott.ssa Silvia Bonfanti) durante le ore di laboratorio e i momenti di ricevimento per validare le scelte architetturali e lo stato di avanzamento del lavoro.  
* **Interazione con utenti reali:** Non essendoci una committenza formale esterna, non avremo un collaudo diretto sul campo da parte di utenti terzi. La verifica del software verrà quindi eseguita direttamente da noi studenti tramite test e simulazioni di scenari d'uso tipici della sede CRI.  
* **Esperienza diretta sul dominio:** Un punto di forza del gruppo è la presenza di un membro attivo come volontario soccorritore della Croce Rossa Italiana. Questo ci consente di comprendere le procedure operative reali della sede e di analizzare direttamente l'applicativo Turnapp attualmente in uso, identificandone i difetti pratici e traducendo le esigenze del personale in requisiti software.

## 3.2 Ruoli

Trattandosi di un gruppo composto da sole tre persone, abbiamo scelto un'organizzazione orizzontale e flessibile, adottando i principi di un team agile:

* **Assenza di gerarchie rigide:** Non prevediamo ruoli chiusi o prefissati (come un project manager dedicato); ogni componente deve possedere un buon livello di autodisciplina e spirito di adattamento.  
* **Versatilità dei membri:** Ciascuna studentessa parteciperà a tutte le fasi principali del ciclo di vita:  
  * Modellazione dei diagrammi UML;  
  * Sviluppo del backend in Java (logica di business e DAO);  
  * Creazione dell'interfaccia grafica con JavaFX e Scene Builder;  
  * Scrittura ed esecuzione dei test unitari con JUnit 5\.  
* **Stile di comunicazione:** Lo scambio di informazioni tra noi sarà continuo e poco formale per velocizzare il lavoro quotidiano, mentre la documentazione di progetto manterrà un livello di formalità adeguato agli standard richiesti per l'esame.

Tracciabilità del contributo: il lavoro di ciascuna componente è documentata dai commit, dalle issue e dalle Pull Request su GitHub. 

## 3.3 Conoscenze richieste minime

Per garantire che ciascun membro del team possa contribuire attivamente a tutte le attività di sviluppo, sono state individuate le seguenti competenze di base:

* **Programmazione orientata agli oggetti:** Conoscenza del linguaggio Java (ereditarietà, polimorfismo, incapsulamento e gestione delle eccezioni).  
* **Controllo di versione:** Utilizzo di Git per il lavoro collaborativo (gestione di branch, commit, pull request e risoluzione di eventuali conflitti) tramite GitHub.  
* **Modellazione UML:** Capacità di leggere e produrre i diagrammi essenziali previsti dal corso (Use Case, Classi, Sequenza, Stati, ecc.).  
* **Interfacce grafiche:** Basi di sviluppo di interfacce desktop tramite JavaFX e gestione degli eventi.  
* **Linguaggio di scrittura:** Conoscenza e uso corretto della lingua italiana, sia a livello grammaticale che nell'uso della terminologia tecnica informatica.

# 4\. Standard, linee guida, procedure

## 4.1 Standard di Codifica {#4.1-standard-di-codifica}

Si adottano le convenzioni ufficiali Oracle: 

* **Variabili e Metodi:**  
  * Variabili locali e nomi dei metodi in *lowerCamelCase*.  
  * Variabili membro private sempre lette e modificate tramite metodi Get e Set.  
* **Costanti:** Dichiarate come *`static final`* e scritte interamente in MAIUSCOLO con underscore per separare le parole.  
* **Nomi delle Classi:** In *UpperCamelCase*, sostantivi semplici e descrittivi senza abbreviazioni. Le interfacce seguono la stessa regola  
* **Package:** tutto minuscolo  
* **Organizzazione dei file:** una sola classe pubblica per file, con un commento iniziale (nome classe, versione, data).  
* **Indentazione e Formattazione:** Uso dell’indentazione standard a 4 spazi, impostata in modo uniforme nell'IDE (Eclipse). Parentesi graffe sempre presenti negli if e una sola istruzione per riga.   
* **Commenti e Documentazione nel Codice:**  
  * Commenti inline (`//`) riservati unicamente alla spiegazione di passaggi logici complessi o regole aziendali particolari.  
  * Commenti Javadoc (`/** ... */`) obbligatori per documentare classi, interfacce e metodi pubblici principali (indicando parametri di input `@param` e valori di ritorno `@return`).

## 4.2 Linee Guida per la Documentazione

* **Lingua e registro:** Tutti i documenti sono redatti in lingua italiana, utilizzando un registro tecnico, chiaro e rigoroso.  
* **Condivisione dei file:** I documenti di analisi e le bozze testuali vengono condivisi su Google Drive per consentire la revisione collaborativa tra i membri del gruppo, mentre le versioni definitive vengono caricate all'interno del repository GitHub di progetto, nella cartella /documenti.  
* **Struttura e intestazioni:** Ciascun documento deve presentare una prima sezione indicante il titolo del file, gli autori, la data di redazione e la versione dell'ultima modifica apportata.  
* **Stabilità della struttura:** Qualora durante l'evoluzione del progetto si decida di eliminare un requisito o un paragrafo, abbiamo stabilito di non riordinare la numerazione delle sezioni successive. Questa scelta serve a evitare errori o incongruenze con i riferimenti incrociati già presenti all'interno del testo.

## 4.3 Procedure di Gestione del Codice

Il codice sorgente dell'applicazione è gestito tramite un repository condiviso su GitHub.

* **Struttura dei Branch:**  
  * Branch principale (`main`): Contiene esclusivamente le versioni stabili, compilabili e prive di errori del software. Non è consentito effettuare modifiche e push diretti su questo ramo.  
  * Branch di sviluppo: Utilizzati dai singoli membri per sviluppare e testare nuove funzionalità in isolamento, creando un branch dedicato.  
* **Convenzione dei Commit:** Ogni *commit* deve presentare un messaggio chiaro e sintetico che descriva l'intervento effettuato.  
* **Pull Request e Revisione:** L'integrazione di un branch secondario all'interno del main avviene mediante una Pull Request. Prima del merge, la richiesta deve essere esaminata da almeno un altro membro del team, che verifica che il codice compili correttamente e sia coerente con gli standard definiti.  
* **Organizzazione Repository e File di Configurazione**: Il repository su GitHub sarà organizzato separando nettamente la documentazione dal codice sorgente, strutturando le cartelle principali in **/documenti** e **/codice.** Nel file **.gitignore** verranno esclusi i file generati dalla compilazione e dall’esecuzione. **I file .project e .classpath NON saranno inseriti nel .gitignore**, per consentire ai docenti l'importazione diretta dei progetti su Eclipse senza errori.

## 4.4 Standard di Architettura e Design {#4.4-standard-di-architettura-e-design}

Il software segue un'architettura modulare a tre layer basata sul Pattern **MVC (Model-View-Controller)** che garantisce la separazione dei compiti:

* **Model:** Comprende le classi del dominio, la logica di business dell'applicazione e la gestione della persistenza dei dati. Per la memorizzazione utilizziamo un database SQLite integrato, comunicando con esso tramite il pattern DAO (Data Access Object) per mantenere separate le query SQL dalla logica del programma*.* La gestione delle dipendenze del progetto Java e delle librerie esterne avverrà mediante Maven (**`pom.xml`**), integrando obbligatoriamente il framework di logging **`log4j`** per il tracciamento delle operazioni di sistema.  
* **View:** Rappresenta l'interfaccia utente grafica (GUI). Viene implementata in JavaFX utilizzando file dichiarativi .fxml costruiti mediante lo strumento Scene Builder, arricchiti da fogli di stile .css per la formattazione grafica.  
* **Controller:** Gestisce il flusso tra la Vista e il Modello. Raccoglie gli eventi scatenati dall'utente nell'interfaccia (come la pressione di pulsanti), invoca i metodi corrispondenti nel Modello e aggiorna la Vista con le nuove informazioni.

**Modellazione UML:** 

La progettazione del sistema viene formalizzata attraverso i seguenti diagrammi UML, ciascuno dedicato a uno specifico aspetto dell'applicazione:

* **Diagramma dei Casi d'Uso:** Definisce le funzionalità del sistema accessibili ai diversi ruoli utente (Volontario, Capoturno e Amministratore), come l'iscrizione ai turni, la visualizzazione del calendario e la gestione delle emergenze/sostituzioni.  
* **Diagramma delle Classi:** Struttura il codice dell'applicazione evidenziando le entità di dominio (Volontario, Turno, Ambulanza), i componenti del layer DAO per l'accesso a SQLite e i Controller dell'interfaccia JavaFX con le relative relazioni.  
* **Diagramma di Sequenza:** Modella lo scambio temporale di messaggi tra View, Controller e Database durante due scenari chiave: la registrazione di un volontario a un turno e la segnalazione di un'assenza improvvisa.  
* **Diagramma delle Attività:** Descrive il flusso logico dell'algoritmo di validazione delle qualifiche, verificando la validità delle certificazioni (patenti, BLSD) e la conformità dei requisiti minimi dell'equipaggio prima dell'assegnazione.  
* **Diagramma degli Stati:** Rappresenta il ciclo di vita e le transizioni di stato dell'entità Turno (Incompleto, Confermato, Operativo, Concluso, Annullato).  
* **Diagramma delle Comunicazioni:** Mostra la collaborazione strutturale tra gli oggetti del sistema durante il processo di notifica e reperimento di un sostituto per un turno scoperto.  
* **Diagramma Temporale**: Modella il comportamento degli stati rispetto ai vincoli di tempo obbligatori del regolamento, con particolare attenzione al rispetto delle 8 ore minime di riposo tra due turni consecutivi.  
* **Diagramma di Deployment:** Rappresenta la configurazione fisica a livello locale, mostrando la distribuzione dell'applicazione desktop (Java/JavaFX) e la collocazione del file di database SQLite sul filesystem dell'utente.

La progettazione UML viene formalizzata mediante l'uso del software StarUML v5 (tool adottato ufficialmente per il corso). Il relativo file di progetto **`.mdj`** con tutti i diagrammi verrà caricato su GitHub all'interno della cartella **`/documenti`.**

## 4.5 Procedure di Testing {#4.5-procedure-di-testing}

* **Test Unitari:** Sviluppati tramite framework **JUnit 5** per verificare isolatamente il funzionamento dei metodi della Business Logic (es. corretta identificazione di una patente scaduta, calcolo del rispetto delle 8 ore di riposo).  
* **Test di Integrazione:** Verificano la corretta comunicazione tra i moduli software del sistema, con particolare attenzione all'interazione tra i componenti DAO e il database SQLite.  
* **Test di Sistema e Accettazione:** Vengono svolti manualmente da noi studenti eseguendo l'applicazione completa attraverso l'interfaccia JavaFX, simulando scenari operativi reali con dati plausibili (login, inserimento dati, gestione turni e sostituzioni) per individuare tempestivamente eventuali comportamenti imprevisti.  
* **Obiettivo di Copertura:** Ci poniamo come obiettivo di raggiungere una copertura minima superiore all'80% per quanto riguarda i metodi e le diramazioni logiche delle classi del package di Business Logic.

## 4.6 Procedure di Comunicazione

* **Incontri di Alignment:** Riunioni a cadenza settimanale, svolte nei laboratori universitari o da remoto su piattaforme come Google Meet o Discord, per valutare lo stato di avanzamento e pianificare le attività successive.  
* **Comunicazione Rapida:** Utilizzo di un canale di messaggistica istantanea (gruppo WhatsApp) per chiarire dubbi estemporanei sul codice o condividere avvisi urgenti.  
* **Verbali e Decisioni:** Tracciamento delle decisioni architetturali più rilevanti all'interno di un file condiviso su Google Drive, accessibile a tutti i componenti del gruppo.

# 5\. Attività di gestione 

Durante lo sviluppo cercheremo di bilanciare requisiti, tempi e risorse per evitare ritardi nella consegna finale.

## 5.1 Monitoraggio dello Stato di Avanzamento

Per verificare lo stato del lavoro organizziamo brevi riunioni periodiche tra i membri del gruppo, sia di persona all'università che online. In questi incontri analizziamo cosa è stato completato, i problemi riscontrati nel codice Java e le prossime attività da svolgere, annotando i progressi principali in un report condiviso. Il controllo del codice avviene in parallelo tramite GitHub, monitorando i commit e le modifiche apportate alle classi.

L’avanzamento si misura con tre indicatori: il numero di card in Done rispetto al totale sul Kanban, lo stato delle milestone MS1-MS4 su Github e l’esito dei test JUnit. Se si rileva un ritardo in una milestone, si ripianifica spostando i requisiti Could-have fuori dal progetto, senza ridurre i Must-have.

## 5.2 Prioritizzazione dei Requisiti

Per gestire al meglio il tempo a disposizione (2 mesi) e non rischiare di accumulare lavoro arretrato, applichiamo la metodologia **MoSCoW** per classificare le funzionalità da sviluppare:

* **Must-have (Indispensabili):** Comprendono funzionalità indispensabili per soddisfare i requisiti del progetto tali come l'autenticazione login, l'anagrafica dei volontari, la gestione del database SQLite e il motore di validazione per qualifiche, scadenze e ore di riposo, controllo della disponibilità del mezzo alla conferma del turno, dashboard con calendario a semafori;  
* **Should-have (Importanti):** Funzionalità di grande valore che completano il sistema: segnalazione di assenza con proposta di sostituti idonei (Smart Dispatch) , la scheda delle ambulanze con simulazione dei parametri IoT (ossigeno, dispositivi). I valori sono simulati con aggiornamento su richiesta.   
* **Could-have (Opzionali):** Funzionalità aggiuntive da implementare solo se avanzano tempo e risorse, come migliorie dell’algoritmo di Smart Dispatch per le sostituzioni automatiche, aggiornamento automatico periodico dei parametri IoT, storico delle letture e avvisi.  
* **Won't-have (Non previste):** Funzionalità complesse che esulano dallo scopo del corso e dai due mesi di lavoro, come l'applicazione mobile o l'invio di SMS reali.

## 5.3 Organizzazione del Lavoro tramite GitHub Kanban Board

Utilizziamo la **Kanban Board** integrata direttamente su GitHub per tracciare visivamente tutte le task di sviluppo e coordinare il lavoro dei tre membri del team. La board è strutturata nelle seguenti colonne:

1. **To Do (Da fare):** Elenco delle cose da sviluppare, ordinate in base alle priorità MoSCoW.  
2. **In Progress (In corso):** Task su cui uno dei membri sta attualmente lavorando.  
3. **In Review / Testing:** Moduli e classi Java completati che richiedono la verifica da parte di un altro compagno prima dell'integrazione.  
4. **Done (Completate):** Funzionalità testate con successo e pubblicate sul branch principale.

Una card passa in Done solo quando la Pull Request è approvata e i test associati sono verdi. 

# 6\. GESTIONE DEI RISCHI 

L'analisi dei rischi identifica le potenziali criticità tecniche, di dominio e organizzative che potrebbero impattare sullo sviluppo dello **Smart Rescue System**, definendo per ciascuna le relative azioni di mitigazione e prevenzione.

## 6.1 Matrice di Sintesi dei Rischi

| ID | Categoria | Descrizione Rischio | Probabilità (P) | Impatto (I) | Mitigazione Principale |
| :---- | :---- | :---- | :---- | :---- | :---- |
| **R1** | **Tecnico** | Blocco DB SQLite per accessi concorrenti | Media | Alto | Pattern Singleton \+ metodi DAO sincronizzati |
| **R2** | **Tecnico** | Freezing dell'interfaccia utente JavaFX | Media | Medio | Separazione MVC tramite viste FXML; gestione delle chiamate al database (javafx.concurrent.Task)  |
| **R3** | **Dominio** | Errata gestione regole e qualifiche CRI | Alta | Alto | Pattern Strategy \+ test unitari con JUnit 5 |
| **R4** | **Organizzativo** | Conflitti di codice durante i merge su GitHub | Media | Medio | Workflow Feature Branch \+ Pull Request con revisione |
| **R5** | **Strumentazione** | Incompatibilità di build tra ambienti diversi | Bassa | Alto | Progetto Maven standardizzato su Eclipse e Java 21+ prova di avvio da zero su ogni computer |
| **R6** | **Pianificazione** | Tempo insufficiente per completare le funzionalità | Alta | Alto | Prioritizzazione MoSCoW e ripianificazione alle milestone |

## 

## 6.2 Dettaglio dei Rischi e Strategie di Mitigazione

* **R1 \- Concorrenza e Blocco del Database SQLite (Rischio Tecnico)**  
  * **Problema**: Accessi simultanei in scrittura (es. due utenti che si iscrivono allo stesso turno per lo stesso ruolo) possono generare l'errore SQLITE\_BUSY.  
  * **Mitigazione**: Centralizzazione dell'oggetto Connection tramite **Pattern Singleton**, sincronizzazione dei metodi di scrittura nel layer **DAO** e abilitazione della modalità WAL (*Write-Ahead Logging*) all'avvio della connessione.  
* **R2 \- Blocco dell'Application Thread JavaFX (Rischio di Usabilità)**  
  * **Problema:** Se query al database SQLite (es. il caricamento di decine di turni nel calendario) vengono eseguite sullo stesso thread che disegna la grafica, la finestra potrebbe bloccarsi temporaneamente per qualche frazione di secondo.  
  * **Mitigazione:** Netta separazione secondo il pattern MVC: i Controller JavaFX gestiscono solo gli eventi di visualizzazione. Per le operazioni su SQLite che leggono o scrivono molti record, si incapsula l'invocazione del DAO all'interno della classe nativa Task di JavaFX, aggiornando la vista solo al completamento dei dati.  
* **R3 \- Complessità delle Regole di Validazione CRI (Rischio di Dominio)**  
  * **Problema**: Sovrapposizione di vincoli stringenti (brevetti scaduti, rispetto delle 8 ore di riposo minime, patenti speciali CRI) con rischio di approvare turni non a norma.  
  * **Mitigazione**: Isolamento delle regole di ingaggio con il **Pattern Strategy** e validazione continua tramite una suite di test **JUnit 5** focalizzata sui casi limite nel package service.  
* **R4 \- Conflitti di Integrazione del Codice su GitHub (Rischio Organizzativo)**  
  * **Problema**: Sviluppo parallelo di moduli differenti da parte delle tre componenti del team con rischio di *merge conflicts* complessi.  
  * **Mitigazione**: Divieto di push diretto sul ramo main, utilizzo di **Feature Branches** individuali, obbligo di **Pull Request (PR)** con approvazione di almeno un'altra sviluppatrice e tracciamento task su **GitHub Kanban Board**.  
* **R5 \- Incompatibilità dell'Ambiente di Build (Rischio Strumentazione)**  
  * **Problema**: Differenze di sistema operativo o configurazione IDE che impediscono l'avvio del progetto o l'importazione da parte dei docenti.  
  * **Mitigazione**: Adozione esclusiva di **Java 21**, **Eclipse IDE** e **Maven** (pom.xml). Inclusione obbligatoria nel repository dei file di configurazione .project e .classpath, escludendo nel .gitignore esclusivamente la cartella target/.   
* **R6 \- Tempo insufficiente per completare le funzionalità (Rischio di Pianificazione)**  
  * **Problema:** Rallentamenti imprevisti nello sviluppo o difficoltà tecniche che rischiano di ritardare il rilascio finale.  
  * **Mitigazione:** Rigorosa prioritizzazione MoSCoW e monitoraggio alle milestone (MS1-MS3); in caso di ritardo accumulato, i requisiti Could-have vengono rimossi dallo sprint senza intaccare i requisiti Must-have.

# 7\. Personale 

Il team di sviluppo è formato da tre studentesse di Ingegneria Informatica. Trattandosi di un progetto universitario svolto durante il semestre accademico (in parallelo alla frequenza delle lezioni e allo studio per altri esami), l'importo di lavoro stimato per ciascun membro è di circa 6-7 ore settimanali, distribuite su un arco di circa 7-8 settimane. L'impegno complessivo previsto è dunque di circa 45-50 ore individuali per ciascuna studentessa, per un carico di lavoro totale stimato di circa 135-150 ore per l'intero gruppo.

Non essendoci ruoli gerarchici fissi, tutti i membri collaborano all'avanzamento generale; tuttavia, l'inizio, la durata, la quantità di persone e le competenze richieste variano in base al momento di sviluppo:

**Fase 1: Analisi dei Requisiti e Modellazione Dati**

* Inizio e Durata: Settimana 1 (durata: 2 settimane).  
* Personale impiegato: 3 persone (team al completo).  
* Competenze: Comprensione del dominio CRI (supportata dal membro volontario), redazione requisiti IEEE 830, modellazione UML con StarUML e progettazione schemi relazionali SQL.

**Fase 2: Sviluppo del Core Backend e Persistenza**

* Inizio e Durata: Settimana 3 (durata: 2 settimane).  
* Personale impiegato: 2 persone concentrate su entità di dominio, layer DAO e regole di validazione dei turni e Service Layer; 1 persona dedicata all'algoritmo di Smart Dispatch e al modulo di simulazione della flotta.  
* Competenze: Programmazione OOP in Java 21, JDBC per SQLite, gestione delle transazioni, applicazione del Pattern Strategy e collaudo con JUnit 5\.

**Fase 3: Sviluppo Interfaccia Grafica JavaFX e Integrazione MVC**

* Inizio e Durata: Settimana 5 (durata: 2 settimane).  
* Personale impiegato: 2 persone dedicate al disegno delle interfacce in Scene Builder e ai fogli di stile CSS; 1 persona dedicata al coordinamento dell'integrazione tra Controller JavaFX e Service Layer.  
* Competenze: Utilizzo di Scene Builder, gestione degli eventi in JavaFX, threading con javafx.concurrent.Task e rispetto dell'architettura MVC.

**Fase 4: Testing di Sistema, Collaudo e Rilascio Finale**

* Inizio e Durata: Settimana 7 (durata: circa 2 settimane, fino alla consegna).  
* Personale impiegato: 3 persone a tempo pieno.  
* Competenze: Esecuzione di scenari di test end-to-end, verifica della portabilità multipiattaforma (Windows, Linux, macOS), correzione dei bug e revisione della documentazione.

**Formazione:** Le competenze di base si acquisiscono con i materiali del corso: lezioni su *programmazione concorrente*, eventi e MVC, esercitazioni su GitHub, JUnit e UML con gli esercizi del corso. Le conoscenze sul dominio CRI vengono trasferite dal membro volontario alle altre due componenti nella Fase 1\. 

# 8\. Metodi e tecniche

In questa sezione vengono definiti i metodi, le tecniche e gli strumenti adottati durante le fasi di analisi, progettazione, sviluppo, testing e gestione della configurazione del software.

## 8.1 Metodologie e tecniche per fase

**Ingegneria dei requisiti:** Raccolta sul campo tramite l'analisi dell'applicativo esistente (Turnapp) con la collaborazione del membro volontario CRI; formalizzazione funzionale mediante redazione di casi d'uso testuali e diagrammi UML degli Use Case. Specifica dei requisiti secondo lo standard IEEE 830 e prioritizzazione con la tecnica (MoSCoW).

**Progettazione architetturale:** Modellazione mediante diagrammi UML 2.0 realizzati su StarUML v5 (per il dettaglio dei singoli diagrammi si veda la [Sezione 4.4](#4.4-standard-di-architettura-e-design)); adozione del pattern architetturale MVC (Model-View-Controller) , del pattern DAO e dei Design Pattern GoF (Singleton, Factory, Strategy)

**Implementazione:** Linguaggio Java 21; persistenza relazionale gestita localmente tramite SQLite con driver JDBC; logging applicativo con framework log4j; interfaccia grafica desktop sviluppata interamente in JavaFX, strutturata con layout dichiarativi .fxml generati tramite Scene Builder e stilizzati con fogli di stile CSS per garantire un aspetto moderno e ordinato.

**Documentazione tecnica:** La relazione di progetto e i verbali di analisi vengono redatti in formato collaborativo su Google Drive, convertiti in PDF/md e storicizzati nel repository all'interno della cartella dedicata /documenti.

## 8.2 Strumenti e controllo della configurazione

**Ambiente di Sviluppo (IDE):** Eclipse, configurato con formattazione e indentazione uniforme a 4 spazi.

**Test, modellazione e tracciabilità:** JUnit 5 per i test unitari; StarUML per i diagrammi; issue e pull request GitHub per la tracciabilità delle modifiche. 

**Gestione delle dipendenze e Build:** Apache Maven per gestire in modo centralizzato e automatico le librerie necessarie. 

**Controllo di versione (SCM):** Utilizzo di Git con repository remoto condiviso su GitHub. 

## 8.3 Ambiente e apparecchiature di prova

Non disponendo di infrastrutture di test dedicate, l'ambiente di prova coincide con i computer di sviluppo personali. I test verranno eseguiti garantendo la compatibilità multipiattaforma sui seguenti sistemi operativi:

* Windows 10 / 11 (64-bit)  
* Ubuntu Linux 22.04 / 24.04 LTS (64-bit)  
* macOS (architettura Apple Silicon/ARM)

Su tutte le postazioni si usa Java 21\. 

## 8.4 Pianificazione, ordine di integrazione e testing

I test vengono integrati fin dalle prime fasi di sviluppo per prevenire l'accumulo di errori:

**Test Unitari (dalla Fase 2):** Sviluppati con il framework JUnit 5 per validare in isolamento la Business Logic (calcolo vincoli delle 8 ore di riposo, controllo scadenze brevetti CRI e patenti).

**Ordine di Integrazione dei Componenti (Fase 2-3):**

* Step 1: Integrazione del database SQLite con le classi del layer DAO;  
* Step 2: Collegamento della Business Logic validata al layer DAO;  
* Step 3: Integrazione del simulatore IoT della flotta e dell'algoritmo di Smart Dispatch al motore logico;  
* Step 4: Collegamento finale dei Controller JavaFX tra viste .fxml e il Service Layer: associazione degli ID dei componenti grafici (bottoni, tabelle, celle del calendario) ai metodi Java che richiamano la Business Logic validata, visualizzando a schermo i risultati (fase 3\)

**Test di Accettazione (Fase 4):** Trattandosi di un progetto privo di committenza esterna sul campo, le prove di accettazione consistono nella simulazione end-to-end eseguita direttamente da noi seguendo scenari verosimili (creazione account, composizione equipaggio ambulanza per turno 118, simulazione assenza e riassegnazione automatica) per accertare l'assenza di eccezioni a runtime prima della consegna finale.

# 9\. Garanzia di qualità 

Per garantire che il software sviluppato rispetti i requisiti di correttezza, affidabilità e manutenibilità richiesti per l'esame, il nostro team adotta una serie di controlli e procedure durante tutto il periodo di sviluppo.  
Trattandosi di un progetto accademico, la qualità viene verificata direttamente da noi studenti senza l'uso di strumenti complessi o esterni, seguendo queste attività principali:

## 9.1 Revisione tra pari del codice (Code Review)

Nessuna parte di codice viene integrata nel programma definitivo senza essere prima controllata:

* Prima di unire una nuova funzionalità al ramo principale (main) su GitHub, lo studente che l'ha scritta deve aprire una Pull Request.  
* Almeno un'altra compagna di gruppo deve esaminare il codice prima del merge, verificando che compili senza errori, che i test associati siano verdi, che siano rispettate le convenzioni Oracle ([Sez. 4.1](#4.1-standard-di-codifica)) con i relativi commenti Javadoc e che non vi sia codice duplicato.

## 9.2 Automazione dei controlli con i Test (JUnit 5\)

La qualità della logica di business viene garantita attraverso l'esecuzione costante dei test:

* I test unitari scritti con JUnit 5 vengono eseguiti ad ogni modifica importante per verificare che le nuove funzioni non rompano quelle già sviluppate in precedenza (evitando errori di regressione).  
* Una funzionalità non viene considerata "completata" finché tutti i test associati alle sue regole (come il calcolo delle ore di riposo o la validità delle patenti CRI) non ottengono esito verde (100% superati).

## 9.3 Qualità e allineamento della documentazione

Per evitare che la documentazione rimanga indietro rispetto al codice:

* La relazione e i diagrammi UML vengono aggiornati passo dopo passo insieme all'implementazione delle classi Java, e non rimandati alla fine del lavoro.  
* Ogni documento condiviso su Google Drive viene riletto da tutti i membri del gruppo per correggere eventuali errori di battitura, verificare la coerenza con i requisiti e accertarsi che i termini tecnici usati siano corretti.

## 9.4 Verifica e validazione finale {#9.4-verifica-e-validazione-finale}

Dato che non abbiamo una committenza reale con cui fare un collaudo formale, la validazione del software avverrà internamente prima della consegna:  
**Verifica**: Controlliamo che l'architettura rispetti il pattern MVC, che non ci siano parti di codice duplicate e che il database SQLite salvi correttamente i dati su file senza perdite.  
**Validazione**: Simuliamo l'utilizzo dell'applicazione eseguendo le schermate JavaFX con i tre attori previsti (Volontario, Capoturno e Amministratore). Inserendo dati verosimili, ci assicuriamo che l'interfaccia risponda in modo fluido, che il calendario visualizzi correttamente i semafori colorati in base alla copertura e che compaiano finestre di dialogo (Alert JavaFX) chiare quando un'azione non è consentita.   
La validazione verifica che i quattro limiti di Turnapp descritti nella prima sezione siano superati: messaggio specifico sulla qualifica mancante, segnalazione dell’assenza con proposta di sostituti, conferma del turno bloccata se il mezzo non è disponibile, servizi separati per tipo. 

## 9.5 Attributi di qualità e verifica

Le caratteristiche di qualità del software di riferimento (ISO 9126\) sono verificate come segue: 

| Caratteristica | Come la verifichiamo |
| ----- | ----- |
| Funzionalità | Test unitari JUnit 5 sulle regole di validazione (qualifiche, scadenze, ore di riposo) |
| Affidabilità | Gestione delle eccezioni, logging con log4j, test di integrazione tra DAO e database SQLite |
| Usabilità | Messaggi di errore specifici, calendario a semafori, prova dei flussi principali con scenari d’uso |
| Efficienza | Interfaccia non bloccata nelle operazioni lunghe ( javafx.concurrent.Task / Service) e tempi di risposta accettabili nei test manuali |
| Manutenibilità | Convenzioni Oracle, Javadoc, architettura MVC, revisione tra pari |
| Portabilità | Java 21 e Maven, prova di avvio da zero sui computer del team |

# 10\. Pacchetti di lavoro (workpackages)

Il lavoro è scomposto in quattordici pacchetti, collegati alle quattro fasi e alle milestone di progetto: MS1 comprende WP1-WP4, MS2 WP5-WP8, MS3 WP9-WP11 e MS4 WP12-WP14. Ogni pacchetto è svolto da tutte e tre le componenti del team; le singole attività sono assegnate una per persona sul Kanban, e ogni Pull Request è revisionata da una persona diversa da chi l’ha scritta.

| ID | Pacchetto di lavoro | Fase | Prodotti | Dipende da |
| ----- | ----- | ----- | ----- | ----- |
| WP1 | Project Plan e approvazione  | 1 | Project Plan, issue di approvazione | — |
| WP2 | Requisiti e casi d’uso | 1 | Specifica IEEE 830, casi d’uso e diagramma casi d’uso | WP1 |
| WP3 | Setup di repository, Maven, Kanban e logging | 1 | Repository, `pom.xml`, board GitHub | — |
| WP4 | Modellazione strutturale  | 1 | Diagrammi delle classi, schema del database, codice generato da StarUML | WP2 |
| WP5 | Modellazione del comportamento  | 2 | Diagrammi di stato, sequenza, attività, comunicazione o temporale | WP2, WP4 |
| WP6 | Architettura e design | 2 | Viste architetturali, design pattern, descrizione del design | WP4 |
| WP7 | Dominio e persistenza  | 2 | Classi di dominio, DAO SQLite | WP4 |
| WP8 | Validazione dei turni e test | 2 | Regole (Strategy), test JUnit 5 | WP5, WP7 |
| WP9 | Login e servizio turni | 3 | Login e ruoli, iscrizione e conferma, controllo del mezzo | WP7, WP8 |
| WP10 | Assenze, sostituti e stato dei mezzi  | 3 | Segnalazione di assenza, Smart Dispatch (base), stato e parametri simulati dei mezzi  | WP8, WP9 |
| WP11 | Interfaccia grafica | 3 | Viste per ruolo, calendario a semafori | WP4 (dati simulati), poi WP9, WP10 |
| WP12 | Test, misure e refactoring | 4 | Casi di test descritti, copertura, metriche, refactoring documentato | WP8 |
| WP13 | Documentazione di gestione, qualità e manutenzione | 4 | Ciclo di vita seguito, uso di GitHub, organizzazione del team, qualità | WP1, WP3 |
| WP14 | Collaudo, presentazione e consegna  | 4 | Scenari di collaudo, presentazione, tag v1.0 | tutti |

Analisi del Percorso Critico:

In accordo con quanto analizzato nella [Sezione 2.4](#2.4-percorso-critico), il percorso critico del progetto è rappresentato dalla sequenza:

**WP2 (Requisiti) → WP4 (Modellazione) →WP7 (Dominio/DAO) → WP8 (Validazione Turni/Test) → WP9 (Servizio Turni e Login) → WP11 (Interfaccia Grafica core) → WP14 (Collaudo e Consegna).**

I pacchetti WP6 (Design Pattern avanzati), WP5 (Diagrammi dinamici) e WP10 (Smart Dispatch esteso e parametri simulati dei mezzi) sono sviluppati in parallelo o trattati come requisiti non bloccanti (Should-have/Could-have), garantendo che un eventuale ritardo su di essi non comprometta la consegna del nucleo fondamentale dell'applicazione (Must-have).

# 11\. Risorse 

# Il corretto svolgimento del progetto si avvale di un insieme di risorse hardware, software e di gestione condivise tra le tre componenti del team di sviluppo.

## 11.1 Risorse Hardware:

* **Postazioni di Sviluppo**: 3 Personal Computer portatili con ambienti operativi diversi per testare la portabilità cross-platform del software:  
  * 1 PC con **Windows 10/11** (64-bit)  
  * 1 PC con **Ubuntu Linux** (22.04/24.04 LTS)  
  * 1 Apple **MacBook Pro** (macOS con architettura Apple Silicon/ARM)  
* **Ambiente di Esecuzione e Collaudo**: Architettura multi-piattaforma per verificare sul campo la reale indipendenza del software dall'hardware sottostante garantita dalla Java Virtual Machine (JVM).

## 11.2 Risorse Software e Ambiente di Sviluppo

* **Linguaggio e Runtime**: **Java SE Development Kit (JDK 21\)** come ambiente standard di compilazione ed esecuzione.  
* **Ambiente di Sviluppo Integrato (IDE)**: **Eclipse IDE** (ambiente di riferimento obbligatorio per il corso) configurato per la gestione di progetti Maven.  
* **Gestione Dipendenze e Build**: **Apache Maven** (`pom.xml`) per l'importazione automatica delle librerie esterne, inclusi il driver JDBC e il framework di logging **log4j**.  
* **Database Embedded**: **SQLite 3** gestito tramite driver JDBC per la persistenza locale dei dati con pattern DAO.  
* **Interfaccia Grafica (GUI)**: **JavaFX** integrato con lo strumento **Scene Builder** per la progettazione delle viste dichiarative `.fxml` e relativo styling CSS.  
* **Modellazione UML**: **StarUML v5** (software ufficiale adottato per il corso) per la realizzazione dei diagrammi e la produzione del file di progetto `.mdj`.  
* **Framework di Testing**: **JUnit 5** per lo sviluppo della suite di test unitari automatizzati focalizzati sulla Business Logic.

## 11.3 Risorse di Coordinamento e Versioning

* **Controllo di Versione (SCM)**: **GitHub** per il versionamento del codice e della documentazione, strutturato nelle cartelle distinte `/codice` e `/documenti`.  
* **Project Management**: **GitHub Projects (vista Board)** per il tracciamento delle task secondo la prioritizzazione MoSCoW e **GitHub Issues** per il confronto formale con i docenti.  
* **Collaborazione e Documentazione**: **Google Drive** per la stesura iniziale dei testi e successiva conversione in formato **Markdown (`.md`)** da storicizzare su repository.  
* **Comunicazione del Team**: canale **WhatsApp** per il coordinamento rapido quotidiano.

## 11.4 Materiali di riferimento

Il team si avvale dei materiali del corso: il libro di testo (H. van Vliet , *Software Engineering: Principles and Practice)* , le slide e gli esercizi di UML, le esercitazioni su Maven, GitHub e JUnit.

# 12\. Budget e programma 

La pianificazione temporale e la gestione delle risorse per lo sviluppo di **Smart Rescue System** si articolano su un arco temporale di **7-8 settimane**, ripartendo il carico di lavoro in modo bilanciato tra i membri del gruppo.

## 12.1 Budget Temporale e Finanziario**:**

**Costo Finanziario**: **€0** . Il progetto fa uso esclusivo di tecnologie open-source, librerie gratuite e strumenti a licenza didattica (Java 21 JDK, Eclipse IDE, Apache Maven, SQLite, StarUML v5, Scene Builder, GitHub).

**Impegno Temporale Complessivo: \~45-50 ore individuali** per ciascuna sviluppatrice (per un totale complessivo di circa **135-150 ore per il team**), calibrate sull'arco delle 7-8 settimane di sviluppo previste dalla roadmap.

## 12.2 Programmazione delle Fasi di Sviluppo (Roadmap)

* **Fase 1 – Analisi dei Requisiti e Modellazione Dati (Settimane 1-2):** Analisi delle procedure CRI, stesura del Project Plan (Doc 1\) e del documento dei Requisiti IEEE 830 (Doc 3), setup del repository GitHub, definizione dei diagrammi dei casi d'uso e dello schema SQLite.  
* **Fase 2 – Sviluppo del Core Backend e Validazione JUnit (Settimane 3-4):** Modellazione del dominio, implementazione del layer DAO, codifica delle regole di validazione con Pattern Strategy, algoritmo di Smart Dispatch e Service Layer e superamento della suite di test JUnit 5 (copertura \> 80%).  
* **Fase 3 – Sviluppo Interfaccia Grafica JavaFX e Integrazione MVC (Settimane 5-6):** Progettazione grafica dei file .fxml con Scene Builder, sviluppo dei Controller JavaFX, esecuzione asincrona delle query pesanti e assemblaggio della dashboard e del calendario a semafori.  
* **Fase 4 – Testing di Sistema, Collaudo e Release (Settimane 7-8):** Feature freeze, esecuzione manuale degli scenari d'uso tipici della sede CRI su tutti i sistemi operativi del team (Windows, Linux, macOS), rifinitura della documentazione e consegna finale del repository (tag v1.0).

## 12.3 Pietre Miliari e Scadenze di Consegna {#12.3-pietre-miliari-e-scadenze-di-consegna}

Per monitorare i progressi rispetto agli obiettivi tecnici definiti nella [Sezione 2.3](#2.3-pietre-miliari-e-criteri-di-verifica), sono fissate le seguenti scadenze temporali lungo le 7-8 settimane di lavoro:

* **E1 – Consegna Project Plan (Mese \-1 rispetto all'esame):** Consegna formale del presente documento tramite apertura di una Issue su GitHub indirizzata ai docenti (**@garganti / @silviabonfanti**).  
* **MS1 – Requisiti e database pronti (Fine Settimana 2):** Completamento dell'analisi, approvazione dei diagrammi UML iniziali e inizializzazione dello schema del database SQLite ([Sez. 2.3](#2.3-pietre-miliari-e-criteri-di-verifica)).  
* **MS2 – Logica di controllo funzionante (Fine Settimana 4):** Conclusione del motore di validazione turni e superamento con esito 100% positivo della suite di unit test JUnit 5 sulla Business Logic ([Sez. 2.3](#2.3-pietre-miliari-e-criteri-di-verifica)).  
* **MS3 – Integrazione dei moduli (Fine Settimana 6):** Completamento del collegamento tra viste JavaFX, database e moduli specialistici; blocco dello sviluppo di nuove funzioni per stabilizzare l'applicazione.  
* **MS4 – Consegna del progetto (Settimana 8):** Verifica manuale dell'applicazione completa e priva di bug su tutti i sistemi operativi del team (Windows, Linux, macOS).  
* **E2 – Consegna Finale Repository (Giorno \-5 rispetto alla prova orale):** Consolidamento del branch main, verifica del file README.md e invio definitivo del link al repository GitHub ai docenti.

# 13\. Gestione dei cambiamenti

Durante il ciclo di vita del software, eventuali richieste di modifica ai requisiti, all'architettura o alle funzionalità vengono gestite tramite una procedura rigorosa di **Change Management**, evitando l'ampliamento incontrollato dello scopo (*scope creep*) e garantendo la coerenza del sistema.

## 13.1 Flusso Operativo di Modifica

1. **Segnalazione della Richiesta**: Qualsiasi proposta di modifica viene aperta sul repository GitHub come **Issue** dedicata, contrassegnata dalle etichette (*labels*) `change-request` o `enhancement`.  
2. **Analisi di Impatto (Impact Analysis)**: Il team analizza collegialmente l'impatto della modifica proposta su tre elementi chiave:  
   * **Architettura MVC e UML**: Valutazione delle ripercussioni sulla struttura delle classi, sul DB SQLite o sui diagrammi StarUML v5.  
   * **Testing e Qualità**: Verifica delle modifiche necessarie sulla suite di test **JUnit 5** per mantenere la copertura del codice \>80%.   
   * **Pianificazione**: Compatibilità con la roadmap delle 8 settimane e con la prioritizzazione MoSCoW (impedendo che funzionalità opzionali rallentino i moduli *Must-have*).  
3. **Approvazione e Assegnazione**:  
   * Se la modifica impatta i requisiti concordati, la scelta viene notificata e validata con i docenti sulla relativa Issue GitHub.  
   * A seguito dell'approvazione, la task viene inserita nella Kanban Board e assegnata a un membro del team.  
   * Le decisioni si prendono insieme. Una modifica viene accettata solo se non ritarda i requisiti Must-have; in caso contrario viene rimandata o rifiutata.  
4. **Implementazione e Merge**: Lo sviluppo avviene in un *Feature Branch* separato e viene integrato nel ramo `main` solo tramite **Pull Request**, previa revisione e superamento dei test automatizzati.

## 13.2 Tracciabilità e Stabilità della Documentazione

* **Conservazione della Numerazione**: In conformità con le linee guida della sezione 4.2, l'eventuale rimozione o modifica di un paragrafo **non altererà la numerazione originale delle sezioni successive**, evitando l'interruzione dei riferimenti incrociati presenti nel testo.  
* **Sincronizzazione degli Artefatti**: Ogni modifica applicata al codice comporta il contestuale aggiornamento del file di progetto StarUML v5 (`.mdj`) e dei file Markdown (`.md`) presenti all'interno della cartella `/documenti`.

# 14\. Consegna

## 14.1 Contenuto della consegna 

Il team consegna il repository GitHub di progetto, che contiene: 

* nella cartella /codice, il progetto Maven importabile direttamente in Eclipse (con .project e .classpath), il codice sorgente, schema.sql e i test JUnit 5;  
* nella cartella /documenti, il Project Plan, il documento dei requisiti, il diagramma UML (file .mdj e immagini) e la documentazione finale  
* nella radice, un [README.md](http://README.md) con le istruzioni per importare e avviare il progetto 

## 14.2 Rilascio 

La versione consegnata è quella del ramo main, contrassegnata con un tag di versione (es. v1.0). Prima della consegna il repository viene clonato da zero e avviato su ciascun computer del team. 

## 14.3 Criteri di accettazione

La consegna è accettabile quando: 

* il progetto si importa e si compila senza errori  
* tutti i test JUnit 5 sono verdi e la copertura della Business Logic raggiunge l’obiettivo descritto nella [sezione 4.5](#4.5-procedure-di-testing) (Copertura \> 80%)  
* gli scenari di collaudo ([sezione 9.4](#9.4-verifica-e-validazione-finale)) dimostrano che i quattro limiti di Turnapp descritti nella [sezione 1](#1.1-background-e-motivazione) sono superati;  
* la documentazione è completa e allineata al codice

## 14.4 Scadenze

Le scadenze di consegna sono quelle indicate nella [sezione 12.3](#12.3-pietre-miliari-e-scadenze-di-consegna)

