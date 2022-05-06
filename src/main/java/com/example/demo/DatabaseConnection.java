
/*package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection()
    {
        String databaseName = "proiectfis";
        String databaseUser = "";
        String databasePassword = "";
        String URL = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(URL, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }
        return databaseLink;
    }

}*/
