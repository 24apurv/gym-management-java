
package com.persistence;

import com.db.DatabaseConnection;
import com.model.Customer;
import gui.user.UserWindowController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersistCustomer {
    
    private static ObservableList<Customer> list;
    
    public static void add(Customer customer) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="INSERT INTO CUSTOMER(CUSTOMERNAME,GENDER,DATEOFBIRTH,ADDRESS,MOBILENUMBER,EMAILID,BRANCH) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getGender());
        pstmt.setDate(3, customer.getDateOfBirth());
        pstmt.setString(4, customer.getAddress());
        pstmt.setString(5, customer.getMobileNumber());
        pstmt.setString(6, customer.getEmailId());
        pstmt.setString(7, customer.getBranch());
        pstmt.executeUpdate();
    }
    
    public static void membershipUpgrade(Customer customer) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="UPDATE CUSTOMER SET STARTINGDATE=?, EXPIRATIONDATE=?, MEMBERSHIPPLAN=? WHERE PRN=?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setDate(1, customer.getStartingDate());
        pstmt.setDate(2, customer.getExpirationDate());
        pstmt.setString(3, customer.getMembershipPlan());
        pstmt.setString(4, UserWindowController.getPrn());
        pstmt.executeUpdate();
    }
    
    public static void update(Customer customer) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="UPDATE CUSTOMER SET CUSTOMERNAME=?, GENDER=?, DATEOFBIRTH=?, ADDRESS=?, MOBILENUMBER=?, EMAILID=?, BRANCH=? WHERE PRN=?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, customer.getName());
        pstmt.setString(2, customer.getGender());
        pstmt.setDate(3, customer.getDateOfBirth());
        pstmt.setString(4, customer.getAddress());
        pstmt.setString(5, customer.getMobileNumber());
        pstmt.setString(6, customer.getEmailId());
        pstmt.setString(7, customer.getBranch());
        pstmt.setInt(8, customer.getPrn());
        pstmt.executeUpdate();
    }
    
    public static ObservableList<Customer> searchByMobile(String mobileNumber) throws SQLException
    {
        list = FXCollections.observableArrayList();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM CUSTOMER WHERE MOBILENUMBER LIKE ?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, mobileNumber);
        ResultSet result=pstmt.executeQuery();
        while(result.next())
        {
            list.add(new Customer(result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("EMAILID"), result.getString("ADDRESS"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"),result.getString("BRANCH"), result.getString("MEMBERSHIPPLAN"), result.getString("GENDER"), result.getDate("DATEOFBIRTH"), result.getInt("PRN")));
        }
        return list;
    }
    
    public static ObservableList<Customer> searchByName(String name) throws SQLException
    {
        list = FXCollections.observableArrayList();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM CUSTOMER WHERE CUSTOMERNAME LIKE ?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, name);
        ResultSet result=pstmt.executeQuery();
        while(result.next())
        {
            list.add(new Customer(result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("EMAILID"), result.getString("ADDRESS"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"),result.getString("BRANCH"), result.getString("MEMBERSHIPPLAN"), result.getString("GENDER"), result.getDate("DATEOFBIRTH"), result.getInt("PRN")));
        }
        return list;
    }
    
    public static void remove(Integer prn) throws SQLException
    {
       Connection conn = DatabaseConnection.getConnection();
       String query = "DELETE FROM CUSTOMER WHERE PRN=?"; 
       PreparedStatement pstmt = conn.prepareStatement(query);
       pstmt.setInt(1, prn);
       pstmt.executeUpdate();
    }
    
    public static ObservableList<Customer> retrieveAll() throws SQLException
    {
        list = FXCollections.observableArrayList();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM CUSTOMER";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while(result.next())
        {
            list.add(new Customer(result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("EMAILID"), result.getString("ADDRESS"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"),result.getString("BRANCH"), result.getString("MEMBERSHIPPLAN"), result.getString("GENDER"), result.getDate("DATEOFBIRTH"), result.getInt("PRN")));
        }
        return list;
    }
    
    public static Customer retrieve(String prn) throws SQLException
    {
        Customer customer = new Customer();
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM CUSTOMER WHERE PRN=?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, prn);
        ResultSet result=pstmt.executeQuery();
        while(result.next())
        {
            customer.setName(result.getString("CUSTOMERNAME"));
            customer.setMobileNumber(result.getString("MOBILENUMBER"));
            customer.setEmailId(result.getString("EMAILID"));
            customer.setAddress(result.getString("ADDRESS"));
            customer.setStartingDate(result.getDate("STARTINGDATE"));
            customer.setExpirationDate(result.getDate("EXPIRATIONDATE"));
            customer.setBranch(result.getString("BRANCH"));
            customer.setMembershipPlan(result.getString("MEMBERSHIPPLAN"));
            customer.setGender(result.getString("GENDER"));
            customer.setDateOfBirth(result.getDate("DATEOFBIRTH"));
        }
        return customer;
    }
}
