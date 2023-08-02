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
public class Product {

    private int productId;
    private String code;
    private String name;
    private String keyword, shortDescription, description;
    private int categoryId;
    private int supplierId;
    private boolean isActive;
    private double unitPrice;
    private String base64Image;
    private int stockQuantity, unitOnOrders;
    private String createdDate;
    private int createdBy, discountId;
    private Discount discount;
    private Vector<Review> reviews;
    private int warranty;
    private Category category;
    private Administrator admin;
    private Supplier supplier;

    public Product() {
    }

    public Product(int productId, String code,String name, boolean isActive, double unitPrice, int stockQuantity, int unitOnOrders, Discount discount, int warranty, Category category, Supplier supplier) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.unitOnOrders = unitOnOrders;
        this.discount = discount;
        this.warranty = warranty;
        this.category = category;
        this.supplier = supplier;
    }
    
    

    public Product(int productId, String code,String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, boolean isActive, double unitPrice, String base64Image, int stockQuantity, int discountId, int warranty, String createdDate) {
        this.productId = productId;
        this.name = name;
        this.code = code;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.discountId = discountId;
        this.warranty = warranty;
        this.createdDate = createdDate;
    }
    
    public Product(String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, boolean isActive, double unitPrice, String base64Image, int stockQuantity, int discountId, int warranty) {
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.discountId = discountId;
        this.warranty = warranty;
    }
    
    public Product(int productId, String name, double unitPrice, String base64Image) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
    }

    public Product(int productId, String code, String name, double unitPrice, String base64Image, Discount discount) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.discount = discount;
    }

    public Product(int productId, String code) {
        this.productId = productId;
        this.code = code;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, double unitPrice, String base64Image, int stockQuantity, int discountId) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.discountId = discountId;
    }

    public Product(int productId, String name, String keyword, String shortDescription, String description, double unitPrice, String base64Image, int stockQuantity, Discount discount, int warranty, Category category, Supplier supplier) {
        this.productId = productId;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.discount = discount;
        this.warranty = warranty;
        this.category = category;
        this.supplier = supplier;
    }

    public Product(int productId, double unitPrice) {
        this.productId = productId;
        this.unitPrice = unitPrice;
    }

    public Product(int productId, String code, String name, double unitPrice, String base64Image, Discount discount, Category category) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.discount = discount;
        this.category = category;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, boolean isActive, double unitPrice, String base64Image, int stockQuantity, Discount discount, int warranty, Category category, Supplier supplier) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.discount = discount;
        this.warranty = warranty;
        this.category = category;
        this.supplier = supplier;
    }

    public Product(int productId, String code, String name, Vector<Review> reviews) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.reviews = reviews;
    }

    public Product(int productId, String name, Category category, Supplier supplier) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product(int productId, String code,String name, double unitPrice, int stockQuantity, int unitOnOrders, Category category) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.unitOnOrders = unitOnOrders;
        this.category = category;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, boolean isActive, double unitPrice, String base64Image, int stockQuantity, int unitOnOrders, String createdDate, int createdBy, int discountId, Discount discount, Vector<Review> reviews, int warranty, Category category, Administrator admin) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.unitOnOrders = unitOnOrders;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.discountId = discountId;
        this.discount = discount;
        this.reviews = reviews;
        this.warranty = warranty;
        this.category = category;
        this.admin = admin;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, boolean isActive, double unitPrice, int stockQuantity, int unitOnOrders, String createdDate, int createdBy, int discountId, Discount discount, Vector<Review> reviews, int warranty, Category category, Administrator admin) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.unitOnOrders = unitOnOrders;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.discountId = discountId;
        this.discount = discount;
        this.reviews = reviews;
        this.warranty = warranty;
        this.category = category;
        this.admin = admin;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, boolean isActive, double unitPrice, String base64Image, int stockQuantity, int unitOnOrders, String createdDate, int createdBy, int discountId) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.unitOnOrders = unitOnOrders;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.discountId = discountId;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, int categoryId, int supplierId, boolean isActive, double unitPrice, int stockQuantity, int unitOnOrders, String createdDate, int createdBy, int discountId) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.unitOnOrders = unitOnOrders;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.discountId = discountId;
    }

    public Product(int productId, String name, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }
    
    

    public int getProductId() {
        return productId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getUnitOnOrders() {
        return unitOnOrders;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getDiscountId() {
        return discountId;
    }

    public Discount getDiscount() {
        return discount;
    }

    public Vector<Review> getReviews() {
        return reviews;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public Category getCategory() {
        return category;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setUnitOnOrders(int unitOnOrders) {
        this.unitOnOrders = unitOnOrders;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public Product(int productId, String code, String name, String keyword, String shortDescription, String description, boolean isActive, double unitPrice, String base64Image, int stockQuantity, Category category, Supplier supplier, int warranty, Discount discount, String createdDate) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.keyword = keyword;
        this.shortDescription = shortDescription;
        this.description = description;
        this.isActive = isActive;
        this.unitPrice = unitPrice;
        this.base64Image = base64Image;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.supplier = supplier;
        this.warranty = warranty;
        this.discount = discount;
        this.createdDate = createdDate;
    }

    public void setReviews(Vector<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", code=" + code + ", name=" + name + ", keyword=" + keyword + ", shortDescription=" + shortDescription + ", description=" + description + ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", isActive=" + isActive + ", unitPrice=" + unitPrice + ", base64Image=" + base64Image + ", stockQuantity=" + stockQuantity + ", unitOnOrders=" + unitOnOrders + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", discountId=" + discountId + ", discount=" + discount + ", reviews=" + reviews + ", warranty=" + warranty + ", category=" + category + ", admin=" + admin + ", supplier=" + supplier + '}';
    }

}
