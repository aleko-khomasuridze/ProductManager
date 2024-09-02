package com.example.productmanager.alekokhomasuridze.dao;


import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ProductDAO {
    public void insertProduct(Product product) throws SQLException;
    public void updateProduct(Product targetProduct, Product newProduct) throws SQLException;
    public boolean deleteProduct(Product product) throws SQLException;
    public ProductDTO findProductById(int id) throws SQLException;
    public ObservableList<Product> findAllProduct() throws SQLException;
}
