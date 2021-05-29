package com.kieuanh.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private static Connection connection;

    private ConnectionJDBC() {
    }

    public static final String URL = "jdbc:mysql://localhost:3306/book_manager";
    public static final String USER = "root";
    public static final String PASSWORD = "123456@Abc";

    public static Connection getConnect(){
        if (connection==null){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ket noi khong thanh cong");
            e.printStackTrace();
        }
        }

        return  connection;
    }
}
