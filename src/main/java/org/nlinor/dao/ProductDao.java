package org.nlinor.dao;

import org.nlinor.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public static void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Product (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    price REAL NOT NULL,
                    quantity INTEGER NOT NULL
                );
                """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Product tablosu oluşturuldu.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProduct(Product product) {
        String sql = "INSERT INTO Product(name, price, quantity) VALUES(?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.executeUpdate();
            System.out.println(product.getName() + " başarıyla eklendi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public static void dropTable() {
        String sql = "DROP TABLE IF EXISTS Product";

        try (Connection conn = DatabaseManager.getConnection();
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Product tablosu kaldırıldı.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addQuantityColumn() {
        String checkColumnSql = """
        PRAGMA table_info(Product);
        """;

        String addColumnSql = "ALTER TABLE Product ADD COLUMN quantity INTEGER DEFAULT 0";

        try (Connection conn = DatabaseManager.getConnection();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(checkColumnSql)) {

            boolean columnExists = false;

            while (rs.next()) {
                String columnName = rs.getString("name");
                if ("quantity".equalsIgnoreCase(columnName)) {
                    columnExists = true;
                    break;
                }
            }

            if (!columnExists) {
                stmt.execute(addColumnSql);
                System.out.println("Quantity sütunu tabloya eklendi.");
            } else {
                System.out.println("Quantity sütunu zaten mevcut.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
