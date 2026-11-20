# **Smart Rescue System – Documento dei requisiti**

## **1\. Introduzione**

Questo documento specifica i requisiti di Smart Rescue System, l'applicazione per la gestione di turni, qualifiche e mezzi della Croce Rossa Italiana (CRI) sviluppata per il corso di Ingegneria del Software (A.A. 2026/2027).

|  |  |
| :---- | :---- |
| Titolo | Smart Rescue System – Documento dei requisiti |
| Autori | Derouich Sukaina, Frimane Karima, Laklaa Marwa |
| Data | 18 novembre 2026 |
| Versione | 1.0 |

### **1.1 Scopo e destinatari**

Il documento descrive cosa deve fare il sistema, non come è realizzato. Serve al team come riferimento per progettazione e test e ai docenti (prof. Angelo Gargantini, dott.ssa Silvia Bonfanti) per valutare la completezza dei requisiti. Segue lo schema dello standard IEEE 830 e, per la scrittura dei requisiti, il formato del documento di esempio del corso (*Mechanical Lung Ventilator*).

### **1.2 Ambito del prodotto**

Smart Rescue System coordina le attività di una sede CRI in un'unica applicazione desktop per tre ruoli: Volontario, Capoturno e Amministratore.

**Il sistema deve occuparsi di:**

* turni dei volontari per area di servizio, con controllo automatico di qualifiche, patenti, scadenze e riposo minimo;  
* composizione delle squadre e conferma dei turni da parte del Capoturno;  
* ricerca dei sostituti idonei quando un volontario si cancella;  
* stato e dotazioni dei mezzi, con simulazione dei parametri di bordo (es. ossigeno);  
* calendario con indicazione visiva della copertura (verde, giallo, rosso).

**Fuori ambito:** applicazione mobile, invio di SMS reali, telemetria reale dei mezzi (i dati sono simulati), qualsiasi gestione di farmaci (i volontari non li somministrano in ambulanza).

### **1.3 Glossario**

| Termine | Definizione |
| :---- | :---- |
| Volontario | Operatore CRI che consulta i turni e si iscrive a quelli compatibili con le proprie qualifiche. |
| Capoturno | Referente di servizio: supervisiona la squadra, conferma i turni e gestisce le assenze. Ha tutte le funzioni del Volontario. |
| Amministratore | Amministratore di Comitato: pianifica i turni, gestisce volontari, aree e mezzi, monitora la flotta. |
| Area di servizio | Attività della sede in cui si svolgono i turni (es. H12, Mensa), raggruppata in una categoria. |
| Categoria | Raggruppamento delle aree: Emergenza e trasporto, Sanitaria, Sociale, Eventi. |
| Turno | Servizio con data, orario e area, composto da posizioni da coprire. |
| Posizione | Ruolo da ricoprire in un turno (es. capo equipaggio, autista, soccorritore), con i requisiti richiesti. |
| Equipaggio | Insieme dei volontari assegnati alle posizioni di un turno. |
| Qualifica | Brevetto, attestato o patente posseduti da un volontario, con data di rilascio e scadenza (es. BLSD, patente di servizio). |
| Mezzo | Veicolo associato a un turno (ambulanza, auto di servizio, pulmino); obbligatorio solo per le aree di emergenza e trasporto. |
| Dotazione | Apparecchiatura o materiale a bordo di un mezzo (es. bombola di ossigeno, defibrillatore). |
| Copertura | Grado di riempimento delle posizioni di un turno o di una giornata. |
| Riposo minimo | Intervallo di almeno 8 ore tra la fine di un turno e l'inizio del successivo per lo stesso volontario. |
| Annullamento ravvicinato | Annullamento di un'iscrizione quando il turno inizia entro la soglia stabilita (es. 48 ore). |
| Smart Dispatch | Ricerca automatica di volontari sostitutivi idonei per una posizione scoperta. |
| MoSCoW | Classificazione delle priorità: Must, Should, Could, Won't. |

### **1.4 Riferimenti**

1. Smart Rescue System – Project Plan (Documento 1), versione corrente.  
2. Requisiti per il progetto di Ingegneria del Software, A.A. 2026/2027, v1.0.1.  
3. S. Bonfanti, A. Gargantini, *Mechanical Lung Ventilator – Software Requirements Specification for ABZ 2024 case study*, v1.5, 13 dicembre 2023\.  
4. IEEE Std 830-1998, *Recommended Practice for Software Requirements Specifications*.

### **1.5 Convenzioni**

* Ogni requisito ha un identificativo (**FUN.n**, **GUI.n**, **NFR.n, DOM.n**) e la forma «Il sistema deve…».  
* La priorità è indicata con MoSCoW (M, S, C, W) come nel Project Plan.  
* Se un requisito viene eliminato, i numeri successivi non vengono riassegnati, per non rompere i riferimenti incrociati (Project Plan, sez. 4.2).  
* I casi d'uso sono indicati con **UC-n** e collegati ai requisiti nella sezione 9\.

## **2\. Raccolta dei requisiti**

I requisiti nascono dall'analisi di Turnapp, la piattaforma con cui la CRI gestisce oggi i turni dei volontari, svolta da una componente del gruppo che è volontaria attiva della CRI. Non esiste una committenza esterna: la validazione avviene all'interno del team (sezione 2.4).

### **2.1 Tecniche usate**

* **Analisi dell'applicativo esistente:** uso di Turnapp da volontario, con verifica diretta di cosa mostra, cosa blocca e cosa non consente.  
* **Esperienza sul campo:** procedure operative reali della sede (aree di servizio, composizione degli equipaggi, regole sul riposo e sulle qualifiche).  
* **Scenari tipici di sede:** iscrizione a un turno, assenza improvvisa, mezzo non disponibile, usati per derivare i casi d'uso (sezione 4).

