
package com.model;

import java.sql.Date;


public class Customer {
    private String name;
    private String mobileNumber;
    private String emailId;
    private String address;
    private Date startingDate;
    private Date expirationDate;
    private String branch;
    private String membershipPlan;
    private String gender;
    private Date dateOfBirth;
    private Integer prn;

    public Customer(String name, String mobileNumber, String emailId, String address, Date startingDate, Date expirationDate, String branch, String membershipPlan, String gender, Date dateOfBirth, Integer prn) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.address = address;
        this.startingDate = startingDate;
        this.expirationDate = expirationDate;
        this.branch = branch;
        this.membershipPlan = membershipPlan;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.prn = prn;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(String membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPrn() {
        return prn;
    }

    public void setPrn(Integer prn) {
        this.prn = prn;
    }

   
}
