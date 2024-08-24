package com.example.productmanager.alekokhomasuridze;

import com.example.productmanager.alekokhomasuridze.config.DatabaseConfig;
import com.example.productmanager.alekokhomasuridze.dao.ProductDAOImpl;
import com.example.productmanager.alekokhomasuridze.model.dto.ProductDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage.setTitle("Product Manager");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
