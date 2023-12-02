package com.connectionPool.test1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class connectionPool {
    // Create a BlockingQueue to store the connections
    private BlockingQueue<Connection> connectionQueue;

    // Create a constructor to initialize the queue and add connections to it
    public connectionPool(int initialConnections) throws SQLException, ClassNotFoundException {
        connectionQueue = new ArrayBlockingQueue<Connection>(initialConnections);
        for (int i = 0; i < initialConnections; i++) {
            connectionQueue.add(createConnection());
        }
    }

    // Create a method to create a new connection
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        Connection connection=new connection().getConnected();
        return  connection;
    }

    // Create a method to retrieve a connection from the queue
    public Connection getConnection() throws InterruptedException {
        return connectionQueue.take();
    }

    // Create a method to return a connection to the queue
    public void returnConnection(Connection connection) throws InterruptedException {
        connectionQueue.put(connection);
    }

    // Create a method to close all connections in the queue
    public void closeAllConnections() throws SQLException {
        for (Connection connection : connectionQueue) {
            connection.close();
        }
    }
}


