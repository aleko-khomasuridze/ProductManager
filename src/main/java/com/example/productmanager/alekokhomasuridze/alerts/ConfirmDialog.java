package com.example.productmanager.alekokhomasuridze.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Utility class for displaying confirmation dialogs.
 * <p>
 * This class provides a method to show a confirmation dialog with a given message
 * and returns the user's response.
 * </p>
 */
public class ConfirmDialog {

    /**
     * Displays a confirmation dialog with the specified message.
     *
     * @param message The message to be displayed in the confirmation dialog.
     * @return {@code true} if the user clicks OK; {@code false} otherwise.
     */
    public static boolean show(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null); // No header text is displayed
        alert.setContentText(message); // Set the main content message

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
