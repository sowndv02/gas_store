/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import java.util.List;


/**
 *
 * @author ADMIN
 */
public class Shipments {
    private int shipmentId;
    private String companyName, email, phone;
    private boolean status;
    private String createdDate;
    private int createdBy;
    private List<Order> orders;
    private Administrator admin;

    public Shipments(String companyName, String email, String phone, boolean status, Administrator admin) {
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.admin = admin;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
    
    
    public Shipments(int shipmentId, String companyName) {
        this.shipmentId = shipmentId;
        this.companyName = companyName;
    }

    public Shipments(int shipmentId, String companyName, String email, String phone, boolean status) {
        this.shipmentId = shipmentId;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }
    

    public Shipments(int shipmentId, String companyName, String email, String phone, boolean status, String createdDate, Administrator admin) {
        this.shipmentId = shipmentId;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createdDate = createdDate;
        this.admin = admin;
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
    
    
    public Shipments(int shipmentId, String companyName, String email, String phone, boolean status, String createdDate) {
        this.shipmentId = shipmentId;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Shipments() {
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreated() {
        return createdDate;
    }

    public void setCreated(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Shipments{" + "shipmentId=" + shipmentId + ", companyName=" + companyName + ", email=" + email + ", phone=" + phone + ", status=" + status + ", createdDate=" + createdDate + ", orders=" + orders + '}';
    }
    
}
