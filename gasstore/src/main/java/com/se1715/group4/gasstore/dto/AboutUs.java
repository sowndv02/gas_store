/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

/**
 *
 * @author Admin
 */
public class AboutUs {
    private int aboutUsId;
    private String content;
    private String img;

    public AboutUs() {
    }

    public AboutUs(int aboutUsId, String content, String img) {
        this.aboutUsId = aboutUsId;
        this.content = content;
        this.img = img;
    }

    public int getAboutUsId() {
        return aboutUsId;
    }

    public void setAboutUsId(int aboutUsId) {
        this.aboutUsId = aboutUsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
