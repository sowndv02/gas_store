/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

/**
 *
 * @author CC
 */
public class WarrantyWarning {
    private int isSend;
    private String dueDate;
    private int orderID;
    private int productID;
    private Product product;
    private Order order;

    public WarrantyWarning(int isSend, String dueDate, int orderID, int productID) {
        this.isSend = isSend;
        this.dueDate = dueDate;
        this.orderID = orderID;
        this.productID = productID;
    }

    public WarrantyWarning(int isSend, String dueDate, int orderID, int productID, Product product, Order order) {
        this.isSend = isSend;
        this.dueDate = dueDate;
        this.orderID = orderID;
        this.productID = productID;
        this.product = product;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    
}
