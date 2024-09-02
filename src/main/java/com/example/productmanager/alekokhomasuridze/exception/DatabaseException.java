package com.example.productmanager.alekokhomasuridze.exception;

/**
 * Custom exception class for handling database-related errors.
 * <p>
 * This class extends the {@link RuntimeException} and is used to represent
 * exceptions that occur during database operations. It allows for providing
 * an error message and an underlying cause.
 * </p>
 */
public class DatabaseException extends RuntimeException {

    /**
     * Constructs a new DatabaseException with the specified detail message and cause.
     *
     * @param message The detail message explaining the reason for the exception.
     * @param cause   The underlying cause of the exception (can be {@code null}).
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
