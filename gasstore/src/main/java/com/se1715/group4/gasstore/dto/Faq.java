/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

/**
 *
 * @author ADMIN
 */
public class Faq {
    private int FaqID;
    private boolean isActive;
    private String question;
    private String answer;
    private int createdBy;
    Administrator admin;
    private String createdDate, stringId;
    

    public Faq() {
    }

    public Faq(boolean isActive, String question, String answer, int createdBy, String createdDate) {
        this.isActive = isActive;
        this.question = question;
        this.answer = answer;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public Faq(int FaqID, boolean isActive, String question, String answer, int createdBy, String createdDate, String stringId) {
        this.FaqID = FaqID;
        this.isActive = isActive;
        this.question = question;
        this.answer = answer;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    public Faq(int FaqID, boolean isActive, String question, String answer, Administrator admin, String createdDate, String stringId) {
        this.FaqID = FaqID;
        this.isActive = isActive;
        this.question = question;
        this.answer = answer;
        this.admin = admin;
        this.createdDate = createdDate;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    
    public int getFaqID() {
        return FaqID;
    }

    public void setFaqID(int FaqID) {
        this.FaqID = FaqID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "FrequentlyAskedQuestions{" + "FaqID=" + FaqID + ", isActive=" + isActive + ", question=" + question + ", answer=" + answer + ", createdBy=" + createdBy + ", createdDate=" + createdDate + '}';
    }
    
    
}
