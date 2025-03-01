package com.learn.challenges.designpattern.creational;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class SingletonDbConnectionPool {

    static class DatabaseConnectionPool {

        private static DatabaseConnectionPool instance;
        private final Queue<Connection> connectionPool;
        private final int MAX_CON_SIZE = 10;

        private DatabaseConnectionPool() {
            connectionPool = new LinkedList<>();
            for (int i = 0; i < MAX_CON_SIZE; i ++) {
                connectionPool.add(createNewConnection());
            }
        }

        public static synchronized DatabaseConnectionPool getInstance() {
            if (instance == null) {
                instance = new DatabaseConnectionPool();
            }
            return instance;
        }

        private Connection createNewConnection() {
            try {
                return DriverManager.getConnection(
                        "jdbc:your_database_url", "username", "password");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public Connection getConnection() {
            if (connectionPool.isEmpty()) {
                // Handle case when no connections are available
                return null;
            }
            return connectionPool.poll();
        }

        public void releaseConnection(Connection connection) {
            if (connection != null)
                connectionPool.add(connection);
        }

    }

    public static void main(String[] args) {
        DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        pool.releaseConnection(connection);
    }
}
