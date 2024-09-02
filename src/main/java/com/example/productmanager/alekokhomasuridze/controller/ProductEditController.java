package com.example.productmanager.alekokhomasuridze.controller;

import com.example.productmanager.alekokhomasuridze.dao.ProductDAO;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import com.example.productmanager.alekokhomasuridze.util.FXMLUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductEditController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantityField;

    private Product product;
    private TableController tableController;
    private DatabaseService databaseService;
    private int targetProductId;


    public void initialize () {

        FXMLUtils.makeDecimalField(priceField);
        FXMLUtils.makeNumericField(quantityField);



        try {
            ProductDAOImpl productDAO = new ProductDAOImpl(ConnectionUtil.getConnection());
            databaseService = new DatabaseService(productDAO);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void exit () {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void cancel (ActionEvent actionEvent) {
        exit();
    }

    public void edit(ActionEvent event) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        Product newProduct = new Product(name, description, price, quantity);

        databaseService.updateProduct(product,  newProduct);

        tableController.refreshProductData(event);

        exit();
    }

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }

    public void setProduct(Product product) {
        this.product = product;
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        descriptionField.setText(product.getDescription());
        quantityField.setText(String.valueOf(product.getQuantity()));
    }

    public void setTargetProductId(int targetProductId) {
        this.targetProductId = targetProductId;
    }
}
