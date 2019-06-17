
package com.persistence;

import com.db.DatabaseConnection;
import com.model.MembershipPlans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PersistMembershipPlans {
    public static void store(MembershipPlans membershipPlan) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="INSERT INTO MEMBERSHIPPLANS (PLANNAME,AMOUNT,DURATION) VALUES (?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, membershipPlan.getName());
        pstmt.setFloat(2, membershipPlan.getAmount());
        pstmt.setInt(3, membershipPlan.getDuration());
        pstmt.executeUpdate();
    }
    
    public static void update(MembershipPlans membershipPlan) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="UPDATE MEMBERSHIPPLANS SET AMOUNT=? ,DURATION=? WHERE PLANNAME LIKE ?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(3, membershipPlan.getName());
        pstmt.setFloat(1, membershipPlan.getAmount());
        pstmt.setInt(2, membershipPlan.getDuration());
        pstmt.executeUpdate();
    }
    
    public static MembershipPlans retrieve(String name) throws SQLException
    {
        MembershipPlans membershipPlan = new MembershipPlans();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM MEMBERSHIPPLANS WHERE PLANNAME LIKE ?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next())
        {
            membershipPlan.setName(rs.getString("PLANNAME"));
            membershipPlan.setDuration(rs.getInt("DURATION"));
            membershipPlan.setAmount(rs.getFloat("AMOUNT"));
        }
        return membershipPlan;
    }
    
    public static ObservableList<MembershipPlans> retrieveAll() throws SQLException
    {
        ObservableList<MembershipPlans> list = FXCollections.observableArrayList();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM MEMBERSHIPPLANS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
            list.add(new MembershipPlans(rs.getString("PLANNAME"),rs.getFloat("AMOUNT"),rs.getInt("DURATION")));
        }
        return list;
    }
    
    public static ObservableList<String> retrieveAllNames() throws SQLException
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM MEMBERSHIPPLANS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
        {
            list.add(rs.getString("PLANNAME"));
        }
        return list;
    }
    
    public static void remove(String name) throws SQLException
    {
       Connection conn = DatabaseConnection.getConnection();
       String query = "DELETE FROM MEMBERSHIPPLANS WHERE PLANNAME LIKE ?"; 
       PreparedStatement pstmt = conn.prepareStatement(query);
       pstmt.setString(1, name);
       pstmt.executeUpdate();
    }
}
