/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import com.google.gson.annotations.SerializedName;


/**
 *
 * @author ADMIN
 */
public class Review {
    private int reviewId;
    private int customerId;
    private int productId;
    @SerializedName("rate")
    private int rate;
    @SerializedName("content")
    private String content;
    private String dateRate;
    private boolean status;
    private Customer customer;
    private Product product;

    public Review(int reviewId, int customerId, int productId, int rate, String content, String dateRate, boolean status, Customer customer, Product product) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.productId = productId;
        this.rate = rate;
        this.content = content;
        this.dateRate = dateRate;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }

    public Review(int reviewId, int rate, String content, String dateRate, boolean status, Customer customer) {
        this.reviewId = reviewId;
        this.rate = rate;
        this.content = content;
        this.dateRate = dateRate;
        this.status = status;
        this.customer = customer;
    }

    public Review(int reviewId, int rate, String content, String dateRate, boolean status, Customer customer, Product product) {
        this.reviewId = reviewId;
        this.rate = rate;
        this.content = content;
        this.dateRate = dateRate;
        this.status = status;
        this.customer = customer;
        this.product = product;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Review(int reviewId, int rate, String content, String dateRate, Customer customer) {
        this.reviewId = reviewId;
        this.rate = rate;
        this.content = content;
        this.dateRate = dateRate;
        this.customer = customer;
    }

    public Review(int customerId, int productId, int rate, String content) {
        this.customerId = customerId;
        this.productId = productId;
        this.rate = rate;
        this.content = content;
    }

    
    public Review() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDateRate() {
        return dateRate;
    }

    public void setDateRate(String dateRate) {
        this.dateRate = dateRate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewId=" + reviewId + ", customerId=" + customerId + ", productId=" + productId + ", rate=" + rate + ", content=" + content + ", dateRate=" + dateRate + ", status=" + status + ", customer=" + customer + ", product=" + product + '}';
    }

    
    
    
    
}
