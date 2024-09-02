package com.example.productmanager.alekokhomasuridze.service;

import com.example.productmanager.alekokhomasuridze.dao.ProductDAO;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.exception.DatabaseException;
import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * Service class for managing database operations related to products.
 * <p>
 * This class acts as a layer between the data access objects (DAOs) and the
 * rest of the application, providing methods to handle product-related
 * database operations such as adding, retrieving, updating, and deleting products.
 * </p>
 */
public class DatabaseService {
    private final ProductDAO productDAO;

    /**
     * Constructs a new DatabaseService with the specified ProductDAO implementation.
     *
     * @param productDAO The ProductDAO implementation to be used for database operations.
     */
    public DatabaseService(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Adds a new product to the database.
     *
     * @param product The product to be added.
     * @throws RuntimeException If an SQL exception occurs during the operation.
     */
    public void addProduct(Product product) {
        try {
            productDAO.insertProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding product", e);
        }
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to be retrieved.
     * @return A ProductDTO containing the product's data.
     * @throws RuntimeException If an SQL exception occurs during the operation.
     */
    public ProductDTO getProductById(int id) {
        try {
            return productDAO.findProductById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving product", e);
        }
    }

    /**
     * Updates an existing product in the database.
     *
     * @param targetProduct The product to be updated.
     * @param newProduct    The new product data to update with.
     * @throws RuntimeException If an SQL exception occurs during the operation.
     */
    public void updateProduct(Product targetProduct, Product newProduct) {
        try {
            productDAO.updateProduct(targetProduct, newProduct);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    /**
     * Deletes a product from the database.
     *
     * @param product The product to be deleted.
     * @return {@code true} if the product was successfully deleted; {@code false} otherwise.
     * @throws RuntimeException If an SQL exception or custom DatabaseException occurs during the operation.
     */
    public boolean deleteProduct(Product product) {
        try {
            return productDAO.deleteProduct(product);
        } catch (SQLException | DatabaseException e) {
            throw new RuntimeException("Error deleting product", e);
        }
    }

    /**
     * Retrieves a list of all products from the database.
     *
     * @return An ObservableList of all products.
     * @throws RuntimeException If an SQL exception occurs during the operation.
     */
    public ObservableList<Product> getAllProducts() {
        try {
            return productDAO.findAllProduct();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all products", e);
        }
    }
}
