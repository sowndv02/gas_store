/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;

import java.util.List;


/**
 *
 * @author ADMIN
 */
public class Role {
    private int roleId;
    private String code, name, description;
    private List<Administrator> admin;

    public Role() {
    }

    public Role(int roleId, String code, String name) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
    }
    

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Administrator> getAdmin() {
        return admin;
    }

    public void setAdmin(List<Administrator> admin) {
        this.admin = admin;
    }

    
    
    
}
