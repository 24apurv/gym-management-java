
package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnection {
    private static Connection connect=null;
   
    public static Connection makeConnection()
    {
       
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url="jdbc:oracle:thin:@titanx.ckvadj2t5tmc.ap-south-1.rds.amazonaws.com:1521:titanx";
            String username="admin";
            String password="admin123";
            
            connect=DriverManager.getConnection(url, username, password);
            
            System.out.println("Connection established");
            
            return connect;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    public static Connection getConnection() {
        
        return connect;
    }
    
    
    
}