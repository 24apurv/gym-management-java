
package com.persistence;

import com.db.DatabaseConnection;
import com.model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PersistUsers {
    
    private static Users users = new Users();
    
    public static void store(Users users) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="INSERT INTO USERS (USERNAME,PWD,PRIVILEGELEVEL,USERSNAME,BRANCH) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, users.getUsername());
        pstmt.setString(2, users.getPassword());
        pstmt.setString(3, users.getPrivilege());
        pstmt.setString(4, users.getUsersname());
        pstmt.setString(5, users.getBranch());
        pstmt.executeUpdate();
    }
    
    public static void update(Users users) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="UPDATE USERS SET PWD=?,PRIVILEGELEVEL=?,USERSNAME=?,BRANCH=? WHERE USERNAME=?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(5, users.getUsername());
        pstmt.setString(1, users.getPassword());
        pstmt.setString(2, users.getPrivilege());
        pstmt.setString(3, users.getUsersname());
        pstmt.setString(4, users.getBranch());
        pstmt.executeUpdate();
    }
    
    public static Users retrieve(String username,String password) throws SQLException
    {
        users.setUsername(username);
        users.setPassword(password);
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM USERS WHERE USERNAME LIKE ? AND PWD LIKE ?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet result=pstmt.executeQuery();
        while(result.next())
        {
            users.setBranch(result.getString("BRANCH"));
            users.setUsersname(result.getString("USERSNAME"));
            users.setPrivilege(result.getString("PRIVILEGELEVEL"));
        }
        System.out.println(users);
        return users;
    }
    
      public static ObservableList<Users> retrieveAll() throws SQLException
    {
        ObservableList<Users> list = FXCollections.observableArrayList();
        
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM USERS";
        Statement stmt = conn.createStatement();
        ResultSet result=stmt.executeQuery(query);
        while(result.next())
        {
            Users user = new Users();
            user.setBranch(result.getString("BRANCH"));
            user.setUsersname(result.getString("USERSNAME"));
            user.setPrivilege(result.getString("PRIVILEGELEVEL"));
            user.setUsername(result.getString("USERNAME"));
            user.setPassword(result.getString("PWD"));
            list.add(user);
        }
        return list;
    }
    
    public static Users getUsers()
    {
        return users;
    }
    
    public static void remove(String username) throws SQLException
    {
       Connection conn = DatabaseConnection.getConnection();
       String query = "DELETE FROM USERS WHERE USERNAME LIKE ?"; 
       PreparedStatement pstmt = conn.prepareStatement(query);
       pstmt.setString(1, username);
       pstmt.executeUpdate();
    }
    
}
