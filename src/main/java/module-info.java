module com.example.productmanager.alekokhomasuridze {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

    opens com.example.productmanager.alekokhomasuridze.model.entity to javafx.base;
    opens com.example.productmanager.alekokhomasuridze.controler to javafx.fxml;
    exports com.example.productmanager.alekokhomasuridze;
}