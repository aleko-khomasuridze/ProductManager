package com.example.productmanager.alekokhomasuridze.util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class FXMLUtils {
    public static void makeNumericField(TextField textField) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(textFormatter);
    }

    public static void makeDecimalField(TextField textField) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*\\.?\\d*")) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(textFormatter);
    }
}
