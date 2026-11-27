package it.unibg.smartrescue;

import org.junit.jupiter.api.Test;
import it.unibg.smartrescue.database.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void testSanityCheck() {
        assertTrue(true, "L'ambiente di test JUnit 5 è configurato correttamente.");
    }
    @Test
    void testDatabaseConnectionEmbedded() throws SQLException {
        // Verifica che il database embedded venga creato/connesso con percorso relativo
        Connection conn = DatabaseManager.getConnection();
        
        assertNotNull(conn, "La connessione non deve essere null");
        assertFalse(conn.isClosed(), "Il database embedded deve essere aperto e attivo");
    }
    @Test
    void testConnessioneEInizializzazioneTabelle() throws SQLException {
        // Chiede la connessione: crea il file, attiva WAL e lancia schema.sql
        Connection conn = DatabaseManager.getConnection();
        assertNotNull(conn, "La connessione non deve essere null");

        // Verifica che la tabella 'area_servizio' sia stata creata davvero all'avvio
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='area_servizio';")) {
            
            assertTrue(rs.next(), "La tabella 'area_servizio' deve essere stata creata all'avvio da schema.sql");
        }
    }
}