package org.nlinor;

import javafx.application.Application;
import org.nlinor.ui.MainApp;
import org.nlinor.dao.ProductDao;

public class Main {
    public static void main(String[] args) {
        // Veritabanı tablolarını başlat
        ProductDao.createTable(); // Tabloyu oluştur
        ProductDao.addQuantityColumn(); // Sütunu ekle

        // JavaFX uygulamasını başlat
        Application.launch(MainApp.class, args);

    }
}
