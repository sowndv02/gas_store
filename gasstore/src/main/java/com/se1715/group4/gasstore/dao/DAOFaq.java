/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Faq;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOFaq extends DBUtil {

    private final Connection connection = DBUtil.makeConnection();

//    public Map<Integer, Integer> mapFaqNumber() {
//        Map<Integer, Integer> mapFaqNumber = new HashMap<>();
//        String sql = "select blogId, count(*) from Faq group by blogId";
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                int blogId = rs.getInt(1);
//                int number = rs.getInt(2);
//                mapFaqNumber.put(blogId, number);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return mapFaqNumber;
//    }
    public Vector<Faq> getAllFaq(String status) {
        Vector<Faq> faq = new Vector<>();
        DAOAdministrator daoAdmin = new DAOAdministrator();
        String sql = "SELECT * FROM Faq ";
        if(!status.isEmpty()) sql += status;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int FaqID = rs.getInt("FaqID");
                Boolean isActive = rs.getBoolean("isActive");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                int createdBy = rs.getInt("createdBy");
                String createdDate = rs.getString("createdDate");
                Administrator admin = daoAdmin.getAdminById(createdBy);
                String stringId = convertToEnglish(FaqID);
                faq.add(new Faq(FaqID, isActive, question, answer, admin, createdDate, stringId));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return faq;
    }
    
    private static final String[] ones = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public static String convertToEnglish(int number) {
        if (number == 0) {
            return "Zero";
        }

        if (number < 0) {
            return "Minus " + convertToEnglish(-number);
        }

        if (number < 20) {
            return ones[number];
        }

        if (number < 100) {
            return tens[number / 10] + " " + convertToEnglish(number % 10);
        }

        if (number < 1000) {
            return ones[number / 100] + " Hundred " + convertToEnglish(number % 100);
        }

        if (number < 1000000) {
            return convertToEnglish(number / 1000) + " Thousand " + convertToEnglish(number % 1000);
        }

        if (number < 1000000000) {
            return convertToEnglish(number / 1000000) + " Million " + convertToEnglish(number % 1000000);
        }

        return convertToEnglish(number / 1000000000) + " Billion " + convertToEnglish(number % 1000000000);
    }


    public Faq getFaqById(int id) {
        String sql = "select * from faq where FaqID = " + id;
        ResultSet rs = getData(sql);
        Faq faq=new Faq();
        try {
            while (rs.next()) {
                int FaqID = rs.getInt("FaqID");
                boolean isActive = rs.getBoolean("isActive");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                int createdBy = rs.getInt("createdBy");
                String createdDate = rs.getString("createdDate");
                String stringId = convertToEnglish(FaqID);
                faq = new Faq(FaqID, isActive, question, answer, createdBy, createdDate, stringId);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return faq;
    }

    public boolean deleteFaq(int id) {
        String sql = "DELETE Faq WHERE FaqID=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public void updateFaq(Faq faq) {
        String sql = "UPDATE Faq SET isActive=?, question=?, answer=?, createdBy=?, createdDate=? where FaqID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            // Set parameters
            pre.setBoolean(1, faq.isIsActive());
            pre.setString(2, faq.getQuestion());
            pre.setString(3, faq.getAnswer());
            pre.setInt(4, faq.getCreatedBy());
            pre.setString(5, faq.getCreatedDate());
            pre.setInt(6, faq.getFaqID());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int addNewFaq(Faq f) {
        int number = 0;
        String sql = "INSERT INTO FAQ([isActive],question,answer,createdBy,createdDate) VALUES(?,?,?,?,GETDATE())";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, f.isIsActive());
            pre.setString(2, f.getQuestion());
            pre.setString(3, f.getAnswer());
            pre.setInt(4, f.getCreatedBy());

            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
         public int CheckQuestionAndAnswer(String table, String question, String answer){
        int number = 0;
        String sql  = "SELECT Count(*) FROM "+table+" WHERE quesion = ? OR answer= ?";
//        if(table.equalsIgnoreCase("supplier")) sql = sql + " OR homePage = '" + homepage +"'";
        System.out.println(table);
        System.out.println(question);
        System.out.println(answer);
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, question);
            pre.setString(2, answer);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
            System.out.println(number);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public static void main(String[] args) {
    }
}