### **2.2 Limiti di Turnapp e requisiti che li risolvono**

| Problema osservato in Turnapp | Conseguenza | Requisiti |
| :---- | :---- | :---- |
| Quando blocca un'iscrizione non dice cosa manca (brevetto assente o scaduto). | Il volontario non sa come sbloccarsi. | FUN.11, FUN.12 |
| Non permette di cancellarsi da soli: serve un superiore. In caso di assenza la casella resta vuota, senza ricerca di sostituti. | Turni scoperti e lavoro manuale per il Capoturno. | FUN.13, FUN.14, FUN.24, FUN.25 |
| Traccia solo le persone: il turno di ambulanza non mostra il mezzo (gestito da un altro software). | Si possono confermare turni con ambulanza guasta, in manutenzione o già occupata. | FUN.30, FUN.31, FUN.32 |
| Mostra in un unico elenco attività molto diverse (emergenza, mensa, Bimbambulanza, sportello sociale). | Difficile trovare il turno giusto. | FUN.6, FUN.7 |

### **2.3 Funzioni di Turnapp da mantenere**

* I ruoli richiesti sono visibili in ogni turno (FUN.9).  
* Il profilo raccoglie anagrafica, qualifiche e scadenze (FUN.3).  
* Il volontario riceve comunicazioni dalla sede (FUN.5).

### **2.4 Limiti del metodo**

Non ci sono interviste a utenti terzi né collaudo sul campo. I nomi esatti delle posizioni dell'equipaggio e le dotazioni di bordo (ad esempio la presenza del defibrillatore) vanno ancora verificati su Turnapp e con la sede; fino ad allora sono indicati come esempi.

## **3\. Descrizione generale**

Smart Rescue System è un'applicazione desktop a sé stante, con database locale, che sostituisce l'uso separato di Turnapp e del software dei mezzi: tiene insieme turni, qualifiche e stato della flotta e impedisce a monte le formazioni non a norma.

### **3.1 Utenti e ruoli**

| Ruolo | Cosa fa nel sistema | Note |
| :---- | :---- | :---- |
| Volontario | Consulta aree, calendario e turni; si iscrive e si cancella; consulta profilo, qualifiche e comunicazioni. | Può iscriversi solo a posizioni compatibili con le sue qualifiche. |
| Capoturno | Tutto ciò che fa il Volontario, più: controlla e modifica l'equipaggio, conferma, annulla e chiude i turni, gestisce le assenze e assegna i sostituti, verifica le dotazioni del mezzo. | Eredita le funzioni del Volontario. |
| Amministratore | Pianifica i turni; gestisce volontari, qualifiche, aree di servizio e mezzi; monitora la flotta. | Non eredita le funzioni del Volontario. |

### **3.2 Funzioni principali**

1. **Turni e iscrizioni:** calendario per area, iscrizione con controllo automatico dei requisiti, annullamento autonomo.  
2. **Gestione dell'equipaggio:** composizione, conferma e chiusura del turno da parte del Capoturno.  
3. **Sostituzioni:** ricerca dei volontari idonei quando una posizione resta scoperta.  
4. **Mezzi e dotazioni:** stato dei mezzi, disponibilità al momento della conferma, parametri di bordo simulati.  
5. **Anagrafica e qualifiche:** profili con scadenze e avvisi.

### **3.3 Vincoli**

* **Piattaforma:** applicazione Java 21 gestita con Maven e sviluppata in Eclipse; deve funzionare su Windows 10/11, Ubuntu 22.04/24.04 e macOS.  
* **Persistenza:** database SQLite locale, accessibile tramite pattern DAO; tracciamento delle operazioni con log4j.  
* **Architettura:** tre livelli secondo il pattern MVC.  
* **Interfaccia:** applicazione desktop realizzata in JavaFX (con layout dichiarativi FXML e Scene Builder), in conformità all'architettura MVC stabilita nel Project Plan. I requisiti grafici specifici (schermate, dashboard con calendario a semafori, navigazione e messaggi di errore) sono dettagliati formalmente nella Sezione 6\.  
* **Tempi e risorse:** progetto di gruppo da tre persone, senza costi, con le scadenze dei milestone del Project Plan.

### **3.4 Assunzioni e dipendenze**

* I dati di volontari, mezzi e turni sono inseriti dall'Amministratore o caricati come dati di prova: non c'è integrazione con Turnapp.  
* La telemetria dei mezzi è simulata: non esistono sensori reali.  
* Le regole di dominio (riposo minimo, qualifiche richieste dalle posizioni) sono quelle descritte nella sezione 7.2 e verranno rifinite con la sede CRI.

## **4\. Casi d'uso**

Il sistema offre 32 casi d'uso per i tre ruoli. Il diagramma UML (StarUML, file documenti/UML/Diagramma dei casi d'uso nel repository) mostra attori, include ed extend; qui sotto sono elencati tutti i casi e descritti nel dettaglio quelli con logica propria.

### **4.1 Elenco dei casi d'uso**

