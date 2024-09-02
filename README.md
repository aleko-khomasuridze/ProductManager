
# Product Manager Application

## Overview

The **Product Manager Application** is a JavaFX-based desktop application designed to manage a product inventory for small to medium-sized businesses. It provides a user-friendly interface for adding, editing, deleting, and viewing products. The application is built with modularity in mind, separating concerns between data access, business logic, and UI components.

## Features

- **Product Management**: Add, edit, delete, and view products in the inventory.
- **Data Validation**: Ensure data integrity with input validation for numeric and decimal fields.
- **Responsive UI**: A clean and intuitive interface built using JavaFX.
- **Modular Design**: Separation of concerns across different layers (Controller, Service, DAO).
- **Database Integration**: Persistent data storage using an SQL database.

## Project Structure

The project is organized into the following packages:

- **controller**: Contains the JavaFX controllers that manage UI interactions.
  - `TableController.java`: Manages the product list and handles CRUD operations.
  - `ProductEditController.java`: Manages the editing of existing products.
  - `ProductAddController.java`: Manages the addition of new products.
  
- **service**: Contains the service classes that encapsulate business logic.
  - `DatabaseService.java`: Handles database operations related to products.

- **dao**: Data Access Object (DAO) classes responsible for direct database interaction.
  - `ProductDAO.java`: Interface defining CRUD operations for products.
  - `ProductDAOImpl.java`: Implementation of the `ProductDAO` interface.

- **model**: Contains the data models used throughout the application.
  - `Product.java`: Entity class representing a product in the inventory.
  - `ProductDTO.java`: Data Transfer Object for transferring product data.

- **util**: Utility classes that provide common functionality.
  - `ConnectionUtil.java`: Manages database connections.
  - `FXMLUtils.java`: Contains helper methods for configuring JavaFX components.

- **alerts**: Contains custom alert dialogs.
  - `ConfirmDialog.java`: Utility class for displaying confirmation dialogs.

- **exception**: Custom exceptions for error handling.
  - `DatabaseException.java`: Custom exception class for database-related errors.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher.
- **JavaFX SDK** for running the JavaFX application.
- **Maven** for dependency management and building the project.
- **SQL Database** (e.g., MySQL, SQLite) for persistent data storage.

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/aleko-khomasuridze/ProductManager.git
   ```

2. **Set up the database:**
   - Configure your database connection in `DatabaseConfig.java`.
   - Run the provided SQL script (if available) to set up the necessary tables.
   - Follow the detailed instructions in `src/MySQL_Docker_Setup_README.md` to set up a MySQL database using Docker.

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn javafx:run
   ```

### Usage

1. **Adding a Product**: Click the "Add New Product" button, fill in the product details, and click "Save."
2. **Editing a Product**: Click the "Edit" button next to a product, modify the details, and click "Save."
3. **Deleting a Product**: Click the "Delete" button next to a product and confirm the deletion.
4. **Viewing Products**: The main table displays all products with their details.

### Customization

- **Styling**: Customize the UI by modifying the FXML files and associated CSS files.
- **Business Logic**: Add or modify business logic by editing the service classes.
- **Database Configuration**: Adjust the database connection settings in `DatabaseConfig.java`.

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request with your changes.

## Acknowledgments

- Thanks to the JavaFX community for providing an excellent UI framework.
- Inspired by real-world product management systems and inventory tools.
