/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author ADMIN
 */
public class Feedback {
    private int feedbackId;
    private String name;
    private String subject;
    private String email;
    private String content, reply, repDate, sendDate;
    private boolean status;
    private int role;
    private Customer customer;
    private Shipments shipment;
    private Supplier supplier;

    public Feedback(int feedbackId, String name, String subject, String email, String content, String reply, String repDate, String sendDate, boolean status, int role) {
        this.feedbackId = feedbackId;
        this.name = name;
        this.subject = subject;
        this.email = email;
        this.content = content;
        this.reply = reply;
        this.repDate = repDate;
        this.sendDate = sendDate;
        this.status = status;
        this.role = role;
    }

    
    
    public Feedback(int feedbackId, String email, String content, String reply, String repDate, String sendDate, boolean status, Customer customer) {
        this.feedbackId = feedbackId;
        this.email = email;
        this.content = content;
        this.reply = reply;
        this.repDate = repDate;
        this.sendDate = sendDate;
        this.status = status;
        this.customer = customer;
    }

    public Feedback(int feedbackId, String email, String content, String reply, String repDate, String sendDate, boolean status, Supplier supplier) {
        this.feedbackId = feedbackId;
        this.email = email;
        this.content = content;
        this.reply = reply;
        this.repDate = repDate;
        this.sendDate = sendDate;
        this.status = status;
        this.supplier = supplier;
    }

    public Feedback(String name, String subject, String email, String content, int role) {
        this.name = name;
        this.subject = subject;
        this.email = email;
        this.content = content;
        this.role = role;
    }
    
    public Feedback(int feedbackId, String email, String content, String reply, String repDate, String sendDate, boolean status, Shipments shipment) {
        this.feedbackId = feedbackId;
        this.email = email;
        this.content = content;
        this.reply = reply;
        this.repDate = repDate;
        this.sendDate = sendDate;
        this.status = status;
        this.shipment = shipment;
    }

    public Feedback(int feedbackId, String email, String content, String reply, String repDate, String sendDate, boolean status) {
        this.feedbackId = feedbackId;
        this.email = email;
        this.content = content;
        this.reply = reply;
        this.repDate = repDate;
        this.sendDate = sendDate;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shipments getShipment() {
        return shipment;
    }

    public void setShipment(Shipments shipment) {
        this.shipment = shipment;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Feedback() {
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getRepDate() {
        return repDate;
    }

    public void setRepDate(String repDate) {
        
        this.repDate = formatDate(repDate);
    }

    public String getSendDate() {
        sendDate = formatDate(sendDate);
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = formatDate(sendDate);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String formatDate(String date){
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

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", email=" + email + ", content=" + content + ", reply=" + reply + ", repDate=" + repDate + ", sendDate=" + sendDate + ", status=" + status + ", role=" + role + ", customer=" + customer + ", shipment=" + shipment + ", supplier=" + supplier + '}';
    }
    
    
}