| ID | Caso d'uso | Attore | Relazioni | Pr. |
| :---- | :---- | :---- | :---- | :---- |
| UC-1 | Autenticarsi | Volontario, Amministratore (Capoturno come Volontario) |  | M |
| UC-2 | Consultare le aree di servizio | Volontario |  | M |
| UC-3 | Visualizzare il calendario dei turni | Volontario | include UC-2 | M |
| UC-4 | Consultare il dettaglio di un turno | Volontario |  | M |
| UC-5 | Iscriversi a un turno | Volontario | include UC-13 | M |
| UC-6 | Annullare la propria iscrizione | Volontario | esteso da UC-14 | M |
| UC-7 | Consultare i propri turni e lo storico | Volontario |  | M |
| UC-8 | Consultare il proprio profilo | Volontario | include UC-9 | M |
| UC-9 | Consultare qualifiche e scadenze | Volontario |  | M |
| UC-10 | Consultare le comunicazioni | Volontario |  | S |
| UC-11 | Consultare lo stato del turno | Capoturno |  | M |
| UC-12 | Modificare la composizione dell'equipaggio | Capoturno | include UC-13 | M |
| UC-13 | Verificare i requisiti per la posizione | interno | esteso da UC-15 | M |
| UC-14 | Cercare sostituti idonei | interno | estende UC-6; include UC-13 | S |
| UC-15 | Mostrare il motivo del blocco | interno | estende UC-13 | M |
| UC-16 | Confermare il turno | Capoturno | include UC-17, UC-18; esteso da UC-19 | M |
| UC-17 | Verificare la composizione dell'equipaggio | interno |  | M |
| UC-18 | Verificare la disponibilità del mezzo | interno |  | S |
| UC-19 | Mostrare le carenze del turno | interno | estende UC-16 | M |
| UC-20 | Assegnare un sostituto | Capoturno | include UC-14, UC-21 | S |
| UC-21 | Inviare notifica | interno |  | S |
| UC-22 | Annullare il turno | Capoturno | include UC-21 | M |
| UC-23 | Chiudere il turno | Capoturno |  | M |
| UC-24 | Verificare le dotazioni del mezzo | Capoturno | esteso da UC-25 | S |
| UC-25 | Segnalare dotazione carente | interno | estende UC-24 | S |
| UC-26 | Pianificare i turni | Amministratore | include UC-18 | M |
| UC-27 | Gestire volontari e qualifiche | Amministratore |  | M |
| UC-28 | Gestire lo stato dei mezzi | Amministratore | esteso da UC-29 | S |
| UC-29 | Avvisare i capoturno interessati | interno | estende UC-28 | S |
| UC-30 | Gestire le aree di servizio | Amministratore |  | S |
| UC-31 | Gestire le dotazioni del mezzo | Amministratore |  | S |
| UC-32 | Monitorare lo stato della flotta | Amministratore | include UC-24 | S |

I casi marcati «interno» non sono avviati direttamente da un attore: sono richiamati dagli altri casi d'uso.

### **4.2 Schede dei casi d'uso principali**

#### **UC-5 Iscriversi a un turno**

**Attore:** Volontario · **Priorità:** Must · **Requisiti:** FUN.10, FUN.11, FUN.12

**Precondizioni:** il volontario è autenticato; il turno non è annullato né concluso e ha almeno una posizione libera.

**Scenario principale**

1. Il volontario seleziona un turno dal calendario.  
2. Il sistema mostra le posizioni del turno con i requisiti richiesti.  
3. Il volontario sceglie una posizione libera e chiede di iscriversi.  
4. Il sistema verifica i requisiti (UC-13).  
5. Il sistema registra l'iscrizione e aggiorna copertura e stato del turno.  
6. Il sistema conferma l'iscrizione al volontario.

**Alternative**

* 4a. Un requisito non è soddisfatto (qualifica mancante o scaduta, riposo minimo non rispettato): il sistema non registra l'iscrizione e mostra il motivo del blocco (UC-15). Il caso termina.  
* 5a. La posizione è stata occupata nel frattempo: il sistema lo comunica e torna al passo 2\.

**Postcondizioni:** il volontario è assegnato alla posizione e la copertura del turno è aggiornata.

#### **UC-6 Annullare la propria iscrizione**

**Attore:** Volontario · **Priorità:** Must · **Requisiti:** FUN.13, FUN.14

**Precondizioni:** il volontario è autenticato ed è iscritto a un turno non ancora iniziato.

**Scenario principale**

1. Il volontario apre i propri turni e sceglie quello da annullare.  
2. Il volontario chiede l'annullamento; il sistema chiede conferma.  
3. Il volontario conferma.  
4. Il sistema libera la posizione e aggiorna copertura e stato del turno.  
5. Il sistema conferma l'annullamento al volontario.

**Alternative**

* 4a. Annullamento ravvicinato (il turno inizia entro la soglia stabilita, es. 48 ore): il sistema avvia la ricerca di sostituti idonei (UC-14) e li propone al Capoturno.  
* 2a. Il volontario rinuncia: nessuna modifica.

**Postcondizioni:** la posizione è libera; nel caso ravvicinato è disponibile l'elenco dei sostituti.

#### **UC-16 Confermare il turno**

**Attore:** Capoturno · **Priorità:** Must · **Requisiti:** FUN.19, FUN.20, FUN.21, FUN.31

**Precondizioni:** il Capoturno è autenticato; il turno non è annullato, operativo o concluso.

**Scenario principale**

1. Il Capoturno apre lo stato del turno (UC-11).  
2. Il Capoturno chiede la conferma.  
3. Il sistema verifica la composizione dell'equipaggio (UC-17).  
4. Il sistema verifica la disponibilità del mezzo, se il turno lo prevede (UC-18).  
5. Il sistema porta il turno nello stato Confermato e comunica la conferma agli iscritti.

**Alternative**

* 3a / 4a. L'equipaggio non è a norma o il mezzo non è disponibile: la conferma è bloccata e il sistema mostra le carenze (UC-19). Il turno resta Incompleto.

**Postcondizioni:** il turno è Confermato, oppure resta invariato con l'elenco delle carenze.

