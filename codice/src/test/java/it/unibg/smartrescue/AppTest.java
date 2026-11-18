package it.unibg.smartrescue;

import org.junit.jupiter.api.Test;
import it.unibg.smartrescue.database.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;

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
}