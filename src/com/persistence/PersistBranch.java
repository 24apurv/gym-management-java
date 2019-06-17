
package com.persistence;

import com.db.DatabaseConnection;
import com.model.Branch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PersistBranch {
    private static Branch branch;
    
    public static void store(Branch branch) throws SQLException
    {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO BRANCH (BRANCHNAME,CODE,ADDRESS) VALUES (?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, branch.getName());
        pstmt.setString(2, branch.getCode());
        pstmt.setString(3, branch.getAddress());
        pstmt.executeUpdate();
    }
    
    public static void update(Branch branch) throws SQLException
    {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE BRANCH SET CODE=?, ADDRESS=? WHERE BRANCHNAME=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, branch.getAddress());
        pstmt.setString(2, branch.getCode());
        pstmt.setString(3, branch.getName());
        pstmt.executeUpdate();
    }
    
    public static Branch retrieve(String name) throws SQLException
    {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM BRANCH WHERE BRANCHNAME=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next())
        {
            branch.setName(rs.getString("BRANCHNAME"));
            branch.setCode(rs.getString("CODE"));
            branch.setAddress(rs.getString("ADDRESS"));
        }
        return branch;
    }
    
    public static void remove(String code) throws SQLException
    {
       Connection conn = DatabaseConnection.getConnection();
       String query = "DELETE FROM BRANCH WHERE CODE=?"; 
       PreparedStatement pstmt = conn.prepareStatement(query);
       pstmt.setString(1, code);
       pstmt.executeUpdate();
    }
    
    public static Branch getBranch()
    {
        return branch;
    }
    
    public static ObservableList<Branch> retrieveAll() throws SQLException
    {
        ObservableList<Branch> list = FXCollections.observableArrayList();
         Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM BRANCH";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
            list.add(new Branch(rs.getString("BRANCHNAME"),rs.getString("CODE"),rs.getString("ADDRESS")));
        }
        return list;
    }
    
     public static ObservableList<String> retrieveAllNames() throws SQLException
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM BRANCH";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
            list.add(rs.getString("BRANCHNAME"));
        }
        return list;
    }
}