#### **UC-20 Assegnare un sostituto**

**Attore:** Capoturno · **Priorità:** Should · **Requisiti:** FUN.24, FUN.25, FUN.26

**Precondizioni:** il Capoturno è autenticato; una posizione di un turno non ancora iniziato è scoperta.

**Scenario principale**

1. Il Capoturno seleziona la posizione scoperta.  
2. Il sistema cerca i sostituti idonei (UC-14) e li elenca.  
3. Il Capoturno sceglie un volontario.  
4. Il sistema assegna il volontario alla posizione e aggiorna copertura e stato.  
5. Il sistema notifica il volontario assegnato (UC-21).

**Alternative**

* 2a. Nessun sostituto idoneo: il sistema lo comunica; il Capoturno può lasciare la posizione scoperta o modificare l'equipaggio (UC-12).

**Postcondizioni:** la posizione è coperta e il volontario è stato avvisato.

#### **UC-12 Modificare la composizione dell'equipaggio**

**Attore:** Capoturno · **Priorità:** Must · **Requisiti:** FUN.18, FUN.11, FUN.12

**Precondizioni:** il Capoturno è autenticato; il turno non è iniziato.

**Scenario principale**

1. Il Capoturno apre lo stato del turno.  
2. Il Capoturno aggiunge, rimuove o sostituisce un volontario in una posizione.  
3. Il sistema verifica i requisiti per la posizione (UC-13).  
4. Il sistema aggiorna l'equipaggio, la copertura e lo stato del turno.

**Alternative**

* 3a. Un requisito non è soddisfatto: il sistema rifiuta la modifica e mostra il motivo (UC-15).  
* 4a. La modifica rende l'equipaggio non conforme a un turno Confermato: il turno torna Incompleto.

**Postcondizioni:** l'equipaggio rispetta i requisiti oppure resta invariato.

#### **UC-22 Annullare il turno**

**Attore:** Capoturno · **Priorità:** Must · **Requisiti:** FUN.22, FUN.26

**Precondizioni:** il Capoturno è autenticato; il turno non è iniziato.

**Scenario principale**

1. Il Capoturno seleziona il turno e chiede l'annullamento.  
2. Il sistema chiede conferma.  
3. Il Capoturno conferma.  
4. Il sistema porta il turno nello stato Annullato e libera il mezzo associato.  
5. Il sistema invia una notifica a tutti i volontari iscritti (UC-21).

**Alternative**

* 3a. Il Capoturno rinuncia: nessuna modifica.

**Postcondizioni:** il turno è Annullato e gli iscritti sono stati avvisati.

#### **UC-24 Verificare le dotazioni del mezzo**

**Attore:** Capoturno (e UC-32 Monitorare lo stato della flotta) · **Priorità:** Should · **Requisiti:** FUN.34

**Precondizioni:** l'utente è autenticato; il mezzo ha dotazioni registrate.

**Scenario principale**

1. L'utente seleziona il mezzo del turno.  
2. Il sistema mostra le dotazioni e i parametri simulati (es. livello di ossigeno, stato del defibrillatore).  
3. Il sistema confronta i valori con le soglie stabilite.

**Alternative**

* 3a. Un valore è anomalo (es. ossigeno sotto soglia, dotazione non operativa): il sistema segnala la dotazione carente (UC-25).

**Postcondizioni:** l'utente conosce lo stato delle dotazioni e le eventuali carenze.

#### **UC-26 Pianificare i turni**

**Attore:** Amministratore · **Priorità:** Must · **Requisiti:** FUN.27, FUN.31

**Precondizioni:** l'Amministratore è autenticato; l'area di servizio esiste.

**Scenario principale**

1. L'Amministratore sceglie l'area, la data e l'orario del turno.  
2. L'Amministratore definisce le posizioni e i requisiti di ciascuna.  
3. Se l'area è di emergenza o trasporto, associa un mezzo.  
4. Il sistema verifica la disponibilità del mezzo nella fascia oraria (UC-18).  
5. Il sistema crea il turno in stato Incompleto e lo pubblica nel calendario.

**Alternative**

* 3a. L'area non richiede un mezzo (es. Mensa): i passi 3 e 4 sono saltati.  
* 4a. Il mezzo non è disponibile: il sistema lo segnala e l'Amministratore sceglie un altro mezzo o rinvia.

**Postcondizioni:** il turno è visibile nel calendario con le posizioni da coprire.

#### **UC-28 Gestire lo stato dei mezzi**

**Attore:** Amministratore · **Priorità:** Should · **Requisiti:** FUN.30, FUN.32

**Precondizioni:** l'Amministratore è autenticato; il mezzo è registrato.

**Scenario principale**

1. L'Amministratore seleziona un mezzo.  
2. L'Amministratore imposta lo stato: disponibile, in manutenzione o fuori servizio.  
3. Il sistema salva il nuovo stato.

**Alternative**

* 3a. Il mezzo passa a «in manutenzione» o «fuori servizio» ed è assegnato a turni futuri: il sistema segnala i turni interessati e avvisa i Capoturno (UC-29).

**Postcondizioni:** lo stato del mezzo è aggiornato; i Capoturno interessati sono avvisati.

### **4.3 Casi d'uso interni**

