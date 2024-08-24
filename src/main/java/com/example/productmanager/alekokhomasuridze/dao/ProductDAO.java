package com.example.productmanager.alekokhomasuridze.dao;


import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.model.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    public void insertProduct(ProductDTO product);
    public void updateProduct(Product product) ;
    public boolean deleteProduct(Product product) ;
    public Product findProductById(int id);
    public List<Product> findAllProduct();
}
