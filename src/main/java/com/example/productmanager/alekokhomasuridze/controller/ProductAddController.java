package com.example.productmanager.alekokhomasuridze.controller;

import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import com.example.productmanager.alekokhomasuridze.util.FXMLUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controller class for adding a new product.
 * <p>
 * This class manages the addition of a new product entity, including saving the product
 * details to the database and refreshing the associated table view.
 * </p>
 */
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

    /**
     * Initializes the controller after the FXML file has been loaded.
     * <p>
     * This method configures the input fields and initializes the DatabaseService.
     * </p>
     */
    public void initialize() {
        // Configure input fields to accept specific data types
        FXMLUtils.makeNumericField(quantityField);
        FXMLUtils.makeDecimalField(priceField);

        try {
            ProductDAOImpl productDAO = new ProductDAOImpl(ConnectionUtil.getConnection());
            databaseService = new DatabaseService(productDAO);
        } catch (SQLException e) {
            System.out.printf("%s", e.getMessage());
        }
    }

    /**
     * Adds a new product to the database and updates the table view.
     *
     * @param actionEvent The action event triggered by the add button.
     */
    public void add(ActionEvent actionEvent) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(name, description, price, quantity);

        // Add the new product to the database
        databaseService.addProduct(product);

        // Refresh the product data in the table
        tableController.refreshProductData(actionEvent);

        // Close the window after adding the product
        exit();
    }

    /**
     * Closes the current stage (window).
     */
    private void exit() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Cancels the addition process and closes the window.
     *
     * @param actionEvent The action event triggered by the cancel button.
     */
    public void cancel(ActionEvent actionEvent) {
        exit();
    }

    /**
     * Sets the TableController instance.
     * <p>
     * This is used to refresh the table view after a product is added.
     * </p>
     *
     * @param tableController The TableController instance to be set.
     */
    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }
}
