
package com.persistence;

import com.db.DatabaseConnection;
import com.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PersistTransaction {
    public static void store(Transaction transaction) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="INSERT INTO TRANSACTION(BRANCH,USERSNAME,PRN,CUSTOMERNAME,MOBILENUMBER,MEMBERSHIPPLAN,STARTINGDATE,EXPIRATIONDATE,AMOUNT,SGST,CGST,TOTALAMOUNT,PAYMENTMODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, transaction.getBranch());
        pstmt.setString(2, transaction.getUsersname());
        pstmt.setInt(3, transaction.getPrn());
        pstmt.setString(4, transaction.getCustomerName());
        pstmt.setString(5, transaction.getMobileNumber());
        pstmt.setString(6, transaction.getMembershipPlan());
        pstmt.setDate(7, transaction.getStartingDate());
        pstmt.setDate(8, transaction.getExpirationDate());
        pstmt.setDouble(9, transaction.getAmount());
        pstmt.setDouble(10, transaction.getSgst());
        pstmt.setDouble(11, transaction.getCgst());
        pstmt.setDouble(12, transaction.getTotalAmount());
        pstmt.setString(13, transaction.getPaymentMode());
        pstmt.executeUpdate();
    }
    
    public static ObservableList<Transaction> searchByName(String name) throws SQLException
    {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
         Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM TRANSACTION WHERE CUSTOMERNAME LIKE ?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, name);
        ResultSet result = pstmt.executeQuery();
        while(result.next())
        {
            list.add(new Transaction(result.getInt("INVOICENUMBER"), result.getTimestamp("INVOICEDATE"), result.getString("BRANCH"), result.getString("USERSNAME"), result.getInt("PRN"), result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("MEMBERSHIPPLAN"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"), result.getDouble("AMOUNT"), result.getDouble("SGST"), result.getDouble("CGST"), result.getDouble("TOTALAMOUNT"), result.getString("PAYMENTMODE")));
        }
        return list;
    }
    
    public static ObservableList<Transaction> retrieve(String prn) throws SQLException
    {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
         Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM TRANSACTION WHERE PRN=?";
        PreparedStatement pstmt=conn.prepareStatement(query);
        pstmt.setString(1, prn);
        ResultSet result = pstmt.executeQuery();
        while(result.next())
        {
            list.add(new Transaction(result.getInt("INVOICENUMBER"), result.getTimestamp("INVOICEDATE"), result.getString("BRANCH"), result.getString("USERSNAME"), result.getInt("PRN"), result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("MEMBERSHIPPLAN"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"), result.getDouble("AMOUNT"), result.getDouble("SGST"), result.getDouble("CGST"), result.getDouble("TOTALAMOUNT"), result.getString("PAYMENTMODE")));
        }
        return list;
    }
    
    public static ObservableList<Transaction> retrieveAll() throws SQLException
    {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
         Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM TRANSACTION";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while(result.next())
        {
            list.add(new Transaction(result.getInt("INVOICENUMBER"), result.getTimestamp("INVOICEDATE"), result.getString("BRANCH"), result.getString("USERSNAME"), result.getInt("PRN"), result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("MEMBERSHIPPLAN"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"), result.getDouble("AMOUNT"), result.getDouble("SGST"), result.getDouble("CGST"), result.getDouble("TOTALAMOUNT"), result.getString("PAYMENTMODE")));
        }
        return list;
    }
    
    public static Transaction retrieveLatest() throws SQLException
    {
        Transaction transaction = null;
        Connection conn=DatabaseConnection.getConnection();
        String query="SELECT * FROM TRANSACTION WHERE INVOICENUMBER = (SELECT MAX(INVOICENUMBER) FROM TRANSACTION)";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while(result.next())
        {
            transaction = new Transaction(result.getInt("INVOICENUMBER"), result.getTimestamp("INVOICEDATE"), result.getString("BRANCH"), result.getString("USERSNAME"), result.getInt("PRN"), result.getString("CUSTOMERNAME"), result.getString("MOBILENUMBER"), result.getString("MEMBERSHIPPLAN"), result.getDate("STARTINGDATE"), result.getDate("EXPIRATIONDATE"), result.getDouble("AMOUNT"), result.getDouble("SGST"), result.getDouble("CGST"), result.getDouble("TOTALAMOUNT"), result.getString("PAYMENTMODE"));
        }
        return transaction;
    }
}
