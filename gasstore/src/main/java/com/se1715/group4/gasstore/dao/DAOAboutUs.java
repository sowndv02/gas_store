/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.AboutUs;
import com.se1715.group4.gasstore.dto.Member;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DAOAboutUs extends DBUtil {

    private final Connection connection = DBUtil.makeConnection();

    public AboutUs getAboutUs() {
        AboutUs aboutUs = new AboutUs();
        String sql = "select AboutUsId, content, img from AboutUs where aboutUsId = 1";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                int aboutUsId = rs.getInt("aboutUsId");
                String content = rs.getString("content");
                String img = rs.getString("img");
                aboutUs = new AboutUs(aboutUsId, content, img);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return aboutUs;
    }
    
    public static void main(String[] args) {
        DAOAboutUs dao = new DAOAboutUs();
        System.out.println(dao.getAboutUs().getContent());
    }
}
