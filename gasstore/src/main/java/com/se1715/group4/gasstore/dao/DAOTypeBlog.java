/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Comment;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.TypeBlog;
import com.se1715.group4.gasstore.util.DBUtil;
import java.awt.font.GlyphMetrics;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class DAOTypeBlog extends DBUtil {

    Connection connection = DBUtil.makeConnection();

    public ArrayList<TypeBlog> getAllTypeBlogs() {
        ArrayList<TypeBlog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM TypeBlog";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int typeID = rs.getInt("typeID");
                String typeName = rs.getString("typeName");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                String code = rs.getString("code");
                blogs.add(new TypeBlog(typeID, code, typeName, createdDate, createdBy));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return blogs;
    }

    public Map<Integer, TypeBlog> getMapTypeBlog() {
        Map<Integer, TypeBlog> mapTypeBlog = new HashMap<>();
        String sql = "select * from typeblog";
        try {
            ResultSet rs = getData(sql);

            while (rs.next()) {
                int typeId = rs.getInt("typeId");
                String typeName = rs.getString("typeName");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                mapTypeBlog.put(typeId, new TypeBlog(typeId, typeName, createdDate, createdBy));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return mapTypeBlog;
    }

    public void updateTypeBlog(int id, String typeName) {

        String sql = "UPDATE TypeBlog SET code = ?, typeName=? WHERE typeId=?";
        try {
            String code = slugify(typeName, id);
            PreparedStatement pre = connection.prepareStatement(sql);
            // Set parameters
            pre.setString(1, code);
            pre.setString(2, typeName);
            pre.setInt(3, id);

            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public String slugify(String input, int id) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.toLowerCase(Locale.ENGLISH);
        return slug + "-" + id;
    }

    public int getCurrentTypeBlogID() {
        int id = 0;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT IDENT_CURRENT('TypeBlog')");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return id;
    }

    public boolean deleteTypeBlog(int id) {
        String sql = "DELETE FROM TypeBlog WHERE typeid=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public int insertTypeBlog(TypeBlog typeBlog) {
        int number = 0;
        String sql = "INSERT INTO dbo.TypeBlog(code, typeName, createdDate, createdBy) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, typeBlog.getCode());
            pre.setString(2, typeBlog.getTypeName());
            pre.setString(3, typeBlog.getCreatedDate());
            pre.setInt(4, typeBlog.getCreatedBy());
            number = pre.executeUpdate();
            System.out.println(number);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    
}


