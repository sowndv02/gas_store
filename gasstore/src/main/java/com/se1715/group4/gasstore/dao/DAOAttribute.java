/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Attribute;
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
public class DAOAttribute {

    private Connection connection = DBUtil.makeConnection();

    public Vector<Attribute> getDistinctAttribute() {
        Vector<Attribute> vector = new Vector<>();

        String sql = "SELECT DISTINCT TOP 6 code, name FROM dbo.Attribute";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                vector.add(new Attribute(code, name));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOAttribute.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return vector;
    }
}
