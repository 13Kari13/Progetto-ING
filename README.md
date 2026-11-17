<div align="center">

# 🚑 Smart Rescue System

**Sistema integrato per la gestione operativa, logistica e telemetrica di una sede della Croce Rossa Italiana (CRI)**

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=flat-square&logo=apachemaven)
![JavaFX](https://img.shields.io/badge/GUI-JavaFX%2021-007396?style=flat-square)
![SQLite](https://img.shields.io/badge/Database-SQLite%203-003B57?style=flat-square&logo=sqlite)
![JUnit5](https://img.shields.io/badge/Testing-JUnit%205-25A162?style=flat-square&logo=junit5)
![Log4j2](https://img.shields.io/badge/Logging-Log4j%202-D22128?style=flat-square)

*Progetto Universitario — Corso di Ingegneria del Software (A.A. 2026/2027)*  
*Università degli Studi di Bergamo*

</div>

---

## Obiettivo del progetto

Il progetto nasce per superare quattro problemi pratici riscontrati nell'uso quotidiano dell'attuale gestionale (*Turnapp*):

| Problema in Turnapp | Soluzione in Smart Rescue System |
| :--- | :--- |
| Blocco dell'iscrizione senza spiegazioni | Indica esattamente quale patente/brevetto manca, è scaduto (con data) o se non sono rispettate le 8 ore di riposo |
| Gestione manuale delle assenze | Ricerca e proposta automatica di sostituti idonei (*Smart Dispatch*) in caso di annullamento ravvicinato |
| Nessun controllo sui mezzi | Blocca la conferma del turno se il mezzo è indisponibile e monitora ossigeno e dotazioni tramite telemetria IoT simulata |
| Servizi diversi mescolati in un unico elenco | Divisione per aree di servizio (118, Mensa, Bimbambulanza, Sportello sociale) e calendario a semafori (verde, giallo, rosso) |

---

## Ruoli utente

- **Volontario (Operatore)**: consulta il calendario per aree, vede i dettagli dei turni, si iscrive alle posizioni compatibili con le proprie qualifiche, controlla le scadenze e segnala eventuali assenze.
- **Capoturno (Referente di Servizio)**: controlla gli equipaggi, conferma i turni verificando requisiti e disponibilità del mezzo, assegna i sostituti e verifica le dotazioni di bordo.
- **Amministratore di Comitato**: pianifica i turni, gestisce volontari, qualifiche, aree di servizio, stato dei veicoli e monitora la flotta.

---

## Tecnologie e architettura

- **Linguaggio**: Java 21 (JDK 21)
- **IDE**: Eclipse IDE (indentazione a 4 spazi)
- **Build e dipendenze**: Apache Maven (`pom.xml`)
- **Interfaccia grafica**: JavaFX 21 + Scene Builder (`.fxml`, `.css`)
- **Architettura e pattern**: MVC, DAO, Singleton, Strategy
- **Database**: SQLite 3 (JDBC, modalità WAL)
- **Logging**: Apache Log4j 2 (`log4j-api`, `log4j-core`)
- **Testing**: JUnit 5
- **Modellazione UML**: StarUML v5 (`.mdj`)

---

## Struttura del repository

```text
Progetto-ING/
├── codice/        # Progetto Java Maven (pom.xml, .project, .classpath, src/)
├── documenti/     # Project Plan, Specifica dei Requisiti (IEEE 830) e diagrammi UML
├── .gitignore     # Esclude target/ mantenendo .project e .classpath per Eclipse
└── README.md      # Istruzioni e panoramica del repository
```

---

## Requisiti per importazione ed esecuzione

- Java JDK 21
- Eclipse IDE (con Maven)
- Git

---

## Come importare e verificare il progetto

### Da Eclipse IDE
1. Clonare il repository:
   ```bash
   git clone https://github.com/13Kari13/Progetto-ING.git
   ```
2. Aprire **Eclipse IDE** (con JDK 21 impostato) e andare su:
   `File → Import... → Maven → Existing Maven Projects → Next`
3. Selezionare la cartella **`Progetto-ING/codice`** (dove si trova `pom.xml`) e cliccare **Finish**.
4. Attendere che Maven scarichi le dipendenze *(se serve: tasto destro sul progetto → `Maven → Update Project... → OK`)*.
5. Verificare la build e i test:
   `Click destro su smart-rescue-system → Run As → Maven test` (in console deve comparire `BUILD SUCCESS`).

### Da Terminale (CLI)
1. Clonare il repository e spostarsi nella cartella del progetto Maven:
   ```bash
   git clone https://github.com/13Kari13/Progetto-ING.git
   cd Progetto-ING/codice
   ```
2. Eseguire build e test:
   ```bash
   mvn clean test
   ```

*Testato dal team su Windows 10/11 (64-bit), Ubuntu Linux (22.04/24.04 LTS) e macOS (Apple Silicon).*

---

## Team e corso

| Studente | GitHub |
| :--- | :--- |
| Derouich Sukaina | `@sukaina-deroo` |
| Frimane Karima | `@13Kari13` |
| Laklaa Marwa | `@mlaklaa` |

- **Corso**: Ingegneria del Software — A.A. 2026/2027 (Università degli Studi di Bergamo)
- **Docenti**: Prof. Angelo Gargantini, Dott.ssa Silvia Bonfanti
