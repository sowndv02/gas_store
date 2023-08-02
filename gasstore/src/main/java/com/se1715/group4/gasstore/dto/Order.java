/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author ADMIN
 */
public class Order {
    private int orderId;
    private int customerId;
    private int trackingNumber;
    private double totalMoney;
    private String orderDate, shippedDate, requiredDate, shipAddress;
    private int status;
    private int process;
    private String statusDetail;
    private List<OrderDetail> orderDetails;
    private Customer customer;
    private int shipVia;
    private String notes;
    private Shipments shipment;
    private int payment;
    private String paymentDetail;
    
    
    public Order(int orderId, int trackingNumber, double totalMoney, String orderDate, String shippedDate, String requiredDate, String shipAddress, int status, int payment,int process, Vector<OrderDetail> listOrderdetail, Customer customer, String notes, Shipments shipper) {
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.status = status;
        this.process = process;
        this.orderDetails = listOrderdetail;
        this.customer = customer;
        this.payment = payment;
        this.notes = notes;
        this.shipment = shipper;
    }

        public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getStatusDetail(){
        if(getStatus() == 1 )
            statusDetail = "Thành công";
        if(getStatus() == 0 )
            statusDetail = "Thất bại";
        if(getStatus() == 2 )
            statusDetail = "Đang xử lý";
        if(getStatus() == 3 )
            statusDetail = "Đang giao hàng";
        return statusDetail;
    }

    
    public String getPaymentDetail() {
        if(getPayment()== 1 )
            paymentDetail = "VNpay";
        if(getPayment()== 0 )
            paymentDetail = "Thanh toán khi nhận hàng";
        return paymentDetail;
    }

    
    public String formatDate(String date) {
        String dateTimeString = date;

        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        DateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date dateTime = inputDateFormat.parse(dateTimeString);
            String formattedDate = outputDateFormat.format(dateTime);
            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    
    
    public Order(int orderId, double totalMoney, String orderDate, String shippedDate, String requiredDate, String shipAddress, int status, Shipments shipment) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.status = status;
        this.shipment = shipment;
    }

    public Order(int orderId, double totalMoney, String orderDate, String shippedDate, String requiredDate, String shipAddress, Customer customer, Shipments shipment) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.customer = customer;
        this.shipment = shipment;
    }

    public Order(int orderId, int trackingNumber, double totalMoney, String orderDate, String shippedDate, String requiredDate, String shipAddress, int status, List<OrderDetail> orderDetails, Customer customer, String notes, Shipments shipment) {
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.status = status;
        this.orderDetails = orderDetails;
        this.customer = customer;
        this.notes = notes;
        this.shipment = shipment;
    }

    
    
    public Order() {
    }
    
    public Order(int orderId, double totalMoney, String orderDate, String shippedDate, String requiredDate, String shipAddress, int status, Customer customer, Shipments shipment) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.status = status;
        this.customer = customer;
        this.shipment = shipment;
    }
    
    public Order(int orderId, double totalMoney, String orderDate, String shippedDate, String requiredDate, String shipAddress, int status, Customer customer) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.status = status;
        this.customer = customer;
    }
    

    public Order(int orderId, double totalMoney, int status,String orderDate, String requiredDate, String shipAddress, Customer customer, String notes, Shipments shipment, int trackingNumber, int payment) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shipAddress = shipAddress;
        this.customer = customer;
        this.notes = notes;
        this.shipment = shipment;
        this.trackingNumber = trackingNumber;
        this.payment = payment;
        this.status = status;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(int trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getShipVia() {
        return shipVia;
    }

    public void setShipVia(int shipVia) {
        this.shipVia = shipVia;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Shipments getShipment() {
        return shipment;
    }

    public void setShipment(Shipments shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerId=" + customerId + ", trackingNumber=" + trackingNumber + ", totalMoney=" + totalMoney + ", orderDate=" + orderDate + ", shippedDate=" + shippedDate + ", requiredDate=" + requiredDate + ", shipAddress=" + shipAddress + ", status=" + status + ", orderDetails=" + orderDetails + ", customer=" + customer + ", shipVia=" + shipVia + ", notes=" + notes + ", shipment=" + shipment + '}';
    }
    
    
}
