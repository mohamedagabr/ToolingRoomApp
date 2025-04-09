package com.elsewedyt.toolingapp.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DB_NAME = "TOOLING";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnect(){
        Connection con = null ;
        try {
            con = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DB_NAME),USERNAME,PASSWORD);
        }catch (Exception e){
            System.out.println("getConnect :" + e);
        }
        return con ;
    }
}
