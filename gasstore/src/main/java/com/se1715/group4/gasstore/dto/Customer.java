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
public class Customer {
    private int customerID;
    private String googleId;
    private String userName;
    private String password;
    private String created;
    private String lastLogin;
    private boolean status;
    private boolean gender;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private List<Order> orders;
    private List<Review> reviews;
    private String base64Image;
    private double totalMoney;

    public Customer(int customerID, boolean status, boolean gender, String firstName, String lastName, String address, String phone, String email, double totalMoney) {
        this.customerID = customerID;
        this.status = status;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.totalMoney = totalMoney;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Customer(int customerID, String googleId) {
        this.customerID = customerID;
        this.googleId = googleId;
    }
    
    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Customer(int customerID, boolean status, boolean gender, String firstName, String lastName, String address, String phone, String email) {
        this.customerID = customerID;
        this.status = status;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    
    public Customer() {
    }

    public Customer(int customerID, boolean gender, String firstName, String lastName, String address, String phone, String email) {
        this.customerID = customerID;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    
    public Customer(int customerID, String firstName, String lastName, String base64Image, boolean status, String email, String phone) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.base64Image = base64Image;
        this.status = status;
        this.email = email;
        this.phone = phone;
    }

    public Customer(int customerID) {
        this.customerID = customerID;
    }

    public Customer(int customerID, String created, String lastLogin, boolean status, boolean gender, String firstName, String lastName, String address, String phone, String email, String base64Image) {
        this.customerID = customerID;
        this.created = created;
        this.lastLogin = lastLogin;
        this.status = status;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.base64Image = base64Image;
    }

    public Customer(int customerID, String userName, String password, String created, String lastLogin, boolean status, boolean gender, String firstName, String lastName, String address, String phone, String email, String base64Image) {
        this.customerID = customerID;
        this.userName = userName;
        this.password = password;
        this.created = created;
        this.lastLogin = lastLogin;
        this.status = status;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.base64Image = base64Image;
    }

    public Customer(String userName, String password, String created, String lastLogin, boolean status, boolean gender, String firstName, String lastName, String address, String phone, String email, String base64Image) {
        this.userName = userName;
        this.password = password;
        this.created = created;
        this.lastLogin = lastLogin;
        this.status = status;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.base64Image = base64Image;
    }

    
    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", userName=" + userName + ", password=" + password + ", created=" + created + ", lastLogin=" + lastLogin + ", status=" + status + ", gender=" + gender + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phone=" + phone + ", email=" + email + '}';
    }
    
    
}
