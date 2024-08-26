package com.example.productmanager.alekokhomasuridze.controler;

import com.example.productmanager.alekokhomasuridze.config.DatabaseConfig;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAO;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TableControler {
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
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
