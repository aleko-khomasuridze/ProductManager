package com.example.productmanager.alekokhomasuridze.controller;

import com.example.productmanager.alekokhomasuridze.dao.ProductDAO;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import com.example.productmanager.alekokhomasuridze.util.FXMLUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;


public class ProductAddController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantityField;

    private DatabaseService databaseService;
    private TableController tableController;

    public void initialize() {
        FXMLUtils.makeNumericField(quantityField);
        FXMLUtils.makeDecimalField(priceField);

        try{
            ProductDAOImpl productDAO = new ProductDAOImpl(ConnectionUtil.getConnection());
            databaseService = new DatabaseService(productDAO);
        } catch (SQLException e) {
            System.out.printf("%s", e.getMessage());
        }
    }


    public void add(ActionEvent actionEvent) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        int quantity = Integer.parseInt( quantityField.getText());

        Product product = new Product(name, description, price, quantity);

        databaseService.addProduct(product);

        tableController.refreshProductData(actionEvent);

        exit();
    }

    private void exit() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        exit();
    }

    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }
}
