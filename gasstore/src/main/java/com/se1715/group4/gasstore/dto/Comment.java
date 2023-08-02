/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
public class Comment {
    private int commentId, blogId;
    private String content, datePost;
    private int customerId;
    private boolean status;
    private Customer customer;

    public Comment() {
    }

    public Comment(int commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
    
    public Comment(int blogId, String content, int customerId) {
        this.blogId = blogId;
        this.content = content;
        this.customerId = customerId;
    }
    
    
    
    public Comment(int commentId, int blogId, String content, String datePost, Customer customer, boolean status) {
        this.commentId = commentId;
        this.blogId = blogId;
        this.content = content;
        this.datePost = datePost;
        this.customer = customer;
        this.status = status;
    }

    public Comment(int commentId, int blogId, String content, String datePost, int customerId, boolean status) {
        this.commentId = commentId;
        this.blogId = blogId;
        this.content = content;
        this.datePost = datePost;
        this.customerId = customerId;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
