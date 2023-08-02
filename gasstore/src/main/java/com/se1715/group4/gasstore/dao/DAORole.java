/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Role;
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
public class DAORole {
    private final Connection connection = DBUtil.makeConnection();
    
    public Vector<Role> getRoleByFeedback(){
        Vector<Role> vector = new Vector<>();
        String sql = "SELECT * FROM Role WHERE RoleId != 1 ORDER BY roleId DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                int roleID = rs.getInt("roleID");
                String code = rs.getString("code");
                String name = rs.getString("name");
                vector.add(new Role(roleID, code, name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return vector;
    }
}
