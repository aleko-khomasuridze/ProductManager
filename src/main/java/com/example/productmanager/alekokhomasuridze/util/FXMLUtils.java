package com.example.productmanager.alekokhomasuridze.util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * Utility class for JavaFX FXML operations.
 * <p>
 * This class provides utility methods to modify the behavior of JavaFX {@link TextField}
 * components, such as restricting input to numeric or decimal values.
 * </p>
 */
public class FXMLUtils {

    /**
     * Configures a {@link TextField} to accept only numeric input.
     * <p>
     * This method applies a {@link TextFormatter} to the specified {@code textField}
     * that allows only digits to be entered.
     * </p>
     *
     * @param textField The {@link TextField} to be configured.
     */
    public static void makeNumericField(TextField textField) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        });
        textField.setTextFormatter(textFormatter);
    }

    /**
     * Configures a {@link TextField} to accept only decimal input.
     * <p>
     * This method applies a {@link TextFormatter} to the specified {@code textField}
     * that allows only digits and a single decimal point to be entered.
     * </p>
     *
     * @param textField The {@link TextField} to be configured.
     */
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
