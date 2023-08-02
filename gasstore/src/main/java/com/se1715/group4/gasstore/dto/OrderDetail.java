/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 *
 * @author ADMIN
 */
public class OrderDetail {
    private int orderId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double discount;
    private Order order;
    private Product product;
    private int warranty;
    private String warrantyDetail;
    private int warrantActive;
    public OrderDetail() {
    }
    
    public OrderDetail(int orderId, int quantity, double unitPrice, double discount, Product product, int warranty) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.product = product;
        this.warranty = warranty;
    }

    public OrderDetail(int orderID, int quantity, double unitPrice, double discount, Product product, int warranty, String warrantyDetail) {
        this.orderId = orderID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.product = product;
        this.warranty = warranty;
        this.warrantyDetail = warrantyDetail;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getWarrantyDetail() {
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        DateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dateTime = inputDateFormat.parse(warrantyDetail);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTime);
            cal.add(Calendar.MONTH, warranty);
            warrantyDetail = outputDateFormat.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warrantyDetail;
    }

    public int getWarrantActive() {
        warrantActive=1;
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        try {
            Date dateTime = inputDateFormat.parse(warrantyDetail);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTime);
            cal.add(Calendar.MONTH, warranty);
            if(cal.getTime().before(Date.from(Instant.now()))) {
                warrantActive=0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warrantActive;
    }
    

    public OrderDetail(int orderId, int quantity, double unitPrice, double discount, Product product) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
}
