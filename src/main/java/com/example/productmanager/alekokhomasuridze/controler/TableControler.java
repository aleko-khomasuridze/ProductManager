package com.example.productmanager.alekokhomasuridze.controler;

import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameView.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionView.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceView.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityView.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productsTableView.setItems(productData);
    }

    public void addDummyProduct(ActionEvent event) {
        int nextId = productData.size() + 1; // Simple way to increment ID
        Product newProduct = new Product(nextId, "Product " + nextId, "Description for product " + nextId, 99.99 * nextId, nextId * 10);
        productData.add(newProduct);
    }
}