| ID | Cosa fa |
| :---- | :---- |
| UC-13 | Controlla che il volontario abbia le qualifiche e le patenti richieste, in corso di validità alla data del turno, e rispetti il riposo minimo di 8 ore. |
| UC-14 | Elenca i volontari idonei per una posizione scoperta: requisiti soddisfatti (UC-13), riposo rispettato, nessun altro impegno. |
| UC-15 | Indica il requisito non soddisfatto: qualifica mancante o scaduta (con data) oppure riposo insufficiente. |
| UC-17 | Controlla che le posizioni obbligatorie del turno siano coperte da volontari idonei. |
| UC-18 | Controlla che il mezzo non sia in manutenzione, fuori servizio o già assegnato a un altro turno nella stessa fascia. |
| UC-19 | Elenca le carenze che impediscono la conferma (posizioni scoperte, requisiti, mezzo). |
| UC-21 | Crea la comunicazione per gli utenti interessati (assegnazione, annullamento, avviso). |
| UC-25 | Segnala una dotazione carente al Capoturno e all'Amministratore. |
| UC-29 | Avvisa i Capoturno dei turni a cui è assegnato un mezzo non più disponibile. |

## **5\. Requisiti funzionali**

Il sistema ha 36 requisiti funzionali: 20 Must, 15 Should e 1 Could. Ogni requisito è collegato ai casi d'uso nella sezione 9\.

### **5.1 Accesso e profilo**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.1 | Il sistema deve richiedere l'autenticazione dell'utente prima di consentire l'accesso a qualunque funzione. | I dati su volontari e qualifiche sono riservati e le funzioni dipendono dal ruolo. | M |
| FUN.2 | Il sistema deve riconoscere il ruolo dell'utente (Volontario, Capoturno, Amministratore) e rendere disponibili solo le funzioni di quel ruolo; il Capoturno deve avere anche tutte le funzioni del Volontario. | Ogni ruolo ha responsabilità diverse; il Capoturno è un volontario con compiti di supervisione. | M |
| FUN.3 | Il sistema deve mostrare al volontario il proprio profilo con anagrafica, qualifiche (con data di rilascio e di scadenza) e patenti. | Il volontario deve sapere cosa gli consente di iscriversi ai turni. | M |
| FUN.4 | Il sistema deve indicare per ogni qualifica se è valida, in scadenza o scaduta; la soglia di «in scadenza» deve essere configurabile. | Evita blocchi dovuti a scadenze che il volontario non aveva notato. | S |
| FUN.5 | Il sistema deve mostrare al volontario le comunicazioni a lui destinate (es. turno confermato o annullato, ricerca di un sostituto, qualifica in scadenza). | Il volontario viene a sapere dei cambiamenti senza dover controllare ogni turno. | S |

### **5.2 Aree di servizio e calendario**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.6 | Il sistema deve mostrare le aree di servizio raggruppate nelle categorie Emergenza e trasporto, Sanitaria, Sociale ed Eventi. | Turnapp mescola attività molto diverse in un unico elenco. | M |
| FUN.7 | Il sistema deve mostrare, per l'area scelta, il calendario dei turni con selezione del giorno. | Il volontario trova i turni dell'area che gli interessa. | M |
| FUN.8 | Il sistema deve indicare per ogni giorno del calendario la copertura dei turni con tre livelli visivi: verde (coperto), giallo (parzialmente coperto), rosso (scoperto). Le soglie esatte sono definite in fase di progettazione. | Si vede a colpo d'occhio dove servono volontari. | S |
| FUN.9 | Il sistema deve mostrare il dettaglio di un turno: area, data, orario, posizioni con i requisiti richiesti e i volontari assegnati, mezzo (se previsto) e stato. | In Turnapp i ruoli sono visibili in ogni turno; si aggiungono mezzo e stato. | M |

### **5.3 Iscrizioni del volontario**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.10 | Il sistema deve permettere al volontario di iscriversi a una posizione libera di un turno. | Funzione base della gestione dei turni. | M |
| FUN.11 | Prima di registrare un'iscrizione o un'assegnazione, il sistema deve verificare che il volontario possieda tutte le qualifiche e le patenti richieste dalla posizione, in corso di validità alla data del turno, e che rispetti il riposo minimo di 8 ore tra turni consecutivi. | Rende impossibili le formazioni non a norma. | M |
| FUN.12 | Se un requisito non è soddisfatto, il sistema deve impedire l'iscrizione e indicare quale requisito manca: qualifica assente o scaduta (con la data di scadenza) oppure riposo insufficiente. | Turnapp blocca l'iscrizione senza dire cosa manca. | M |
| FUN.13 | Il sistema deve permettere al volontario di annullare la propria iscrizione a un turno non ancora iniziato. | In Turnapp il volontario deve chiedere a un superiore di essere rimosso. | M |
| FUN.14 | Se l'annullamento avviene entro la soglia stabilita prima dell'inizio del turno (es. 48 ore), il sistema deve avviare la ricerca di sostituti idonei e proporli al Capoturno. | Una cancellazione vicina al turno lascia poco tempo per trovare un sostituto a mano. | S |
| FUN.15 | Il sistema deve mostrare al volontario i propri turni futuri. | Il volontario controlla i suoi impegni. | M |
| FUN.16 | Il sistema deve mostrare al volontario lo storico dei turni svolti. | Il volontario ricostruisce la propria attività. | S |

