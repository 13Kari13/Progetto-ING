-- ============================================================================
-- Smart Rescue System - Schema Database SQLite 3
-- Autore: Derouich Sukaina (WP4 - Modellazione strutturale)
-- ============================================================================

PRAGMA foreign_keys = ON;

-- 1. Utenti del sistema (Volontario, Capoturno, Amministratore)
CREATE TABLE IF NOT EXISTS utente (
    id_utente      INTEGER PRIMARY KEY AUTOINCREMENT,
    nome           TEXT    NOT NULL,
    cognome        TEXT    NOT NULL,
    email          TEXT    NOT NULL UNIQUE,
    password_hash  TEXT    NOT NULL,
    ruolo          TEXT    NOT NULL CHECK (ruolo IN ('VOLONTARIO', 'CAPOTURNO', 'AMMINISTRATORE')),
    telefono       TEXT,
    attivo         INTEGER NOT NULL DEFAULT 1 CHECK (attivo IN (0, 1))
);

-- 2. Catalogo qualifiche, patenti e idoneita CRI
CREATE TABLE IF NOT EXISTS qualifica (
    codice         TEXT    PRIMARY KEY,
    denominazione  TEXT    NOT NULL,
    tipologia      TEXT    NOT NULL,
    permanente     INTEGER NOT NULL DEFAULT 0 CHECK (permanente IN (0, 1)),
    mesi_validita  INTEGER
);

-- 3. Qualifiche possedute dai singoli utenti (data_scadenza NULL se permanente)
CREATE TABLE IF NOT EXISTS utente_qualifica (
    id_utente         INTEGER NOT NULL,
    codice_qualifica  TEXT    NOT NULL,
    data_rilascio     TEXT    NOT NULL,
    data_scadenza     TEXT,
    PRIMARY KEY (id_utente, codice_qualifica),
    FOREIGN KEY (id_utente)        REFERENCES utente(id_utente)   ON DELETE CASCADE,
    FOREIGN KEY (codice_qualifica) REFERENCES qualifica(codice)   ON DELETE RESTRICT
);

-- 4. Aree di servizio
CREATE TABLE IF NOT EXISTS area_servizio (
    id_area         INTEGER PRIMARY KEY AUTOINCREMENT,
    nome            TEXT    NOT NULL UNIQUE,
    categoria       TEXT    NOT NULL,
    richiede_mezzo  INTEGER NOT NULL DEFAULT 0 CHECK (richiede_mezzo IN (0, 1)),
    descrizione     TEXT
);

-- 5. Flotta mezzi della sede
CREATE TABLE IF NOT EXISTS mezzo (
    id_mezzo               INTEGER PRIMARY KEY AUTOINCREMENT,
    sigla                  TEXT    NOT NULL UNIQUE,
    targa                  TEXT    NOT NULL UNIQUE,
    tipo                   TEXT    NOT NULL,
    stato                  TEXT    NOT NULL DEFAULT 'DISPONIBILE'
                                   CHECK (stato IN ('DISPONIBILE', 'IN_MANUTENZIONE', 'FUORI_SERVIZIO')),
    prossima_manutenzione  TEXT
);

-- 6. Dotazioni di bordo e parametri telemetrici simulati
CREATE TABLE IF NOT EXISTS dotazione (
    id_dotazione          INTEGER PRIMARY KEY AUTOINCREMENT,
    id_mezzo              INTEGER NOT NULL,
    nome                  TEXT    NOT NULL,
    unita_misura          TEXT,
    valore_attuale        REAL,
    soglia_minima         REAL,
    stato                 TEXT    NOT NULL DEFAULT 'OK'
                                  CHECK (stato IN ('OK', 'CARENTE', 'NON_OPERATIVA')),
    ultimo_aggiornamento  TEXT,
    FOREIGN KEY (id_mezzo) REFERENCES mezzo(id_mezzo) ON DELETE CASCADE
);

-- 7. Turni pianificati (5 stati del ciclo di vita)
CREATE TABLE IF NOT EXISTS turno (
    id_turno         INTEGER PRIMARY KEY AUTOINCREMENT,
    id_area          INTEGER NOT NULL,
    id_mezzo         INTEGER,
    data_ora_inizio  TEXT    NOT NULL,
    data_ora_fine    TEXT    NOT NULL,
    stato            TEXT    NOT NULL DEFAULT 'INCOMPLETO'
                             CHECK (stato IN ('INCOMPLETO', 'CONFERMATO', 'OPERATIVO', 'CONCLUSO', 'ANNULLATO')),
    note             TEXT,
    CHECK (data_ora_fine > data_ora_inizio),
    FOREIGN KEY (id_area)  REFERENCES area_servizio(id_area) ON DELETE RESTRICT,
    FOREIGN KEY (id_mezzo) REFERENCES mezzo(id_mezzo)        ON DELETE SET NULL
);

