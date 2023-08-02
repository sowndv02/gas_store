/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

/**
 *
 * @author CC
 */
public class WarrantyPolicy {
    private int warrantyPolicyID;
    private String policy1, policy2, policy3;

    public WarrantyPolicy() {
    }

    public WarrantyPolicy(int warrantyPolicyID, String policy1, String policy2, String policy3) {
        this.warrantyPolicyID = warrantyPolicyID;
        this.policy1 = policy1;
        this.policy2 = policy2;
        this.policy3 = policy3;
    }

    public int getWarrantyPolicyID() {
        return warrantyPolicyID;
    }

    public void setWarrantyPolicyID(int warrantyPolicyID) {
        this.warrantyPolicyID = warrantyPolicyID;
    }

    public String getPolicy1() {
        return policy1;
    }

    public void setPolicy1(String policy1) {
        this.policy1 = policy1;
    }

    public String getPolicy2() {
        return policy2;
    }

    public void setPolicy2(String policy2) {
        this.policy2 = policy2;
    }

    public String getPolicy3() {
        return policy3;
    }

    public void setPolicy3(String policy3) {
        this.policy3 = policy3;
    }
    
    
}
