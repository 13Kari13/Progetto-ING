package it.unibg.smartrescue.database;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gestore della connessione SQLite (Pattern Singleton).
 * Supporta la modalità WAL e l'inizializzazione automatica delle tabelle all'avvio.
 */
public class DatabaseManager {

    private static final Logger logger = LogManager.getLogger(DatabaseManager.class);
    private static final String DB_URL = "jdbc:sqlite:smart_rescue.db";
    private static Connection connection = null;

    private DatabaseManager() {
        // Costruttore privato per Pattern Singleton, da fare dopo
    }

    /**
     * Restituisce la connessione attiva al database.
     * Al primo avvio attiva la modalità WAL e crea le tabelle da schema.sql.
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            logger.info("Connessione al database SQLite stabilita (embedded): {}", DB_URL);

            // 1. Attiva la modalità WAL per concorrenza (Rischio R1 Project Plan)
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA journal_mode=WAL;");
            }

            // 2. Crea in automatico le tabelle se il file è nuovo
            initDatabase(connection);
        }
        return connection;
    }

    /**
     * Esegue lo script schema.sql per creare le tabelle all'avvio.
     */
    private static void initDatabase(Connection conn) {
        try (InputStream is = DatabaseManager.class.getResourceAsStream("/schema.sql")) {
            if (is == null) {
                logger.warn("File schema.sql non trovato in resources, tabelle non create.");
                return;
            }

            String script = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            String[] comandi = script.split(";");

            try (Statement stmt = conn.createStatement()) {
                for (String comando : comandi) {
                    String sql = comando.trim();
                    if (!sql.isEmpty()) {
                        stmt.execute(sql);
                    }
                }
                logger.info("Inizializzazione completata: tabelle create/verificate con successo all'avvio.");
            }
        } catch (Exception e) {
            logger.error("Errore durante l'esecuzione di schema.sql: {}", e.getMessage());
        }
    }
}