
# MySQL Docker Setup for Product Management System

This README provides detailed instructions on setting up a MySQL database using Docker for our Product Management System. Follow these steps to ensure that the MySQL instance is configured correctly and ready for development and testing purposes.

## Prerequisites

- Docker installed on your machine. If you do not have Docker installed, please refer to the [Docker official installation guide](https://docs.docker.com/get-docker/).

## Step 1: Running MySQL in Docker

Execute the following command to run a MySQL instance inside a Docker container named `ProductsDB`. This setup specifies the MySQL root password as `1234`, but you should change this password to ensure security in a production environment.

```bash
docker run --name ProductsDB -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql
```

This command does the following:
- `--name ProductsDB`: Names the Docker container.
- `-p 3306:3306`: Maps port 3306 of the host to port 3306 in the container, allowing MySQL client applications on your host to interact with MySQL.
- `-e MYSQL_ROOT_PASSWORD=1234`: Sets the MySQL root password to `1234`.
- `-d mysql`: Runs the container in the background using the latest MySQL image.

## Step 2: Verifying the Container is Running

To ensure that the MySQL Docker container is up and running, use the following command:

```bash
docker ps
```

You should see `ProductsDB` listed in the running containers.

## Step 3: Connecting to MySQL Server

### Option 1: Using MySQL Client

If you have a MySQL client installed on your host machine, connect using:

```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```

Enter the password when prompted.

### Option 2: Using Docker Exec

If you prefer to connect via Docker, execute the following command:

```bash
docker exec -it ProductsDB mysql -u root -p
```

Enter the password (`1234`) when prompted.

## Step 4: Creating the Database and Tables

Once connected to MySQL, execute the following commands to create a new database named `ProductData` and a table named `Products`.

```sql
CREATE DATABASE ProductData;
USE ProductData;

CREATE TABLE Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Step 5: Inserting Sample Data

To insert some sample data into the `Products` table:

```sql
INSERT INTO Products (name, description, price, quantity) VALUES
('Sample Product', 'This is a sample product description.', 19.99, 100);
```

## Step 6: Querying Data

To verify the data has been inserted correctly:

```sql
SELECT * FROM Products;
```

## Conclusion

You should now have a fully functional MySQL database running inside a Docker container, ready for use with our Product Management System. Modify the database schema as needed for your specific application requirements.
