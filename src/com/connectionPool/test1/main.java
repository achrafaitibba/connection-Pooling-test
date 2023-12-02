package com.connectionPool.test1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {
    public static void main(String[] args) throws Exception {
// Create an instance of the ConnectionPool class


        /*normally it will initiate 10 connections every time I call the methode
        that use the connectionPool class,but if I use it with dependency injection
        it will only gets intiated once >>> so it's perfectly working with dependency injection hh I GUESS */
        connectionPool pool = new connectionPool(10);

// Retrieve a connection from the pool
        Connection connection = pool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");

        if (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

// Close the statement and connection
        statement.close();
        pool.returnConnection(connection);

// When you are done using the connection pool, close all connections in the queue
        pool.closeAllConnections();

    }
}
