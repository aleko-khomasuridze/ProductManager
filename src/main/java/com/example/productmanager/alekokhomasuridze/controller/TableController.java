package com.example.productmanager.alekokhomasuridze.controller;

import com.example.productmanager.alekokhomasuridze.Logger.Logger;
import com.example.productmanager.alekokhomasuridze.alerts.ConfirmDialog;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Controller class for managing the product table view.
 * <p>
 * This class handles the display, addition, editing, and deletion of products in the table view.
 * It also provides the necessary interactions with the database to perform these operations.
 * </p>
 */
public class TableController {
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> idView;
    @FXML
    private TableColumn<Product, String> nameView;
    @FXML
    private TableColumn<Product, String> descriptionView;
    @FXML
    private TableColumn<Product, Double> priceView;
    @FXML
    private TableColumn<Product, Integer> quantityView;
    @FXML
    private TableColumn<Product, Void> actionView;

    private Connection connection = null;
    private ProductDAOImpl productDAO = null;
    private DatabaseService databaseService = null;
    private ObservableList<Product> productData = FXCollections.observableArrayList();

    /**
     * Initializes the controller after the FXML file has been loaded.
     * <p>
     * This method sets up the table columns, establishes a database connection,
     * and populates the table with product data.
     * </p>
     */
    @FXML
    public void initialize() {
        // Set up table columns to map to Product entity fields
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameView.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionView.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceView.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityView.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        try {
            this.connection = ConnectionUtil.getConnection();
            this.productDAO = new ProductDAOImpl(connection);
            this.databaseService = new DatabaseService(productDAO);
        } catch (SQLException e) {
            Logger.log.Error(e.getMessage());
        }

        // Load product data and display it in the table view
        this.productData = databaseService.getAllProducts();
        productsTableView.setItems(productData);
        addButtonToTable();
    }

    /**
     * Adds edit and delete buttons to the action column of the table view.
     * <p>
     * The edit button opens a new window for editing the selected product.
     * The delete button removes the selected product from the database after confirmation.
     * </p>
     */
    private void addButtonToTable() {
        actionView.setCellFactory(param -> new TableCell<Product, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.getStyleClass().add("btn-default");
                deleteButton.getStyleClass().add("btn-danger");

                editButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    handleEditAction(product);
                });

                deleteButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    handleDeleteAction(product);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox pane = new HBox(editButton, deleteButton);
                    pane.setSpacing(10);
                    setGraphic(pane);
                }
            }
        });
    }

    /**
     * Handles the action of editing a product.
     * <p>
     * This method opens a new window to edit the selected product and passes the product
     * data to the editing controller.
     * </p>
     *
     * @param product The product to be edited.
     */
    private void handleEditAction(Product product) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/productmanager/alekokhomasuridze/EditProductWindow.fxml")));
            root = loader.load();
            ProductEditController productEditController = loader.getController();
            productEditController.setProduct(product);
            productEditController.setTableController(this);
        } catch (IOException | NullPointerException e) {
            Logger.log.Error(e.getMessage());
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Edit Product");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Handles the action of deleting a product.
     * <p>
     * This method prompts the user for confirmation before deleting the selected product
     * from the database and removing it from the table view.
     * </p>
     *
     * @param product The product to be deleted.
     */
    private void handleDeleteAction(Product product) {
        // Confirm before deleting
        boolean confirmed = ConfirmDialog.show("Are you sure you want to delete this product?");
        if (confirmed) {
            databaseService.deleteProduct(product);
            productsTableView.getItems().remove(product);
        }
    }

    /**
     * Refreshes the product data in the table view.
     * <p>
     * This method clears the existing product data and reloads it from the database.
     * </p>
     *
     * @param event The action event triggered by a refresh action.
     */
    public void refreshProductData(ActionEvent event) {
        productData.clear();
        productData.addAll(databaseService.getAllProducts());
        productsTableView.setItems(productData);
    }

    /**
     * Opens a new window to add a new product.
     * <p>
     * This method creates a new window for entering the details of a new product
     * and adds it to the database upon submission.
     * </p>
     *
     * @param event The action event triggered by the add new product button.
     * @throws IOException If the FXML file for the add product window cannot be loaded.
     */
    public void addNewProduct(ActionEvent event) {
        Stage addStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/productmanager/alekokhomasuridze/AddProductWindow.fxml")));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProductAddController productAddController = loader.getController();
        productAddController.setTableController(this);
        Scene scene = new Scene(parent);
        addStage.setTitle("Add New Product");
        addStage.initModality(Modality.APPLICATION_MODAL); // Makes the window modal
        addStage.setScene(scene);
        addStage.show();
    }
}
