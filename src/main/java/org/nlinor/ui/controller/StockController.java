package org.nlinor.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.nlinor.dao.ProductDao;
import org.nlinor.model.Product;

public class StockController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private Button addButton;
    @FXML
    private ListView<String> productList;

    @FXML
    public void initialize() {
        refreshProductList();
    }

    @FXML
    private void addProduct() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        ProductDao.insertProduct(new Product(0, name, price, quantity));
        refreshProductList();
    }

    private void refreshProductList() {
        productList.getItems().clear();
        ProductDao.getAllProducts().forEach(product ->
                productList.getItems().add(product.getName() + " - " + product.getPrice() + "₺ - " + product.getQuantity() + " adet")
        );
    }
}
