package com.example.productmanager.alekokhomasuridze.service;

import com.example.productmanager.alekokhomasuridze.dao.ProductDAO;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class DatabaseService {
    private final ProductDAO productDAO;

    public DatabaseService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    // Adds a new product to the database
    public void addProduct(ProductDTO product) {
        try {
            productDAO.insertProduct(product);
        } catch (SQLException e) {
            // Handle exception (e.g., log it, wrap it in a runtime exception, etc.)
            throw new RuntimeException("Error adding product", e);
        }
    }

    // Retrieves a product by ID
    public ProductDTO getProductById(int id) {
        try {
            return productDAO.findProductById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving product", e);
        }
    }

    // Updates an existing product
    public void updateProduct(ProductDTO productDTO) {
        try {
            productDAO.updateProduct(productDTO);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    // Deletes a product by ID
    public boolean deleteProduct(ProductDTO productDTO) {
        try {
            return productDAO.deleteProduct(productDTO);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product", e);
        }
    }

    // List all products
    public List<Product> getAllProducts() {
        try {
            return productDAO.findAllProduct();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all products", e);
        }
    }
}
