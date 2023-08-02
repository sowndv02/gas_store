/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Review;
import com.se1715.group4.gasstore.util.DBUtil;
import com.se1715.group4.gasstore.util.SendMail;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOReview {

    private Connection connection = DBUtil.makeConnection();

    
    public int deleteReview(int id, String s){
        int number  = 0;
        String sql ="DELETE Review WHERE Reviewid = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }
    
    public int AddNewReview(Review r) {
        int number = 0;
        String sql = "INSERT INTO dbo.Review(customerId, productId, rate, content, dateRate, status) VALUES(?, ?, ?, ?, GETDATE(), 1)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, r.getCustomerId());
            pre.setInt(2, r.getProductId());
            pre.setInt(3, r.getRate());
            pre.setString(4, r.getContent());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }

        return number;
    }

    public double getAverageRate(int pid) {
        double avgRate = 0;
        Vector<Integer> rateList = new Vector<>();
        String sql = "SELECT Rate FROM Review WHERE ProductID = ? AND Status = 1";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int rate = rs.getInt(1);
                rateList.add(rate);
            }
            for (Integer integer : rateList) {
                avgRate += integer;
            }
            if (rateList.size() != 0) {
                avgRate /= (rateList.size());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return avgRate;
    }
    
    public int UpdateReview(Review r){
        int number  = 0;
        String sql ="UPDATE Review SET rate = ?, content =?, dateRate = GETDATE() WHERE Reviewid = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, r.getRate());
            pre.setString(2, r.getContent());
            pre.setInt(3, r.getReviewId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return number;
    }

    public Vector<Review> getAllReviewByProductId(int productId) {
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT * FROM REVIEW WHERE productId = ? AND Status = 1 ORDER BY dateRate DESC";
        DAOCustomer daoCustomer = new DAOCustomer();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int reviewId = rs.getInt("reviewId");
                int customerId = rs.getInt("customerId");
                int rate = rs.getInt("rate");
                String content = rs.getString("content");
                String dateRate = rs.getString("dateRate");
                Customer customer = daoCustomer.GetCustomerById(customerId);

                vector.add(new Review(reviewId, rate, content, dateRate, customer));
            }
        } catch (SQLException ex) {
        }
        return vector;
    }
    
    public Vector<Review> getAllReviewByCustomer(int productId, int customerId) {
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT * FROM REVIEW WHERE productId = ? AND Status = 1 AND CustomerID = ? ORDER BY dateRate DESC";
        DAOCustomer daoCustomer = new DAOCustomer();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productId);
            pre.setInt(2, customerId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int reviewId = rs.getInt("reviewId");
                int rate = rs.getInt("rate");
                String content = rs.getString("content");
                String dateRate = rs.getString("dateRate");
                Customer customer = daoCustomer.GetCustomerById(customerId);

                vector.add(new Review(reviewId, rate, content, dateRate, customer));
            }
        } catch (SQLException ex) {
        }
        return vector;
    }

    public Vector<Review> getTop5Review() throws IOException {
        Vector<Review> vector = new Vector<>();
        DAOProduct daoProduct = new DAOProduct();
        DAOCustomer daoCustomer = new DAOCustomer();
        String sql = "SELECT TOP 5 * FROM dbo.Review ORDER BY reviewId DESC";
        DAO dao = new DAO();

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("reviewId");
                boolean status = rs.getBoolean("Status");
                int customerId = rs.getInt("customerId");
                int productID = rs.getInt("productID");
                String content = rs.getString("content");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                Product product = daoProduct.getProductById(productID);
                Customer cus = daoCustomer.GetCustomerById(customerId);
                vector.add(new Review(id, customerId, productID, rate, content, postDate, status, cus, product));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Vector<Product> getReviewsProductsByAdmin() throws IOException {
        DAOReview daoReview = new DAOReview();
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT TOP 2 * FROM Product ORDER BY ProductID ASC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productID");
                String code = rs.getString("code");
                String productName = rs.getString("Name");
                Vector<Review> reviews = daoReview.getAllReviewByProductIDAndAdmin(productId);
                vector.add(new Product(productId, code, productName, reviews));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Review> getAllReviewByProductIDAndAdmin(int pid) throws IOException {
        DAOCustomer daoCustomers = new DAOCustomer();
        DAOProduct daoProducts = new DAOProduct();
        Vector<Review> vector = new Vector<>();
        String sql = "SELECT * FROM dbo.Review WHERE ProductID = ? ORDER BY reviewId DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("reviewId");
                String content = rs.getString("content");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                int customerId = rs.getInt("customerId");
                Customer cus = daoCustomers.GetCustomerById(customerId);
                vector.add(new Review(id, rate, content, postDate, status, cus));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Product> getNextReviewsProducts(int amount) throws IOException {
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT Product.* FROM Product ORDER BY ProductID ASC OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        DAOReview daoReview = new DAOReview();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, amount);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String code = rs.getString("code");
                String productName = rs.getString("Name");
                Vector<Review> reviews = daoReview.getAllReviewByProductIDAndAdmin(productID);
                vector.add(new Product(productID, code, productName, reviews));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    public int changeReview(Review r, String type) {
        SendMail send = new SendMail();
        int number = 0;
        String sql = "UPDATE Review SET Status = ? WHERE ReviewId = ?";
        boolean status = type.equals("public");
        String txt = "Hello! We send this email to inform that your comment at " + r.getDateRate() + " with content \"" + r.getContent() + "\" (#" + r.getProduct().getCode() + ")~" + r.getProduct().getName()
                + ". Violation of our standards so we decided to hide this comment!"
                + " If you have any questions, please reply to this message to get it resolved! Thank you very much!";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, status);
            pre.setInt(2, r.getReviewId());
            number = pre.executeUpdate();
//            if (number > 0 && !status) {
//                send.sendHiddenReview(r.getCustomer().getEmail(), txt);
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;

    }

    public Review getReviewByID(int rid) throws IOException {
        Review r = null;
        DAOProduct daoProducts = new DAOProduct();
        DAOCustomer daoCustomers = new DAOCustomer();

        String sql = "SELECT * FROM Review WHERE reviewID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt("ProductID");
                String content = rs.getString("content");
                int customerId = rs.getInt("customerId");
                int rate = rs.getInt("Rate");
                String postDate = rs.getString("DateRate");
                boolean status = rs.getBoolean("Status");
                Customer customer = daoCustomers.GetCustomerById(customerId);
                Product product = daoProducts.getProductById(productID);
                r = new Review(rid, rate, content, postDate, status, customer, product);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return r;
    }
    
    public Review getContent(int rid){
        Review r = null;
        String sql = "SELECT * FROM Review WHERE reviewID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, rid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int productID = rs.getInt("ProductID");
                String content = rs.getString("content");
                int customerId = rs.getInt("customerId");
                int rate = rs.getInt("Rate");
                r = new Review(customerId, productID, rate, content);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return r;
    }

}
