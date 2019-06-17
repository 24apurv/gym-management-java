
package com.model;

import java.sql.Date;
import java.sql.Timestamp;


public class Transaction {
    private Integer invoiceNumber;
    private Timestamp invoiceDate;
    private String branch;
    private String usersname;
    private Integer prn;
    private String customerName;
    private String mobileNumber;
    private String membershipPlan;
    private Date startingDate;
    private Date expirationDate;
    private Double amount;
    private Double sgst;
    private Double cgst;
    private Double totalAmount;
    private String paymentMode;

    public Transaction(Integer invoiceNumber, Timestamp invoiceDate, String branch, String usersname, Integer prn, String customerName, String mobileNumber, String membershipPlan, Date startingDate, Date expirationDate, Double amount, Double sgst, Double cgst, Double totalAmount, String paymentMode) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.branch = branch;
        this.usersname = usersname;
        this.prn = prn;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.membershipPlan = membershipPlan;
        this.startingDate = startingDate;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.sgst = sgst;
        this.cgst = cgst;
        this.totalAmount = totalAmount;
        this.paymentMode = paymentMode;
    }

    public Transaction() {
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Timestamp getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Timestamp invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }

    public Integer getPrn() {
        return prn;
    }

    public void setPrn(Integer prn) {
        this.prn = prn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getSgst() {
        return sgst;
    }

    public void setSgst(Double sgst) {
        this.sgst = sgst;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

   
    
}
