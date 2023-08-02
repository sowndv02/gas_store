/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.AboutUs;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DAOHeaderFooter extends DBUtil {

    private final Connection connection = DBUtil.makeConnection();

    public ArrayList<Footer> getFooters() {
        ArrayList<Footer> footers = new ArrayList<>();
        String sql = "select top 4 aboutUsId, content, img, description from AboutUs where isFooter = 1";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("aboutUsId");
                String img = rs.getString("img");
                String content = rs.getString("content");
                String description = rs.getString("description");
                footers.add(new Footer(id, content, img, description));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return footers;
    }

    public ArrayList<Header> getHeaders() {
        ArrayList<Header> headers = new ArrayList<>();
        String sql = "select aboutUsId, content, description, status from AboutUs where isHeader = 1 AND status = 1";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("aboutUsId");
                String name = rs.getString("content");
                String href = rs.getString("description");
                boolean status = rs.getBoolean("status");
                headers.add( new Header(id, name, href, status));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return headers;
    }
    
    public Footer getFooterById(int id) {
        Footer footer = new Footer();
        String sql = "select content, img, description from AboutUs where isFooter = 1 AND aboutUsId = " + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String img = rs.getString("img");
                String content = rs.getString("content");
                String description = rs.getString("description");
                footer = new Footer(id, content, img, description);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return footer;
    }

    
    public void updateFooter(Footer footer) {
        String sql = "UPDATE AboutUs SET content = ?, img = '/gasstore/client/images/shipping-icon/1.png', description = ?, isFooter = 1, isHeader = null, status = null WHERE aboutUsId = " + footer.getId();
        int number = 0;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            // Set parameters
            pre.setString(1, footer.getContent());
            pre.setString(2, footer.getDescription());
            number = pre.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
