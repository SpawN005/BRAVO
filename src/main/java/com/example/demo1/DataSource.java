package com.example.demo1;

import java.sql.*;
import java.io.IOException;

public class DataSource {
    private static DataSource instance;
    Connection connection;

    public DataSource(){
        try {
              connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
        } catch (  SQLException e) {
            e.printStackTrace();
        }
    }
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
    public Connection getCnx(){

        return connection;
    }
}
