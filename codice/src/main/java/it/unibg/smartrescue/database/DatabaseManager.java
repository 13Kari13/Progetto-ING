package it.unibg.smartrescue.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gestore della connessione al database SQLite locale (Pattern Singleton).
 * Rispetta il vincolo di portabilità: nessun percorso assoluto e creazione embedded.
 */
public class DatabaseManager {

    private static final Logger logger = LogManager.getLogger(DatabaseManager.class);
    
    // 1. PERCORSO RELATIVO: nessun path assoluto (C:\ o /home/...)
    private static final String DB_URL = "jdbc:sqlite:smart_rescue.db";
    
    private static Connection connection = null;

    private DatabaseManager() {
        // Costruttore privato per Pattern Singleton
    }

    /**
     * Restituisce la connessione attiva al database.
     * Se il database non esiste ancora, SQLite lo crea automaticamente all'avvio.
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(DB_URL);
                logger.info("Connessione al database SQLite stabilita (embedded): {}", DB_URL);
                
                // Abilita la modalità WAL per gestire accessi concorrenti (Rischio R1 del Project Plan)
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("PRAGMA journal_mode=WAL;");
                }
            } catch (SQLException e) {
                logger.error("Impossibile connettersi al database SQLite: {}", e.getMessage());
                throw e;
            }
        }
        return connection;
    }
}