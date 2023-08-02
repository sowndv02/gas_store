/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.SlideShow;
import com.se1715.group4.gasstore.util.DBUtil;
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
public class DAOSlide {

    private Connection connection = DBUtil.makeConnection();

    public Vector<SlideShow> getAllSlide() {
        Vector<SlideShow> vector = new Vector<>();
        String sql = "Select Id, image, createDate, createBy FROM SlideShow";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                vector.add(new SlideShow(rs.getInt("Id"), rs.getString("image"), rs.getInt("createBy"), rs.getString("createDate")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
        return vector;
    }

    public int InsertImgSlide(String img, int id) {
        int number = 0;
        String sql = "INSERT INTO SlideShow(image, id) VALUES(?,?)";
        if (id == 2 || id == 3 || id == 1) {
            UpdateImgSlide(img, id);
        } else {
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1, img);
                pre.setInt(2, id);
                number = pre.executeUpdate();
            } catch (SQLException ex) {
            }
        }

        return number;
    }

    public int UpdateImgSlide(String img, int pid) {
        int number = 0;
        String sql = "UPDATE SlideShow Set Image = ? WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, img);
            pre.setInt(2, pid);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return number;
    }
}
