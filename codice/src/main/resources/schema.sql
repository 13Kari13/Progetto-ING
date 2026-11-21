-- ============================================================================
-- Smart Rescue System - Schema Database SQLite 3
-- ============================================================================

PRAGMA foreign_keys = ON;

-- 1. Utenti del sistema (Volontario, Capoturno, Amministratore)
CREATE TABLE IF NOT EXISTS utente (
    idUtente      INTEGER PRIMARY KEY AUTOINCREMENT,
    nome          TEXT    NOT NULL,
    cognome       TEXT    NOT NULL,
    email         TEXT    NOT NULL UNIQUE,
    passwordHash  TEXT    NOT NULL,
    telefono      TEXT,
    ruolo         TEXT    NOT NULL CHECK (ruolo IN ('VOLONTARIO', 'CAPOTURNO', 'AMMINISTRATORE')),
    attivo        BOOLEAN NOT NULL DEFAULT 1 CHECK (attivo IN (0, 1))
);

-- 2. Catalogo qualifiche, patenti e idoneita CRI
CREATE TABLE IF NOT EXISTS qualifica (
    codice         TEXT    PRIMARY KEY,
    denominazione  TEXT    NOT NULL,
    tipologia      TEXT    NOT NULL,
    permanente     BOOLEAN NOT NULL DEFAULT 0 CHECK (permanente IN (0, 1)),
    mesiValidita   INTEGER
);

-- 3. Qualifiche possedute dai singoli utenti (dataScadenza NULL se permanente)
CREATE TABLE IF NOT EXISTS utente_qualifica (
    idUtente         INTEGER NOT NULL,
    codiceQualifica  TEXT    NOT NULL,
    dataRilascio     TEXT    NOT NULL,
    dataScadenza     TEXT,
    PRIMARY KEY (idUtente, codiceQualifica),
    FOREIGN KEY (idUtente)        REFERENCES utente(idUtente)   ON DELETE CASCADE,
    FOREIGN KEY (codiceQualifica) REFERENCES qualifica(codice)  ON DELETE RESTRICT
);

-- 4. Aree di servizio
CREATE TABLE IF NOT EXISTS area_servizio (
    idArea          INTEGER PRIMARY KEY AUTOINCREMENT,
    nome            TEXT    NOT NULL UNIQUE,
    categoria       TEXT    NOT NULL,
    descrizione     TEXT,
    richiedeMezzo   BOOLEAN NOT NULL DEFAULT 0 CHECK (richiedeMezzo IN (0, 1)),
    isContinuativo  BOOLEAN NOT NULL DEFAULT 0 CHECK (isContinuativo IN (0, 1))
);

-- 5. Flotta mezzi della sede
CREATE TABLE IF NOT EXISTS mezzo (
    idMezzo               INTEGER PRIMARY KEY AUTOINCREMENT,
    sigla                 TEXT    NOT NULL UNIQUE,
    targa                 TEXT    NOT NULL UNIQUE,
    tipo                  TEXT    NOT NULL,
    stato                 TEXT    NOT NULL DEFAULT 'DISPONIBILE'
                                  CHECK (stato IN ('DISPONIBILE', 'IN_MANUTENZIONE', 'FUORI_SERVIZIO')),
    prossimaManutenzione  TEXT
);

-- 6. Dotazioni di bordo e parametri telemetrici simulati
CREATE TABLE IF NOT EXISTS dotazione (
    idDotazione          INTEGER PRIMARY KEY AUTOINCREMENT,
    idMezzo              INTEGER NOT NULL,
    nome                 TEXT    NOT NULL,
    unitaMisura          TEXT,
    valoreAttuale        REAL,
    sogliaMinima         REAL,
    stato                TEXT    NOT NULL DEFAULT 'OK'
                                 CHECK (stato IN ('OK', 'CARENTE', 'NON_OPERATIVA')),
    ultimoAggiornamento  TEXT,
    FOREIGN KEY (idMezzo) REFERENCES mezzo(idMezzo) ON DELETE CASCADE
);

-- 7. Turni pianificati (allineati alla classe Turno)
CREATE TABLE IF NOT EXISTS turno (
    idTurno       INTEGER PRIMARY KEY AUTOINCREMENT,
    idArea        INTEGER NOT NULL,
    idMezzo       INTEGER,
    data          TEXT    NOT NULL,
    oraInizio     TEXT    NOT NULL,
    oraFine       TEXT    NOT NULL,
    fasciaOraria  TEXT,
    stato         TEXT    NOT NULL DEFAULT 'INCOMPLETO'
                          CHECK (stato IN ('INCOMPLETO', 'CONFERMATO', 'OPERATIVO', 'CONCLUSO', 'ANNULLATO')),
    note          TEXT,
    FOREIGN KEY (idArea)  REFERENCES area_servizio(idArea) ON DELETE RESTRICT,
    FOREIGN KEY (idMezzo) REFERENCES mezzo(idMezzo)        ON DELETE SET NULL
);

