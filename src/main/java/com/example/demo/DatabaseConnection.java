package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection()
    {
      //  String databaseName = "mydb";
        String databaseUser = "root";
        String databasePassword = "root";
        String URL = "jdbc:mysql://localhost:3306/mydb";
        //denisa String databasePassword = "199145";
        //String URL = "jdbc:mysql://localhost:3306/bazadate";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(URL, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();

        }
        return databaseLink;
    }

}
