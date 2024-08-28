package com.example.productmanager.alekokhomasuridze.controller;

import com.example.productmanager.alekokhomasuridze.alerts.ConfirmDialog;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.sql.SQLException;

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

    @FXML
    public void initialize() {
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
            System.out.print(e.getMessage());
        }

        this.productData = databaseService.getAllProducts();

        productsTableView.setItems(productData);
        addButtonToTable();
    }

    private void addButtonToTable() {
        actionView.setCellFactory(param -> new TableCell<Product, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
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

    private void handleEditAction(Product product) {
        // Delegate to a service or open a dialog for editing
        // For example, open a product edit form dialog
        // ProductEditDialog.show(product);
    }

    private void handleDeleteAction(Product product) {
        // Confirm before deleting

        boolean confirmed = ConfirmDialog.show("Are you sure you want to delete this product?");
        if (confirmed) {
            databaseService.deleteProduct(product);
            productsTableView.getItems().remove(product);
        }
    }

    public void refreshProductData(ActionEvent event) {
        productData.clear();
        productData.addAll(databaseService.getAllProducts());
        productsTableView.setItems(productData);
    }

    public void addDummyProduct(ActionEvent event) {
        try {
            int nextId = productData.size() + 1; // Simple way to increment ID
            Product newProduct = new Product(nextId, "Product " + nextId, "Description for product " + nextId, 99.99 * nextId, nextId * 10);
            productData.add(newProduct);
            databaseService.addProduct(newProduct);
            this.refreshProductData(event);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