### **5.4 Gestione dei turni da parte del Capoturno**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.17 | Il sistema deve mostrare al Capoturno lo stato del turno: equipaggio, copertura, mezzo, dotazioni e stato del turno. | Il Capoturno supervisiona la composizione delle squadre. | M |
| FUN.18 | Il sistema deve permettere al Capoturno di aggiungere, rimuovere e sostituire i volontari di un turno non ancora iniziato; ogni inserimento è soggetto ai controlli di FUN.11. | Il Capoturno corregge l'equipaggio senza poter creare formazioni non a norma. | M |
| FUN.19 | Il sistema deve verificare che l'equipaggio copra le posizioni obbligatorie del turno con volontari idonei. | Una formazione non a norma non può essere confermata. | M |
| FUN.20 | Il sistema deve permettere al Capoturno di confermare un turno solo se l'equipaggio è conforme (FUN.19) e, quando il turno prevede un mezzo, il mezzo è disponibile (FUN.31). | La conferma garantisce che il turno possa partire. | M |
| FUN.21 | Se la conferma è bloccata, il sistema deve mostrare le carenze del turno: posizioni scoperte, requisiti non soddisfatti, mezzo non disponibile. | Il Capoturno sa cosa correggere. | M |
| FUN.22 | Il sistema deve permettere al Capoturno di annullare un turno non ancora iniziato. | Il turno può saltare per cause esterne. | M |
| FUN.23 | Il sistema deve permettere al Capoturno di chiudere un turno al termine del servizio. | Il turno entra nello storico dei volontari. | M |
| FUN.24 | Per una posizione scoperta il sistema deve cercare i volontari idonei (requisiti soddisfatti, riposo rispettato, nessun altro impegno nella stessa fascia oraria) e proporli (Smart Dispatch). | In Turnapp la casella rimane vuota e il sostituto si cerca a mano. | S |
| FUN.25 | Il sistema deve permettere al Capoturno di assegnare a una posizione scoperta uno dei sostituti proposti. | La scelta finale resta al Capoturno. | S |
| FUN.26 | Il sistema deve notificare gli utenti interessati in caso di assegnazione, conferma o annullamento di un turno. | Gli interessati non devono scoprire i cambiamenti da soli. | S |

Nota: finché la gestione dei mezzi (FUN.30, FUN.31) non è realizzata, FUN.20 richiede solo l'equipaggio conforme.

### **5.5 Funzioni dell'Amministratore**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.27 | Il sistema deve permettere all'Amministratore di creare un turno indicando area, data, orario, posizioni, requisiti di ogni posizione e, per le aree di emergenza e trasporto, il mezzo. | Pianificazione generale delle attività della sede. | M |
| FUN.28 | Il sistema deve permettere all'Amministratore di gestire anagrafica, ruolo, qualifiche e scadenze dei volontari. | Le qualifiche sono la base dei controlli di FUN.11. | M |
| FUN.29 | Il sistema deve permettere all'Amministratore di gestire le aree di servizio e la loro categoria. | Le attività della sede cambiano nel tempo. | S |

### **5.6 Mezzi e dotazioni**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.30 | Il sistema deve permettere all'Amministratore di impostare lo stato di un mezzo: disponibile, in manutenzione o fuori servizio. | Turnapp non tiene traccia dei mezzi. | S |
| FUN.31 | Il sistema deve considerare non disponibile un mezzo in manutenzione, fuori servizio o già assegnato a un altro turno nella stessa fascia oraria. | Evita turni confermati con ambulanza guasta o occupata. | S |
| FUN.32 | Quando un mezzo passa in manutenzione o fuori servizio ed è assegnato a turni futuri, il sistema deve segnalare i turni interessati e avvisare i Capoturno. | I Capoturno possono cercare un mezzo alternativo. | S |
| FUN.33 | Il sistema deve permettere all'Amministratore di gestire le dotazioni di ogni mezzo. | Le dotazioni cambiano nel tempo. | S |
| FUN.34 | Il sistema deve simulare i parametri di bordo di un mezzo (es. livello di ossigeno, stato del defibrillatore) e segnalare la dotazione carente quando un valore è anomalo (es. ossigeno sotto soglia). Le soglie sono configurabili. | Il sistema simula la telemetria prevista dal progetto: non ci sono sensori reali. | S |
| FUN.35 | Il sistema deve permettere all'Amministratore di monitorare lo stato di tutta la flotta e delle relative dotazioni. | Vista d'insieme per la gestione della flotta. | S |

### **5.7 Funzioni opzionali**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| FUN.36 | Il sistema potrà esportare i turni dell'utente in formato iCalendar (.ics). | Il volontario può usare i propri turni nel calendario personale, senza integrazione con servizi esterni. | C |

## **6\. Requisiti dell'interfaccia utente**

