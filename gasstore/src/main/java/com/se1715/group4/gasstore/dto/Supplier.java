/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
public class Supplier {
    private int supplierId;
    private String companyName;
    private boolean status;
    private String createdDate;
    private int createdBy;
    private String email, phone, homePage;
    private int number;
    private Administrator admin;

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public Supplier(String companyName, boolean status, String email, String phone, String homePage, Administrator admin) {
        this.companyName = companyName;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.homePage = homePage;
        this.admin = admin;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Supplier(int supplierId, String companyName, int number) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.number = number;
    }

    
    public Supplier() {
    }

    public Supplier(int supplierId, String companyName, boolean status, String createdDate, int createdBy, String email, String phone, String homePage, Administrator admin) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.email = email;
        this.phone = phone;
        this.homePage = homePage;
        this.admin = admin;
    }

    public Supplier(int supplierId, String companyName, boolean status, String email, String phone, String homePage) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.homePage = homePage;
    }
    
    public Supplier(int supplierId, String companyName, boolean status, String createdDate, int createdBy, String email, String phone, String homePage) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.email = email;
        this.phone = phone;
        this.homePage = homePage;
    }
    
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", companyName=" + companyName + ", status=" + status + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", email=" + email + ", phone=" + phone + ", homePage=" + homePage + ", number=" + number + ", admin=" + admin + '}';
    }
    
    
    
}