-- 8. Posizioni di equipaggio per ciascun turno (idVolontario NULL se scoperta)
CREATE TABLE IF NOT EXISTS posizione_turno (
    idPosizione          INTEGER PRIMARY KEY AUTOINCREMENT,
    idTurno              INTEGER NOT NULL,
    nomeRuolo            TEXT    NOT NULL,
    obbligatoria         BOOLEAN NOT NULL DEFAULT 1 CHECK (obbligatoria IN (0, 1)),
    descrizioneAttivita  TEXT,
    idVolontario         INTEGER,
    dataAssegnazione     TEXT,
    FOREIGN KEY (idTurno)      REFERENCES turno(idTurno)   ON DELETE CASCADE,
    FOREIGN KEY (idVolontario) REFERENCES utente(idUtente) ON DELETE SET NULL,
    UNIQUE (idTurno, idVolontario)
);

-- 9. Requisiti di qualifica per ciascuna posizione (AND tra gruppi diversi, OR nello stesso gruppo)
CREATE TABLE IF NOT EXISTS posizione_requisito (
    idRequisito        INTEGER PRIMARY KEY AUTOINCREMENT,
    idPosizione        INTEGER NOT NULL,
    codiceQualifica    TEXT    NOT NULL,
    gruppoAlternativa  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY (idPosizione)     REFERENCES posizione_turno(idPosizione) ON DELETE CASCADE,
    FOREIGN KEY (codiceQualifica) REFERENCES qualifica(codice)            ON DELETE RESTRICT,
    UNIQUE (idPosizione, codiceQualifica)
);

-- 10. Notifiche e comunicazioni interne
CREATE TABLE IF NOT EXISTS notifica (
    idNotifica        INTEGER PRIMARY KEY AUTOINCREMENT,
    idDestinatario    INTEGER NOT NULL,
    idTurno           INTEGER,
    tipo              TEXT    NOT NULL,
    messaggio         TEXT    NOT NULL,
    letta             BOOLEAN NOT NULL DEFAULT 0 CHECK (letta IN (0, 1)),
    dataOraCreazione  TEXT    NOT NULL DEFAULT (datetime('now', 'localtime')),
    FOREIGN KEY (idDestinatario) REFERENCES utente(idUtente) ON DELETE CASCADE,
    FOREIGN KEY (idTurno)        REFERENCES turno(idTurno)   ON DELETE SET NULL
);

-- 11. Soglie e parametri configurabili di sistema (DOM.7)
CREATE TABLE IF NOT EXISTS configurazione (
    chiave       TEXT PRIMARY KEY,
    valore       TEXT NOT NULL,
    descrizione  TEXT
);

-- ============================================================================
-- Dati iniziali di configurazione, qualifiche e aree CRI (idempotenti)
-- ============================================================================

INSERT OR IGNORE INTO configurazione (chiave, valore, descrizione) VALUES
    ('ORE_RIPOSO_MINIME', '8', 'Ore minime di riposo tra fine turno e inizio turno successivo (DOM.1)'),
    ('ORE_ANNULLAMENTO_RAVVICINATO', '48', 'Soglia ore per attivazione automatica Smart Dispatch'),
    ('GIORNI_AVVISO_SCADENZA', '30', 'Giorni di anticipo per segnalare una qualifica in scadenza'),
    ('SOGLIA_MINIMA_OSSIGENO', '50.0', 'Livello minimo per considerare operativa la bombola ossigeno');

