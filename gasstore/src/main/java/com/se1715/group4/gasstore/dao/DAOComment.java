/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Comment;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class DAOComment extends DBUtil {

        private final Connection connection = DBUtil.makeConnection();

    public Map<Integer, Integer> mapCommentNumber() {
        Map<Integer, Integer> mapCommentNumber = new HashMap<>();
        String sql = "select blogId, count(*) from Comment group by blogId";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int blogId = rs.getInt(1);
                int number = rs.getInt(2);
                mapCommentNumber.put(blogId, number);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mapCommentNumber;
    }

    public ArrayList<Comment> getCommentsByBlogId(int id, int cid) {
        ArrayList<Comment> blogs = new ArrayList<>();
        DAOCustomer daoCustomer = new DAOCustomer();
        String sql = "select * from comment where blogid = ? ";
        if(cid != 0) sql += " AND CustomerId = " + cid;
        sql += " ORDER BY DatePost DESC";
            
        try {
            PreparedStatement pre =connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                int commentId = rs.getInt("commentId");
                int blogId = rs.getInt("blogId");
                String content = rs.getString("content");
                String datePost = rs.getString("datePost");
                int customerId = rs.getInt("customerId");
                boolean stauts = rs.getBoolean("status");
                Customer customer = daoCustomer.GetCustomerById(customerId);
                blogs.add(new Comment(commentId, blogId, content, datePost, customer, stauts));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

    public int insertComment(Comment comment) {
        int number = 0;
        String sql = "INSERT INTO dbo.Comment(blogId, content, datePost, customerId, status) "
                + "VALUES(?, ?, GETDATE(), ?, 1)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, comment.getBlogId());
            pre.setString(2, comment.getContent());
            pre.setInt(3, comment.getCustomerId());
            number = pre.executeUpdate();
            System.out.println(number);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public boolean deleteComment(int id) {
        String sql = "DELETE FROM Comment WHERE Commentid=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public void updateComment(Comment comment) {
        String sql = "UPDATE Comment SET content=?, datePost=GETDATE() WHERE commentId=?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            // Set parameters
            pre.setString(1, comment.getContent());
            pre.setInt(2, comment.getCommentId());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime ldt = LocalDateTime.now();
        String formattedString = ldt.format(CUSTOM_FORMATTER);
        DAOComment dao = new DAOComment();
        dao.updateComment(new Comment(3, 8, "New New Content", formattedString, 3, true));
    }

}
