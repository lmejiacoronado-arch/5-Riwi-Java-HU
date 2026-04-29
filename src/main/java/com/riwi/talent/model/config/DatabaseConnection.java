package com.riwi.talent.model.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Task 1 - HU5: JDBC Connection Manager.
 *
 * LEGACY (Java 7 and earlier) — Manual closing with finally block:
 *   Connection conn = null;
 *   try {
 *       conn = DriverManager.getConnection(URL, USER, PASS);
 *   } catch (SQLException e) {
 *       e.printStackTrace();
 *   } finally {
 *       if (conn != null) {
 *           try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
 *       }
 *   }
 *   PROBLEM: verbose, error-prone. If the developer forgets the finally block,
 *   the connection stays open forever → MEMORY LEAK.
 *   The DB eventually runs out of connections and crashes.
 *
 * MODERN (Java 7+ try-with-resources, best practice in Java 17/21):
 *   try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
 *       // use connection...
 *   }
 *   The JVM automatically calls conn.close() when the block exits,
 *   whether by normal completion, return, or any exception.
 *   This works because Connection implements AutoCloseable.
 *   BENEFIT: memory leaks from unclosed connections are physically impossible.
 *
 * HOW MEMORY LEAKS HAPPEN WITHOUT try-with-resources:
 *   Each unclosed Connection holds a socket to the DB server.
 *   Over time they accumulate in the heap until the DB rejects
 *   new connections ("Too many connections") and the JVM runs out of memory.
 */
public class DatabaseConnection {

    // SQLite — no server needed, creates a local .db file automatically
    private static final String URL = "jdbc:sqlite:talent_hub.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Creates the employees table if it does not exist yet.
     * Called once at startup from Main before launching the HU5 menu.
     */
    public static void initializeDatabase() {
        String sql = """
                CREATE TABLE IF NOT EXISTS employees (
                    id            INTEGER PRIMARY KEY AUTOINCREMENT,
                    full_name     TEXT    NOT NULL,
                    email         TEXT    NOT NULL UNIQUE,
                    role          TEXT    NOT NULL,
                    base_salary   REAL    NOT NULL,
                    score         INTEGER NOT NULL
                );
                """;

        try (Connection conn = getConnection();
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("[DB] Table 'employees' ready.");
        } catch (SQLException e) {
            System.err.println("[DB ERROR] " + e.getMessage());
        }
    }
}
