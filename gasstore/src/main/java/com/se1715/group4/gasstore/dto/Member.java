/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

/**
 *
 * @author Admin
 */
public class Member {
    private int memberId;
    private String name;
    private String title;
    private String img;
    private String facebook;
    private String twtter;

    public Member() {
    }

    public Member(int memberId, String name, String title, String img, String facebook, String twtter) {
        this.memberId = memberId;
        this.name = name;
        this.title = title;
        this.img = img;
        this.facebook = facebook;
        this.twtter = twtter;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwtter() {
        return twtter;
    }

    public void setTwtter(String twtter) {
        this.twtter = twtter;
    }
    
}
