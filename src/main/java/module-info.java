module com.example.productmanager.alekokhomasuridze {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.productmanager.alekokhomasuridze to javafx.fxml;
    exports com.example.productmanager.alekokhomasuridze;
}