-- 8. Posizioni di equipaggio per ciascun turno (id_volontario NULL se scoperta)
CREATE TABLE IF NOT EXISTS posizione_turno (
    id_posizione       INTEGER PRIMARY KEY AUTOINCREMENT,
    id_turno           INTEGER NOT NULL,
    nome_ruolo         TEXT    NOT NULL,
    obbligatoria       INTEGER NOT NULL DEFAULT 1 CHECK (obbligatoria IN (0, 1)),
    id_volontario      INTEGER,
    data_assegnazione  TEXT,
    FOREIGN KEY (id_turno)      REFERENCES turno(id_turno)     ON DELETE CASCADE,
    FOREIGN KEY (id_volontario) REFERENCES utente(id_utente)   ON DELETE SET NULL,
    UNIQUE (id_turno, id_volontario)
);

-- 9. Requisiti di qualifica per ciascuna posizione (AND tra gruppi diversi, OR nello stesso gruppo)
CREATE TABLE IF NOT EXISTS posizione_requisito (
    id_requisito        INTEGER PRIMARY KEY AUTOINCREMENT,
    id_posizione        INTEGER NOT NULL,
    codice_qualifica    TEXT    NOT NULL,
    gruppo_alternativa  INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY (id_posizione)     REFERENCES posizione_turno(id_posizione) ON DELETE CASCADE,
    FOREIGN KEY (codice_qualifica) REFERENCES qualifica(codice)             ON DELETE RESTRICT,
    UNIQUE (id_posizione, codice_qualifica)
);

-- 10. Notifiche e comunicazioni interne
CREATE TABLE IF NOT EXISTS notifica (
    id_notifica         INTEGER PRIMARY KEY AUTOINCREMENT,
    id_destinatario     INTEGER NOT NULL,
    id_turno            INTEGER,
    tipo                TEXT    NOT NULL,
    messaggio           TEXT    NOT NULL,
    letta               INTEGER NOT NULL DEFAULT 0 CHECK (letta IN (0, 1)),
    data_ora_creazione  TEXT    NOT NULL DEFAULT (datetime('now', 'localtime')),
    FOREIGN KEY (id_destinatario) REFERENCES utente(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (id_turno)        REFERENCES turno(id_turno)   ON DELETE SET NULL
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

INSERT OR IGNORE INTO qualifica (codice, denominazione, tipologia, permanente, mesi_validita) VALUES
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

INSERT OR IGNORE INTO area_servizio (nome, categoria, richiede_mezzo, descrizione) VALUES
    ('Servizio H12',                              'EMERGENZA',                1, 'Turno emergenza diurno 08:00-13:00 e 13:00-20:00'),
    ('Servizio H24',                              'EMERGENZA',                1, 'Turno emergenza continuativo H24 su 4 fasce orarie'),
    ('Bimbambulanza',                             'EVENTI_E_AMBULATORI',      1, 'Incontri educativi nelle scuole con ambulanza'),
    ('Eventi',                                    'EVENTI_E_AMBULATORI',      1, 'Assistenza sanitaria a eventi con fasce orarie variabili'),
    ('Ambulatorio Galgario',                      'EVENTI_E_AMBULATORI',      0, 'Ambulatorio serale lunedi-venerdi 20:30-22:00'),
    ('Fast Track Ambulatorio OdS',                'EVENTI_E_AMBULATORI',      0, 'Test rapidi, accoglienza e counseling lunedi 17:00-21:00'),
    ('Screening Colesterolo e Glicemia Lazzaretto','EVENTI_E_AMBULATORI',     0, 'Screening mattutini occasionali 07:30-11:00'),
    ('Sportello Sociale',                         'SOCIALE',                  0, 'Colloqui di ascolto e orientamento socio-economico in sede'),
    ('Sportello Sociale Itinerante',              'SOCIALE',                  1, 'Sportello mobile sul territorio con veicolo CRI'),
    ('Sabato Insieme',                            'SOCIALE',                  1, 'Attivita ricreative e trasporto utenti con mezzi CRI'),
    ('Distribuzione Beni',                        'SOCIALE',                  0, 'Preparazione e consegna pacchi alimentari e vestiario'),
    ('Collette',                                  'SOCIALE',                  0, 'Raccolta beni presso punti vendita'),
    ('Centralino',                                'LOGISTICA_E_CENTRALINO',   0, 'Centralino sede e sala radio H24 senza scoperture'),
    ('Sistemazione Magazzino e Kit',              'LOGISTICA_E_CENTRALINO',   0, 'Inventario, controllo scadenze e assemblaggio kit'),
    ('Mensa Stazione',                            'MENSE',                    0, 'Distribuzione pasti in stazione (pranzo domenica e sera lun-gio)'),
    ('Mensa Patronato',                           'MENSE',                    0, 'Servizio ai tavoli lunedi-sabato 18:00-19:30');