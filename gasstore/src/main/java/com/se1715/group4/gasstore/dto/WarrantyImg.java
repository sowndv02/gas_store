/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

/**
 *
 * @author ADMIN
 */
public class WarrantyImg {
    private int warrantyImgId, warrantyId;
    private String base64Image;

    public WarrantyImg(int warrantyImgId, int warrantyId, String base64Image) {
        this.warrantyImgId = warrantyImgId;
        this.warrantyId = warrantyId;
        this.base64Image = base64Image;
    }
    
    public int getWarrantyImgId() {
        return warrantyImgId;
    }

    public void setWarrantyImgId(int warrantyImgId) {
        this.warrantyImgId = warrantyImgId;
    }

    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    
    
}