INSERT OR IGNORE INTO qualifica (codice, denominazione, tipologia, permanente, mesiValidita) VALUES
    ('VIS_MED',     'Visita Medica / Idoneita Sanitaria',                 'Idoneita Fisica',          0, 12),
    ('CRI',         'Corso di Formazione per Volontari CRI',              'Qualifica Base',           1, NULL),
    ('CRIVT',       'Corso per Volontari Temporanei CRI',                 'Qualifica Base',           1, NULL),
    ('TS',          'Trasporto Sanitario',                                'Qualifica Sanitaria',      1, NULL),
    ('SA',          'Soccorritore Ambulanza',                             'Qualifica Sanitaria',      1, NULL),
    ('TSSA',        'Trasporto Sanitario e Soccorso in Ambulanza',        'Qualifica Sanitaria',      1, NULL),
    ('OP_AREU',     'Operativita AREU / Retraining SA',                   'Abilitazione Operativa',   0, 12),
    ('BLSD',        'Basic Life Support & Defibrillation',                'Abilitazione Sanitaria',   0, 24),
    ('FULL_D',      'Full-D (DAE Adulto/Pediatrico)',                     'Abilitazione Sanitaria',   0, 24),
    ('MED',         'Medico CRI',                                         'Titolo Sanitario',         1, NULL),
    ('INF',         'Infermiere',                                         'Titolo Sanitario',         1, NULL),
    ('OSIV',        'Infermiera Volontaria CRI',                          'Titolo Sanitario',         1, NULL),
    ('OS',          'Operatore Sociale CRI',                              'Qualifica Area Sociale',   1, NULL),
    ('OPSD',        'Operatore CRI Persone Senza Dimora',                 'Qualifica Area Sociale',   1, NULL),
    ('OPSP',        'Operatore CRI Sportello Sociale',                    'Qualifica Area Sociale',   1, NULL),
    ('COSP',        'Coordinatore Sportello Sociale',                     'Qualifica Area Sociale',   1, NULL),
    ('MTT',         'Corso Campagna Meet Test and Treat',                 'Qualifica Prevenzione',    1, NULL),
    ('OSD',         'Operatore Sociale Dipendenze',                       'Qualifica Area Sociale',   1, NULL),
    ('APD',         'Attivita Persone con Disabilita',                    'Qualifica Area Sociale',   1, NULL),
    ('ACSI',        'Attivita Contrasto Solitudini Involontarie',         'Qualifica Area Sociale',   1, NULL),
    ('PAT_CIV',     'Patente Civile (Cat. B / C)',                        'Titolo di Guida',          0, 120),
    ('PAT_CRI_ORD', 'Patente di Servizio CRI - Guida Ordinaria',          'Abilitazione Guida CRI',   0, 60),
    ('PAT_CRI_EM',  'Patente di Servizio CRI - Guida in Emergenza (TI)',  'Abilitazione Guida CRI',   0, 60);

INSERT OR IGNORE INTO area_servizio (nome, categoria, descrizione, richiedeMezzo, isContinuativo) VALUES
    ('Servizio H12',                              'EMERGENZA',                'Turno emergenza diurno 08:00-13:00 e 13:00-20:00', 1, 1),
    ('Servizio H24',                              'EMERGENZA',                'Turno emergenza continuativo H24 su 4 fasce orarie', 1, 1),
    ('Bimbambulanza',                             'EVENTI_E_AMBULATORI',      'Incontri educativi nelle scuole con ambulanza', 1, 0),
    ('Eventi',                                    'EVENTI_E_AMBULATORI',      'Assistenza sanitaria a eventi con fasce orarie variabili', 1, 0),
    ('Ambulatorio Galgario',                      'EVENTI_E_AMBULATORI',      'Ambulatorio serale lunedi-venerdi 20:30-22:00', 0, 0),
    ('Fast Track Ambulatorio OdS',                'EVENTI_E_AMBULATORI',      'Test rapidi, accoglienza e counseling lunedi 17:00-21:00', 0, 0),
    ('Screening Colesterolo e Glicemia Lazzaretto','EVENTI_E_AMBULATORI',     'Screening mattutini occasionali 07:30-11:00', 0, 0),
    ('Sportello Sociale',                         'SOCIALE',                  'Colloqui di ascolto e orientamento socio-economico in sede', 0, 0),
    ('Sportello Sociale Itinerante',              'SOCIALE',                  'Sportello mobile sul territorio con veicolo CRI', 1, 0),
    ('Sabato Insieme',                            'SOCIALE',                  'Attivita ricreative e trasporto utenti con mezzi CRI', 1, 0),
    ('Distribuzione Beni',                        'SOCIALE',                  'Preparazione e consegna pacchi alimentari e vestiario', 0, 0),
    ('Collette',                                  'SOCIALE',                  'Raccolta beni presso punti vendita', 0, 0),
    ('Centralino',                                'LOGISTICA_E_CENTRALINO',   'Centralino sede e sala radio H24 senza scoperture', 0, 1),
    ('Sistemazione Magazzino e Kit',              'LOGISTICA_E_CENTRALINO',   'Inventario, controllo scadenze e assemblaggio kit', 0, 0),
    ('Mensa Stazione',                            'MENSE',                    'Distribuzione pasti in stazione (pranzo domenica e sera lun-gio)', 0, 0),
    ('Mensa Patronato',                           'MENSE',                    'Servizio ai tavoli lunedi-sabato 18:00-19:30', 0, 0);