package utils;

import java.sql.*;

public class DataSource {
    private String url = "jdbc:mysql://localhost:3306/bravo";
    private String login = "root";
    private String pwd = "";
    private Connection cnx;
    private static DataSource instance;
    public DataSource(){
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie");
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

        return cnx;
    }
}
