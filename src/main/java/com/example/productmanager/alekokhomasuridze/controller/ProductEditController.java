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
 * Controller class for editing a product.
 * <p>
 * This class manages the editing of a product entity, including updating the product details
 * in the database and refreshing the associated table view.
 * </p>
 */
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

    /**
     * Initializes the controller after the FXML file has been loaded.
     * <p>
     * This method configures the input fields and initializes the DatabaseService.
     * </p>
     */
    public void initialize() {

        // Configure input fields to accept specific data types
        FXMLUtils.makeDecimalField(priceField);
        FXMLUtils.makeNumericField(quantityField);

        try {
            ProductDAOImpl productDAO = new ProductDAOImpl(ConnectionUtil.getConnection());
            databaseService = new DatabaseService(productDAO);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the current stage (window).
     */
    private void exit() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Cancels the editing process and closes the window.
     *
     * @param actionEvent The action event triggered by the cancel button.
     */
    public void cancel(ActionEvent actionEvent) {
        exit();
    }

    /**
     * Saves the changes made to the product and updates the database.
     *
     * @param event The action event triggered by the edit button.
     */
    public void save(ActionEvent event) {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        Product newProduct = new Product(name, description, price, quantity);

        // Update the product in the database
        databaseService.updateProduct(product, newProduct);

        // Refresh the product data in the table
        tableController.refreshProductData(event);

        // Close the editing window
        exit();
    }

    /**
     * Sets the TableController instance.
     * <p>
     * This is used to refresh the table view after a product is edited.
     * </p>
     *
     * @param tableController The TableController instance to be set.
     */
    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }

    /**
     * Sets the product to be edited and populates the input fields with its current details.
     *
     * @param product The product to be edited.
     */
    public void setProduct(Product product) {
        this.product = product;
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        descriptionField.setText(product.getDescription());
        quantityField.setText(String.valueOf(product.getQuantity()));
    }

    /**
     * Sets the target product ID, which can be used for further operations.
     *
     * @param targetProductId The ID of the target product.
     */
    public void setTargetProductId(int targetProductId) {
        this.targetProductId = targetProductId;
    }
}
