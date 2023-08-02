package com.se1715.group4.gasstore.util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DBUtil {

    public static Connection makeConnection() {
        Connection conn = null;
        try {

            String dbURL = "jdbc:sqlserver://localhost;databaseName=SE1715_G4_SWP391;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String pass = "123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            PreparedStatement pre = makeConnection().prepareStatement(sql);
            rs = pre.executeQuery();
        } catch (SQLException ex) {
        }
        return rs;
    }
    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {

        System.out.println("This is to test if we can connect to SQLServer");
        Connection conn = makeConnection();
        DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
        System.out.println("Driver name: " + dm.getDriverName());
        System.out.println("Driver version: " + dm.getDriverVersion());
        closeConnection(conn);
    }
}
