package com.example.productmanager.alekokhomasuridze.dao;

import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Implementation of the ProductDAO interface for managing product data in the database.
 * <p>
 * This class provides methods to insert, update, delete, and retrieve product data
 * from the database. It uses JDBC to interact with the database.
 * </p>
 */
public class ProductDAOImpl implements ProductDAO {
    private final Connection connection;

    /**
     * Constructs a new ProductDAOImpl with the specified database connection.
     *
     * @param connection The database connection to be used for data operations.
     */
    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product The product to be inserted.
     * @throws SQLException If an SQL error occurs during the operation.
     */
    @Override
    public void insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        }
    }

    /**
     * Retrieves all products from the database.
     *
     * @return An ObservableList of all products.
     * @throws SQLException If an SQL error occurs during the operation.
     */
    @Override
    public ObservableList<Product> findAllProduct() throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Products";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                ));
            }
        }
        return products;
    }

    /**
     * Updates an existing product in the database with new values.
     *
     * @param targetProduct The product to be updated.
     * @param newProduct    The new product data to replace the existing data.
     * @throws SQLException If an SQL error occurs during the operation.
     */
    @Override
    public void updateProduct(Product targetProduct, Product newProduct) throws SQLException {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newProduct.getName());
            statement.setString(2, newProduct.getDescription());
            statement.setDouble(3, newProduct.getPrice());
            statement.setInt(4, newProduct.getQuantity());
            statement.setInt(5, targetProduct.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        }
    }

    /**
     * Deletes a product from the database.
     *
     * @param product The product to be deleted.
     * @return {@code true} if the product was successfully deleted; {@code false} otherwise.
     * @throws SQLException If an SQL error occurs during the operation.
     */
    @Override
    public boolean deleteProduct(Product product) throws SQLException {
        String sql = "DELETE FROM Products WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Finds a product in the database by its ID.
     *
     * @param id The ID of the product to be found.
     * @return A ProductDTO containing the product's data, or {@code null} if no product was found with the specified ID.
     * @throws SQLException If an SQL error occurs during the operation.
     */
    @Override
    public ProductDTO findProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Products WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ProductDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
            }
        }
        return null;
    }
}
