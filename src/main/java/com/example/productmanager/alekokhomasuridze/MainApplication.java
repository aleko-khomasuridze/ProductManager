package com.example.productmanager.alekokhomasuridze;

import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import com.example.productmanager.alekokhomasuridze.service.DatabaseService;
import com.example.productmanager.alekokhomasuridze.util.ConnectionUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainView.fxml")));
        stage.setTitle("Product Manager");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        ProductDAOImpl productDAO = null;
        try {
            productDAO = new ProductDAOImpl(ConnectionUtil.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ProductDTO productDTO = new ProductDTO(
                "asjkdla",
                "dksamks",
                12.43,
                34
        );
        DatabaseService databaseService = new DatabaseService(productDAO);
        databaseService.addProduct( productDTO );
        try {
            databaseService.deleteProduct(databaseService.getProductById(2));
        } catch (Exception  e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