I requisiti dell'interfaccia descrivono cosa l'utente vede e può fare, senza fissare la tecnologia grafica. Come riferimento esiste una bozza grafica delle schermate con dati di esempio (Home, Calendario turni, Iscrizione non consentita, Profilo, Stato del turno, Gestione mezzi), che non è l'interfaccia definitiva.

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| GUI.1 | Dopo l'autenticazione il sistema deve mostrare la Home con i prossimi turni dell'utente, le comunicazioni e l'accesso alle aree di servizio. | Punto di partenza unico per le azioni più frequenti. | M |
| GUI.2 | Il sistema deve offrire al Volontario una navigazione sempre visibile con tre voci: Home, Calendario turni, Profilo. Per il Capoturno si aggiunge la voce Stato del turno. | Il volontario trova ogni funzione in al massimo due passaggi. | M |
| GUI.3 | Se il volontario ha qualifiche scadute o in scadenza, la Home deve mostrare un avviso con un collegamento al profilo. | Il volontario scopre il problema prima di cercare di iscriversi. | S |
| GUI.4 | Il calendario deve permettere di scegliere l'area e il giorno e mostrare i turni del giorno con posizioni, requisiti, volontari assegnati e mezzo (se previsto). | Il volontario sceglie il turno con tutte le informazioni davanti. | M |
| GUI.5 | Le posizioni libere devono essere distinguibili da quelle occupate e offrire l'azione di iscrizione. | L'azione è disponibile dove ha senso. | M |
| GUI.6 | Quando l'iscrizione è bloccata, il sistema deve mostrare un messaggio che nomina il requisito mancante e, se scaduto, la data di scadenza (FUN.12). | Turnapp blocca senza spiegare: qui il motivo deve essere sempre visibile. | M |
| GUI.7 | Il profilo deve essere organizzato in sezioni: Anagrafica, Qualifiche, Patenti e scadenze. | Le informazioni sono raggruppate come nel profilo di Turnapp, con in più le scadenze. | M |
| GUI.8 | Il profilo potrà avere una sezione Corsi frequentati. | Completa il quadro delle qualifiche in formazione. | C |
| GUI.9 | Lo stato di una qualifica e la copertura di un giorno devono essere indicati sia con il colore sia con un testo o un simbolo. | Il significato non deve dipendere solo dal colore. | S |
| GUI.10 | La schermata Stato del turno deve mostrare equipaggio, mezzo e dotazioni, con le azioni: modificare l'equipaggio, confermare, annullare, chiudere il turno e scegliere un sostituto. | Il Capoturno gestisce tutto da un unico punto. | M |
| GUI.11 | Se la conferma del turno non è consentita, la schermata deve mostrare l'elenco delle carenze (FUN.21) accanto all'azione. | Il Capoturno vede subito cosa correggere. | M |
| GUI.12 | La schermata di gestione dei mezzi deve elencare i mezzi con tipo, stato, dotazioni e prossima manutenzione e permettere di cambiare lo stato. | L'Amministratore controlla la flotta a colpo d'occhio. | S |
| GUI.13 | Prima di un'azione che annulla qualcosa (iscrizione, turno) il sistema deve chiedere conferma all'utente. | Evita annullamenti per errore. | M |
| GUI.14 | Tutti i testi e i messaggi di errore devono essere in italiano e usare la terminologia del glossario. | Gli utenti sono volontari CRI italiani. | M |

## **7\. Requisiti non funzionali e regole di dominio**

### **7.1 Requisiti non funzionali**

| ID | Requisito | Rationale | Pr. |
| :---- | :---- | :---- | :---- |
| NFR.1 | Il sistema deve funzionare con Java 21 su Windows 10/11 (64 bit), Ubuntu 22.04/24.04 LTS (64 bit) e macOS su architettura Apple Silicon. | Il gruppo e i docenti usano ambienti diversi. | M |
| NFR.2 | Il sistema deve salvare i dati nel database locale in modo che sopravvivano alla chiusura dell'applicazione. | Turni e iscrizioni non possono andare persi. | M |
| NFR.3 | Gli accessi concorrenti al database (es. simulazione dei parametri di bordo e iscrizione di un volontario) non devono causare errori all'utente né dati incoerenti. | Rischio R1 del Project Plan. | M |
| NFR.4 | L'interfaccia non deve bloccarsi durante la lettura o la scrittura dei dati. | Rischio R2 del Project Plan. | S |
| NFR.5 | Le regole di dominio della sezione 7.2 devono essere verificate da test automatici (JUnit 5); la copertura della logica di business deve essere almeno l'80%. | Rischio R3: sono le regole con più probabilità di errore. | M |
| NFR.6 | Il sistema deve separare interfaccia, logica di business e accesso ai dati (architettura a tre livelli MVC, accesso ai dati tramite DAO). | Le regole di dominio devono poter essere provate senza l'interfaccia. | M |
| NFR.7 | Il sistema deve registrare con log4j le operazioni rilevanti: iscrizioni, annullamenti, conferme, cambi di stato dei mezzi. | Permette di ricostruire cosa è successo in caso di dubbio. | S |
| NFR.8 | Il sistema non deve memorizzare le password in chiaro. | Protezione minima dei dati dei volontari. | S |
| NFR.9 | Il codice deve seguire le convenzioni Oracle e documentare con Javadoc classi e metodi pubblici principali. | Standard di codifica del Project Plan. | S |

### **7.2 Regole di dominio**

| ID | Regola |
| :---- | :---- |
| DOM.1 | **Riposo minimo.** Tra la fine di un turno e l'inizio del successivo dello stesso volontario devono passare almeno 8 ore. |
| DOM.2 | **Qualifiche e patenti.** Ogni posizione può richiedere una o più qualifiche (es. BLSD, soccorritore base) o patenti (es. patente di servizio). Una qualifica è valida se non scade prima della data del turno; se non ha scadenza è sempre valida. |
| DOM.3 | **Aree e categorie.** Emergenza e trasporto: Bimbambulanza, H12, H24, Centralino. Sanitaria: Ambulatorio Galgario, Fastrack, Screening. Sociale: Mensa, Sportello sociale, Sabato Insieme, Distribuzione beni, Collette. Eventi: attività speciali. |
| DOM.4 | **Mezzo.** Il mezzo è richiesto solo per le aree di emergenza e trasporto che ne fanno uso (es. H12, H24); le altre categorie non richiedono un mezzo. |
| DOM.5 | **Stati del turno.** Un turno è sempre in uno solo degli stati Incompleto, Confermato, Operativo, Concluso, Annullato (sezione 8). |
| DOM.6 | **Farmaci.** In ambulanza i volontari non somministrano farmaci: il sistema non gestisce farmaci né le loro scadenze. L'unica dotazione sanitaria monitorata è quella dei parametri di bordo (FUN.34). |
| DOM.7 | **Soglie configurabili.** La soglia di annullamento ravvicinato (es. 48 ore), la soglia di «in scadenza» delle qualifiche e le soglie dei parametri di bordo (es. ossigeno) sono valori configurabili, non fissi nel codice. |

## **8\. Ciclo di vita del turno**

