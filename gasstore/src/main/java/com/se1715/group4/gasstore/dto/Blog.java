/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
public class Blog {
    private int blogId;
    private String code;
    private int adminId;
    private int typeBlogId;
    private String datePost;
    private String lastChange;
    private boolean status;
    private String title;
    private String content;
    private String description;
    private String base64Image;

    public Blog(int blogId, String code, int adminId, int typeBlogId, String datePost, String lastChange, boolean status, String title, String content, String base64Image, String description) {
        this.blogId = blogId;
        this.code = code;
        this.adminId = adminId;
        this.typeBlogId = typeBlogId;
        this.datePost = datePost;
        this.lastChange = lastChange;
        this.status = status;
        this.title = title;
        this.content = content;
        this.description = description;
        this.base64Image = base64Image;
    }

    

    public Blog() {
    }

    public int getBlogId() {
        return blogId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getTypeBlogId() {
        return typeBlogId;
    }

    public void setTypeBlogId(int typeBlogId) {
        this.typeBlogId = typeBlogId;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

}
