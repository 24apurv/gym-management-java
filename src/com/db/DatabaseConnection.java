
package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static Connection connect=null;
   
    public static Connection makeConnection() throws ClassNotFoundException, SQLException
    {
       

            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url="jdbc:oracle:thin:@titanx.ckvadj2t5tmc.ap-south-1.rds.amazonaws.com:1521:titanx";
            String username="admin";
            String password="admin123";
            
            connect=DriverManager.getConnection(url, username, password);
            
            System.out.println("Connection established");
            
            return connect;

        
    }

    public static Connection getConnection() {
        
        return connect;
    }
    
    
    
}
