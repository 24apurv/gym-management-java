
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


public class PersistCurrentTransaction {
    public static void store(Transaction transaction) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();
        String query="INSERT INTO CURRENTTRANSACTION(BRANCH,USERSNAME,PRN,CUSTOMERNAME,MOBILENUMBER,MEMBERSHIPPLAN,STARTINGDATE,EXPIRATIONDATE,AMOUNT,SGST,CGST,TOTALAMOUNT,PAYMENTMODE,INVOICENUMBER,INVOICEDATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        pstmt.setInt(14, transaction.getInvoiceNumber());
        pstmt.setTimestamp(15, transaction.getInvoiceDate());
        pstmt.executeUpdate();
    }
    
    public static void remove() throws SQLException
    {
        ObservableList<Transaction> list = FXCollections.observableArrayList();
         Connection conn=DatabaseConnection.getConnection();
        String query="DELETE FROM CURRENTTRANSACTION";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
}
