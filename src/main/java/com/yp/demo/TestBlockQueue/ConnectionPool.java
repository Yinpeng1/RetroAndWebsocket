package com.yp.demo.TestBlockQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private BlockingQueue<Connection> connections;
    private static final int DEFAULT_SIZE = 16;
    private int size;

    public ConnectionPool(String url, String username, String paassword){
        connections = new LinkedBlockingQueue<>(DEFAULT_SIZE);
        size = DEFAULT_SIZE;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                Connection connection = DriverManager.getConnection(url,username, paassword);
                connections.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //同步获取connection
    public Connection getConnectionBySync(){
            try {
               return connections.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
    }

    public void free(Connection connection){
        if (connection != null){
            this.connections.add(connection);
        }
    }

    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://127.0.0.1:3306/yp?userUnicode=true&amp;characterEncoding=utf-8","root","");

    }
}
