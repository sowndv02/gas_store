/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import java.util.List;
import java.util.Vector;


/**
 *
 * @author ADMIN
 */
public class Category {
    private int categoryId;
    private String code;
    private String name;
    private String keyword;
    private String description;
    private Vector<Product> products;
    private int number;
    private String createdDate;
    private int createdBy;
    private Administrator admin;
    
    

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

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
     

    public Category(String code, String name, String keyword) {
        this.code = code;
        this.name = name;
        this.keyword = keyword;
    }
    
    public Category(String code, String name, String keyword, String description) {
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.description = description;
    }

    public Category(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category(int categoryId, String name, int number) {
        this.categoryId = categoryId;
        this.name = name;
        this.number = number;
    }

    public Category(int categoryId, String code, String name) {
        this.categoryId = categoryId;
        this.code = code;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void setProducts(Vector<Product> products) {
        this.products = products;
    }

    public Category(int categoryId, String code, String name, String keyword, String description, String createdDate, Administrator admin) {
        this.categoryId = categoryId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.description = description;
        this.createdDate = createdDate;
        this.admin = admin;
    }
    
    public Category(int categoryId, String code, String name, String keyword, String description, String createdDate, Administrator admin, Vector<Product> products) {
        this.categoryId = categoryId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.description = description;
        this.createdDate = createdDate;
        this.admin = admin;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", code=" + code + ", name=" + name + ", keyword=" + keyword + ", description=" + description + '}';
    }
    
}
