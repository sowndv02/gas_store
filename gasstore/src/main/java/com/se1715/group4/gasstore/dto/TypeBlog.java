/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
public class TypeBlog {
    private int typeId;
    private String code,typeName;
    private String createdDate;
    int createdBy;

    public TypeBlog(int typeId, String code, String typeName, String createdDate, int createdBy) {
        this.typeId = typeId;
        this.code = code;
        this.typeName = typeName;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }
    public TypeBlog(int typeId,  String typeName, String createdDate, int createdBy) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
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


    public TypeBlog() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
}