Un turno è sempre in uno dei cinque stati del Project Plan e passa a Confermato solo se l'equipaggio è conforme e, quando previsto, il mezzo è disponibile.

![stati del turno · 5 stati, 6 transizioni][image1]

stati del turno · 5 stati, 6 transizioni

Un turno nasce Incompleto con la pianificazione; il Capoturno lo conferma, poi diventa Operativo all'inizio del servizio e Concluso alla chiusura. Può essere annullato finché non è iniziato.

| Da | A | Evento | Chi | Requisiti |
| :---- | :---- | :---- | :---- | :---- |
| (nuovo) | Incompleto | Creazione del turno | Amministratore | FUN.27 |
| Incompleto | Confermato | Conferma con equipaggio conforme e mezzo disponibile | Capoturno | FUN.20 |
| Confermato | Incompleto | Un'iscrizione viene annullata, una modifica rende l'equipaggio non conforme o il mezzo non è più disponibile | Sistema | FUN.13, FUN.18, FUN.32 |
| Confermato | Operativo | Inizio dell'orario del turno | Sistema | FUN.17 |
| Operativo | Concluso | Chiusura al termine del servizio | Capoturno | FUN.23 |
| Incompleto, Confermato | Annullato | Annullamento del turno | Capoturno | FUN.22 |

**Punti aperti:** cosa succede a un turno ancora Incompleto all'ora di inizio e se un turno Operativo può essere annullato non sono definiti nel Project Plan e vanno decisi con la sede.

## **9\. Sviluppi futuri, tracciabilità e punti aperti**

### **9.1 Sviluppi futuri**

Questi elementi non fanno parte della prima versione; sono tenuti separati dai requisiti obbligatori.

| Elemento | Priorità | Note |
| :---- | :---- | :---- |
| Esportazione dei turni in formato iCalendar (.ics), FUN.36 | Could | Realizzabile senza servizi esterni. |
| Notifiche visive avanzate | Could | Oltre alle comunicazioni di FUN.5. |
| Integrazione diretta con un calendario online tramite API | Won't | Richiede account e autorizzazioni esterne. |
| Applicazione mobile | Won't | Fuori dallo scopo del corso. |
| Invio di SMS reali | Won't | Le notifiche sono solo nell'applicazione. |
| Telemetria reale dei mezzi | Won't | I parametri sono simulati. |
| Integrazione con Turnapp e con il software dei mezzi | Won't | Non esistono interfacce disponibili. |

### **9.2 Tracciabilità tra casi d'uso e requisiti**

Ogni requisito funzionale è coperto da almeno un caso d'uso, tranne FUN.36 (opzionale).

| Caso d'uso | Requisiti |
| :---- | :---- |
| UC-1 Autenticarsi | FUN.1, FUN.2 |
| UC-2 Consultare le aree di servizio | FUN.6 |
| UC-3 Visualizzare il calendario dei turni | FUN.7, FUN.8 |
| UC-4 Consultare il dettaglio di un turno | FUN.9 |
| UC-5 Iscriversi a un turno | FUN.10, FUN.11, FUN.12 |
| UC-6 Annullare la propria iscrizione | FUN.13, FUN.14 |
| UC-7 Consultare i propri turni e lo storico | FUN.15, FUN.16 |
| UC-8 Consultare il proprio profilo | FUN.3 |
| UC-9 Consultare qualifiche e scadenze | FUN.3, FUN.4 |
| UC-10 Consultare le comunicazioni | FUN.5 |
| UC-11 Consultare lo stato del turno | FUN.17 |
| UC-12 Modificare la composizione dell'equipaggio | FUN.18 |
| UC-13 Verificare i requisiti per la posizione | FUN.11 |
| UC-14 Cercare sostituti idonei | FUN.14, FUN.24 |
| UC-15 Mostrare il motivo del blocco | FUN.12 |
| UC-16 Confermare il turno | FUN.20 |
| UC-17 Verificare la composizione dell'equipaggio | FUN.19 |
| UC-18 Verificare la disponibilità del mezzo | FUN.31 |
| UC-19 Mostrare le carenze del turno | FUN.21 |
| UC-20 Assegnare un sostituto | FUN.25 |
| UC-21 Inviare notifica | FUN.26 |
| UC-22 Annullare il turno | FUN.22 |
| UC-23 Chiudere il turno | FUN.23 |
| UC-24 Verificare le dotazioni del mezzo | FUN.34 |
| UC-25 Segnalare dotazione carente | FUN.34 |
| UC-26 Pianificare i turni | FUN.27 |
| UC-27 Gestire volontari e qualifiche | FUN.28 |
| UC-28 Gestire lo stato dei mezzi | FUN.30 |
| UC-29 Avvisare i capoturno interessati | FUN.32 |
| UC-30 Gestire le aree di servizio | FUN.29 |
| UC-31 Gestire le dotazioni del mezzo | FUN.33 |
| UC-32 Monitorare lo stato della flotta | FUN.35 |

### **9.3 Punti da confermare**

* **Nomi delle posizioni** dell'equipaggio (es. capo equipaggio, autista, soccorritore): da verificare su Turnapp.  
* **Dotazioni di bordo** (es. presenza del defibrillatore): da verificare con la sede.  
* **Soglie:** annullamento ravvicinato (48 ore), «in scadenza», ossigeno sotto soglia, livelli verde/giallo/rosso del calendario.  
* **Stati del turno:** cosa succede a un turno Incompleto all'ora di inizio; se un turno Operativo può essere annullato.  
* **Corsi frequentati** nel profilo (GUI.8): da decidere se includerli.

[image1]: <data:image/png;base64,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>