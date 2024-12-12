package org.nlinor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // SQLite veritabanı bağlantı URL'si
    private static final String URL = "jdbc:sqlite:localdb.sqlite";

    // Veritabanı bağlantısını döndürür
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Veritabanı bağlantısını test etmek için bir metot
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Veritabanı bağlantısı başarılı!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
