/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
public class Warranty {
    private int warrantyId;
    private int productId;
    private int customerId, administatorId, status;
    private String createdDate, appointmentDate, finishDay;
    private String productName;
    private int usingDate;
    private Product product;
    private Customer customer;
    private Administrator admin;
    private String note, description;
    private WarrantyPolicy warrantyPolicy;
    private int orderId, method, number, currentMonth;
    

    public Warranty(int warrantyId, int status, String createdDate, String appointmentDate, String finishDay, Product product, Customer customer, Administrator admin, String note, String description, int method) {
        this.warrantyId = warrantyId;
        this.status = status;
        this.createdDate = createdDate;
        this.appointmentDate = appointmentDate;
        this.finishDay = finishDay;
        this.product = product;
        this.customer = customer;
        this.method = method;
        this.admin = admin;
        this.note = note;
        this.description = description;
    }

    public Warranty(int warrantyId, String appointmentDate, Administrator admin, String note, String description, int method) {
        this.warrantyId = warrantyId;
        this.appointmentDate = appointmentDate;
        this.admin = admin;
        this.note = note;
        this.description = description;
        this.method = method;
    }

    public Warranty() {
    }

    public Warranty(int warrantyId, int productId, String description, Product product, WarrantyPolicy warrantyPolicy) {
        this.warrantyId = warrantyId;
        this.productId = productId;
        this.description = description;
        this.product = product;
        this.warrantyPolicy = warrantyPolicy;
    }

    public Warranty(Product product, int number, int currentMonth) {
        this.product = product;
        this.number = number;
        this.currentMonth = currentMonth;
    }
    
    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public WarrantyPolicy getWarrantyPolicy() {
        return warrantyPolicy;
    }

    public void setWarrantyPolicy(WarrantyPolicy warrantyPolicy) {
        this.warrantyPolicy = warrantyPolicy;
    }

    
    
    public String getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(String finishDay) {
        this.finishDay = finishDay;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(int usingDate) {
        this.usingDate = usingDate;
    }


    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
    
    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAdministatorId() {
        return administatorId;
    }

    public void setAdministatorId(int administatorId) {
        this.administatorId = administatorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Warranty{" + "warrantyId=" + warrantyId + ", productId=" + productId + ", customerId=" + customerId + ", administatorId=" + administatorId + ", status=" + status + ", createdDate=" + createdDate + ", appointmentDate=" + appointmentDate + ", finishDay=" + finishDay + ", product=" + product + ", customer=" + customer + ", admin=" + admin + ", note=" + note + ", description=" + description + ", warrantyPolicy=" + warrantyPolicy + '}';
    }
    
    
    
    
}
