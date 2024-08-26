package com.example.productmanager.alekokhomasuridze.dao;


import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    public void insertProduct(ProductDTO productDTO) throws SQLException;
    public void updateProduct(ProductDTO productDTO) throws SQLException;
    public boolean deleteProduct(ProductDTO productDTO) throws SQLException;
    public ProductDTO findProductById(int id) throws SQLException;
    public ObservableList<Product> findAllProduct() throws SQLException;
}
