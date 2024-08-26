package com.example.productmanager.alekokhomasuridze.dao;

import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insertProduct(ProductDTO product) throws SQLException {
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



    @Override
    public void updateProduct(ProductDTO productDTO) throws SQLException {
        String sql = "UPDATE Products SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productDTO.getName());
            statement.setString(2, productDTO.getDescription());
            statement.setDouble(3, productDTO.getPrice());
            statement.setInt(4, productDTO.getQuantity());
            statement.setInt(5, productDTO.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        }
    }

    @Override
    public boolean deleteProduct(ProductDTO productDTO) throws SQLException {
        String sql = "DELETE FROM Products WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productDTO.getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

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
