/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
public class Discount {

    private int discountId;
    private String name, description;
    private boolean isActive;
    private double discount;
    private String couponCode, startDate, expirationDate;
    private String DateTimeNow;

    public Discount() {
    }

    public Discount(int discountId) {
        this.discountId = discountId;
    }

    public Discount(int discountId, double discount) {
        this.discountId = discountId;
        this.discount = discount;
    }

    public Discount(int discountId, String name, boolean isActive, double discount) {
        this.discountId = discountId;
        this.name = name;
        this.isActive = isActive;
        this.discount = discount;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDateTimeNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SS dd-MM-yyyy");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public void setDateTimeNow(String DateTimeNow) {
        this.DateTimeNow = DateTimeNow;
    }

    public Discount(int discountId, String name, String description, boolean isActive, double discount, String couponCode, String startDate, String expirationDate) {
        this.discountId = discountId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.discount = discount;
        this.couponCode = couponCode;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public Discount(int discountId, String name, String description, boolean isActive, double discount, String couponCode, String startDate, String expirationDate, String createdDate, Administrator admin) {
        this.discountId = discountId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.discount = discount;
        this.couponCode = couponCode;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public Discount(String name, String description, boolean isActive, double discount, String couponCode, String startDate, String expirationDate, String createdDate) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.discount = discount;
        this.couponCode = couponCode;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public Discount(int discountId, String name, String description, boolean isActive, double discount, String couponCode, String startDate, String expirationDate, String DateTimeNow) {
        this.discountId = discountId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.discount = discount;
        this.couponCode = couponCode;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.DateTimeNow = DateTimeNow;
    }

    @Override
    public String toString() {
        return "Discount{" + "discountId=" + discountId + ", name=" + name + ", description=" + description + ", isActive=" + isActive + ", discount=" + discount + ", couponCode=" + couponCode + ", startDate=" + startDate + ", expirationDate=" + expirationDate + ", DateTimeNow=" + DateTimeNow + '}';
    }

    
}
