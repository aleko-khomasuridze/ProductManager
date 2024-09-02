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
            System.out.print(e.getMessage());
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Edit Product");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
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

    public void addNewProduct(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/productmanager/alekokhomasuridze/AddProductWindow.fxml")));
        Parent parent = loader.load();
        ProductAddController productAddController = loader.getController();
        productAddController.setTableController(this);
        Scene scene = new Scene(parent);
        addStage.setTitle("Add New Product");
        addStage.initModality(Modality.APPLICATION_MODAL); // Makes the window modal
        addStage.setScene(scene);
        addStage.show();
    }

}
