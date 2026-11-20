# **Verbali delle riunioni del team**

**Smart Rescue System**

In questo documento sono raccolte le decisioni prese e le attività svolte durante gli incontri del gruppo di lavoro, formato da Karima Frimane, Sukaina Derouich e Marwa Laklaa.

## **Riepilogo degli incontri**

| N. | Data | Argomento principale |
| :---- | :---- | :---- |
| 1 | 9 ottobre 2026 | Avvio del progetto e definizione dell’ambito |
| 2 | 23 ottobre 2026 | Revisione delle bozze e pausa per gli esami |
| 3 | 7 novembre 2026 | Ripresa dopo gli esami, setup GitHub e consegna E1 |
| 4 | 18 novembre 2026 | Verifica ambiente Maven, chiusura WP1, WP2, WP3 e avvio WP4 |

## **Riunione 1: Avvio del progetto e definizione dell’ambito**

| Data | 9 ottobre 2026, dalle 14:30 alle 16:00 |
| :---- | :---- |
| **Luogo** | Aula studio universitaria (Karima e Sukaina in presenza, Marwa da remoto) |
| **Presenti** | Karima Frimane, Sukaina Derouich, Marwa Laklaa |

### **Punti discussi**

> * **Scelta del dominio:** è stato definito il progetto Smart Rescue System, un software gestionale per la Croce Rossa Italiana (CRI). L’idea parte dall’esperienza diretta di Marwa come volontaria.  
> * **Problemi dell’app attuale:** analisi dei limiti di Turnapp, l’applicativo usato oggi. Blocca le iscrizioni ai turni senza dare una motivazione, non gestisce in automatico i cambi turno, non controlla se i mezzi sono disponibili e non divide le attività per categoria.  
> * **Attori del sistema:** individuati i tre ruoli principali, cioè Volontario, Capoturno (che è una specializzazione del Volontario) e Amministratore.  
> * **Metodologia di sviluppo:** scelto un modello iterativo e incrementale, con Kanban board su GitHub e priorità dei requisiti decise con la tecnica MoSCoW. Lo abbiamo preferito all’approccio a cascata.  
> * **Tecnologie:** Java 21, IDE Eclipse, database SQLite embedded (con pattern DAO), interfaccia grafica JavaFX e JUnit 5 per i test.

### **Assegnazione compiti**

> * **Karima Frimane e Sukaina Derouich:** iniziare la bozza del Project Plan (struttura WBS, matrice dei rischi e stima dell’effort, circa 45-50 ore a testa).  
> * **Marwa Laklaa:** raccogliere i requisiti funzionali in modo dettagliato e scrivere il glossario del dominio CRI (patenti, qualifiche operative, riposo minimo di 8 ore).

## **Riunione 2: Revisione delle bozze e pausa per gli esami**

| Data | 23 ottobre 2026, dalle 15:00 alle 16:15 |
| :---- | :---- |
| **Luogo** | Aule studio universitarie (tutti in presenza) |
| **Presenti** | Karima Frimane, Sukaina Derouich, Marwa Laklaa |

### **Punti discussi**

> * **Avanzamento dei lavori:** abbiamo riguardato insieme la struttura del Project Plan e la prima versione dei requisiti funzionali, tra cui Smart Dispatch (gestione delle assenze) e il calendario con gli indicatori a semaforo.  
> * **Strumenti di modellazione:** confermato l’uso di StarUML v5 per tutti i diagrammi UML richiesti dal corso.  
> * **Scadenze universitarie:** valutato il carico di studio per gli esami parziali e deciso di fermare per un po’ le attività del progetto.

### **Decisioni prese**

> * Pausa dal 25 ottobre al 5 novembre 2026 per preparare gli esami.  
> * Alla ripresa, la priorità è aggiornare il repository ufficiale su GitHub, aprire le issue e fare la prima versione del Diagramma dei Casi d’Uso.

## **Riunione 3: Ripresa dopo gli esami, setup GitHub e consegna E1**

| Data | 7 novembre 2026, dalle 14:00 alle 15:30 |
| :---- | :---- |
| **Luogo** | Aula studio universitaria (in presenza) |
| **Presenti** | Karima Frimane, Sukaina Derouich, Marwa Laklaa |

### **Punti discussi**

> * **Project Plan:** revisione finale e approvazione della versione 1.1, con il monte ore bilanciato, la divisione in 4 iterazioni da due settimane e le milestone allineate.  
> * **Repository e modellazione:** create su GitHub le 14 issue dei Work Package (da WP1 a WP14). Marwa ha caricato la prima versione del Diagramma dei Casi d’Uso (commit b257c5a).  
> * **Consegna Milestone E1:** aperta la issue \#15 per consegnare il Project Plan ai docenti (@garganti e @silviabonfanti).

### **Decisioni e assegnazione compiti**

> * **Workflow Git:** non si fanno commit direttamente sul ramo main. Si lavora con feature branch e Pull Request, che devono essere riviste (code review) prima del merge.  
> * **Karima Frimane:** configurare il progetto Maven in Eclipse nella cartella /codice (Java 21, SQLite, JUnit 5, Log4j, JaCoCo) e scrivere la classe per la connessione al database usando percorsi relativi.  
> * **Marwa Laklaa e Sukaina Derouich:** finire il documento dei requisiti (standard IEEE 830\) e sistemare il Diagramma dei Casi d’Uso, togliendo gli elementi duplicati e sistemando i margini.

## **Riunione 4: Verifica ambiente Maven, chiusura WP1, WP2, WP3 e avvio WP4**

| Data | 18 novembre 2026, dalle 10:00 alle 11:30 |
| :---- | :---- |
| **Luogo** | Aula studio universitaria (in presenza) |
| **Presenti** | Karima Frimane, Sukaina Derouich, Marwa Laklaa |

### **Punti discussi**

> * **Build e test:** abbiamo eseguito la suite AppTest con JUnit 5 in Eclipse. Il risultato è positivo: 2 test su 2 superati e nessun errore.  
> * **Persistenza:** controllata la classe DatabaseManager. Il database SQLite (smart\_rescue.db) viene creato e collegato in automatico con un percorso relativo, quindi funziona su qualsiasi computer.  
> * **Diagramma dei Casi d’Uso:** controllato l’albero del modello su StarUML e verificato che attori e casi d’uso siano nel posto giusto rispetto ai confini del sistema. Esportati i file definitivi senza watermark (.mdj, .png, .pdf).  
> * **Integrazione del codice:** la Pull Request \#16 è stata rivista, approvata e unita al ramo main.

### **Decisioni prese**

> * Chiuse su GitHub le prime tre issue, relative ai Work Package WP1, WP2 e WP3.  
> * Iniziate le attività del WP4 (Modellazione strutturale), che si trova sul percorso critico.

### **Assegnazione compiti (Sprint WP4)**

> * **Tutto il team:** progettare insieme il Diagramma delle Classi di dominio su StarUML (Volontario, Turno, Posizione, Mezzo, Dotazione, Qualifica, AreaServizio).  
> * **Karima Frimane :** Diagramma dei Package, Componenti & Export /documenti/uml:  
> * **Sukaina Derouich:** Database (schema.sql) \+ Verifica/Relazione:  
> * **Marwa Laklaa:** Rilevazione reale \+ Diagramma delle Classi & Generazione Codice: