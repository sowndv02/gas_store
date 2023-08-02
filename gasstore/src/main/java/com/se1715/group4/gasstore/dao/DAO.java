/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAO {

    private Connection connection = DBUtil.makeConnection();

    
    public static void main(String[] args) {
        DAO dao = new DAO();
        String table = "Student";
        String company = "PV gas";
        String email = "pvgas@pvgas.com.vn";
        String phone = "+84 28 3781 6777";
        String homepage = "https://www.pvgas.com.vn";
        System.out.println(dao.CheckCompanyAndEmail(table, company, email, phone, homepage));
    }
    
    public int CheckCompanyAndEmail(String table, String company, String email, String phone, String homepage){
        int number = 0;
        if(table == null || company == null || 
                email == null || phone == null || table.isEmpty() || 
                company.isEmpty() || email.isEmpty() || phone.isEmpty()) throw  new IllegalArgumentException("Not acept null parameter");
        if(!homepage.isEmpty()){
            if(homepage.charAt(homepage.length()-1) == '/'){
                homepage = homepage.substring(0, homepage.length()-2);
            }
        }
        if(!table.equalsIgnoreCase("supplier") && !table.equalsIgnoreCase("shipments")) throw new IllegalArgumentException("Table error");
        String sql  = "SELECT Count(*) FROM "+table+" WHERE companyName = ? OR email= ? OR phone = ? ";
        if(table.contains("supplier")) sql = sql + " OR homePage like '%" + homepage +"%'";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, company);
            pre.setString(2, email);
            pre.setString(3, phone);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            DBUtil.closeConnection(connection);
        }
        return number;
    }
    
    public int GetNumbers(String tableName, String status) {
        int number = 0;

        String sql = "SELECT COUNT(*) FROM " + tableName;
        if (!status.isEmpty()) {
            sql += status;
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next())
                number = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public double GetTotal(String col, String tableName, String status) {
        double total = 0;
        String sql = "SELECT SUM("+col+") FROM " + tableName;

        try {
            if (!status.isEmpty()) {
                sql += status;
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return total;
    }
}
