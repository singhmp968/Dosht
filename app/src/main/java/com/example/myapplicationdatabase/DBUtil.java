package com.example.myapplicationdatabase;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {
    Connection conn;
    String url="jdbc:oracle:thin:@localhost:1521:XE";

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url,"hr12","root");
        }catch(Exception e) {
            System.out.println("asda");
        }
        return conn;
    }
}
