/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.util.AES;
import com.se1715.group4.gasstore.util.DBUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOAdministrator {

    private Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
        DAOAdministrator daoAdmin = new DAOAdministrator();
        System.out.println(daoAdmin.LoginAdmin("admin", "admin"));
    }
    
    public int updateAdministrator(Administrator admin, InputStream file) {
        int number = 0;
        String sql = "UPDATE Administrator SET Email = ? WHERE administratorID = ?";
        try {
            if (file != null) {
                sql = "UPDATE Administrator SET Email = ?, img = ? WHERE administratorID = ?";
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, admin.getEmail());
            if (file != null) {
                pre.setBlob(2, file);
                pre.setInt(3, admin.getAdministratorId());
                number = pre.executeUpdate();
            } else {
                pre.setInt(2, admin.getAdministratorId());
                number = pre.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public int ChangePassword(Administrator admin) {
        String sql = "UPDATE Administrator SET Password = ? Where administratorId = ?";
        int number = 0;
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, AES.encrypt(admin.getPassword()));
            pre.setInt(2, admin.getAdministratorId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DAOAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public Administrator getAdminByUsername(String username) {  
        if(username == null || username.isEmpty()) throw new IllegalArgumentException("Username cannot null or empty");
        String sql = "SELECT * FROM dbo.Administrator WHERE userName = ?";
        Administrator admin = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int adminId = rs.getInt("administratorId");
                String lastLogin = rs.getString("lastLogin");
                String password = rs.getString("password");
                String email = rs.getString("email");
                boolean isActive = rs.getBoolean("isActive");
                int roleId = rs.getInt("roleId");

                Blob blob = rs.getBlob("img");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    admin = new Administrator(adminId, username, password, lastLogin, email, isActive, roleId, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return admin;
    }

    public Administrator LoginAdmin(String username, String password) {
        Administrator admin = getAdminByUsername(username);
        try {
            if (AES.decrypt(admin.getPassword()).equalsIgnoreCase(password)) {
                return admin;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Administrator getAdminInfor(int id) {
        String sql = "SELECT * FROM dbo.Administrator WHERE administratorId = ?";
        Administrator admin = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String lastLogin = rs.getString("lastLogin");
                boolean isActive = rs.getBoolean("isActive");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int roleId = rs.getInt("roleId");
                admin = new Administrator(id, username, password, lastLogin, email, isActive, roleId);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return admin;
    }

    public Administrator getAdminById(int id) {
        String sql = "SELECT * FROM dbo.Administrator WHERE administratorId = ?";
        Administrator admin = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String lastLogin = rs.getString("lastLogin");
                boolean isActive = rs.getBoolean("isActive");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int roleId = rs.getInt("roleId");
                Blob blob = rs.getBlob("img");

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    admin = new Administrator(id, username, password, lastLogin, email, isActive, roleId, base64Image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return admin;
    }

    public Administrator getAdminByEmail(String email) {
        String sql = "SELECT * FROM dbo.Administrator WHERE email = ?";
        Administrator admin = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int adminId = rs.getInt("administratorId");
                String lastLogin = rs.getString("lastLogin");
                boolean isActive = rs.getBoolean("isActive");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int roleId = rs.getInt("roleId");
                admin = new Administrator(adminId, username, password, lastLogin, email, isActive, roleId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return admin;
    }

    public Map<Integer, String> getMapAdminstratorName() {
        Map<Integer, String> mapAdministrator = new HashMap<>();
        String sql = "select administratorID, userName from Administrator";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int administratorID = rs.getInt("administratorID");
                String userName = rs.getString("userName");
                mapAdministrator.put(administratorID, userName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return mapAdministrator;
    }

}
