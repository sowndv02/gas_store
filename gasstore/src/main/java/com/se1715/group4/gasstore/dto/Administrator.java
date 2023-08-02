/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dto;


/**
 *
 * @author ADMIN
 */
public class Administrator {
    
    private int administratorId;
    private String userName, password, lastLogin, email;
    private boolean isActive;
    private int roleId;
    private Role role;
    private String base64Image;

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    

    public Administrator() {
    }

    public Administrator(int administratorId, String userName, String password, String lastLogin, String email, boolean isActive, int roleId, String base64Image) {
        this.administratorId = administratorId;
        this.userName = userName;
        this.password = password;
        this.lastLogin = lastLogin;
        this.email = email;
        this.isActive = isActive;
        this.roleId = roleId;
        this.base64Image = base64Image;
    }
    
    

    public Administrator(int administratorId, String userName, String password, String lastLogin, String email, boolean isActive, int roleId, Role role) {
        this.administratorId = administratorId;
        this.userName = userName;
        this.password = password;
        this.lastLogin = lastLogin;
        this.email = email;
        this.isActive = isActive;
        this.roleId = roleId;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Administrator(int administratorId, String userName, String password, String lastLogin, String email, boolean isActive, int roleId) {
        this.administratorId = administratorId;
        this.userName = userName;
        this.password = password;
        this.lastLogin = lastLogin;
        this.email = email;
        this.isActive = isActive;
        this.roleId = roleId;
    }
    
    

    
    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Administrator{" + "administratorId=" + administratorId + ", userName=" + userName + ", password=" + password + ", lastLogin=" + lastLogin + ", email=" + email + ", isActive=" + isActive + ", roleId=" + roleId + ", role=" + role + ", base64Image=" + base64Image + '}';
    }
    
     
    
    
}
