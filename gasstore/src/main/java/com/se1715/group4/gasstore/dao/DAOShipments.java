/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Shipments;
import com.se1715.group4.gasstore.dto.Supplier;
import com.se1715.group4.gasstore.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ADMIN
 */
public class DAOShipments extends DBUtil{
    private final Connection connection = DBUtil.makeConnection();
    
    public static void main(String[] args) {
        DAOShipments dao = new DAOShipments();
        Vector<Shipments> v = dao.getAllShippment();
        for (Shipments shipments : v) {
            System.out.println(shipments);
        }
    }
    
    public Shipments getShipperByEmail(String s) {
        Shipments x = null;
        String sql = "SELECT * FROM Shipments WHERE Email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ShipmentID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                x = new Shipments(id, companyName, phone, email, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }
    
    public int NumberOrderLaterByShipper(int sid) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.[Order] WHERE RequiredDate - OrderDate > 3";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }
    
    public Vector<Order> getOrderByShipperID(int shipperID) throws IOException {
        DAOCustomer daoCustomer = new DAOCustomer();
        Vector<Order> vector = new Vector<>();
        String sql = "SELECT * FROM [Order] WHERE ShipVia =? ORDER BY OrderDate DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("OrderID");
                int customerID = rs.getInt("CustomerID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                String shipAddress = rs.getString("ShipAddress");
                int status = rs.getInt("Status");
                Customer cus = daoCustomer.GetCustomerById(customerID);
                double totalMoney = rs.getDouble("TotalMoney");
                vector.add(new Order(oid, totalMoney, orderDate, shippedDate, requiredDate, shipAddress, status, cus));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public Vector<Supplier> GetNumberProductsByShipper(int shipperID) {
        Vector<Supplier> vector = new Vector<>();

        String sql = "SELECT a.SupplierID, a.CompanyName, COUNT(a.SupplierID) as NumberProducts FROM (SELECT ProductID,companyName,Product.SupplierID FROM dbo.Product INNER JOIN dbo.Supplier \n"
                + "ON Supplier.SupplierID = Product.SupplierID) AS a INNER JOIN\n"
                + "(SELECT ProductID FROM dbo.[Order] INNER JOIN dbo.OrderDetails \n"
                + "ON OrderDetails.OrderID = [Order].OrderID\n"
                + "WHERE ShipVia = ?) AS B ON B.ProductID = a.ProductID\n"
                + "GROUP BY a.SupplierID, a.companyName";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt("SupplierId");
                String companyName = rs.getString("companyName");
                int number = rs.getInt("NumberProducts");
                vector.add(new Supplier(sid, companyName, number));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    
    public int TotalOrderFailByShipper(int shipperID) {
        int number = 0;

        String sql = "SELECT COUNT(*) FROM dbo.[Order] WHERE Status = 0 AND ShipVia =?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                number = rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
    public int TotalOrderSuccessByShipper(int shipperID) {
        int number = 0;

        String sql = "SELECT COUNT(*) FROM dbo.[Order] WHERE Status = 1 AND ShipVia =?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                number = rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
    public int AddNewShipper(Shipments s) {
        int number = 0;
        String sql = "INSERT INTO Shipments(CompanyName, Phone, Email, Status, createdDate, createdBy) VALUES(?,?,?,?, GETDATE(), ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setBoolean(4, s.isStatus());
            pre.setInt(5, s.getAdmin().getAdministratorId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            
        }

        return number;
    }
    
    public int TotalProductsByShipper(int shipperID) {
        int number = 0;

        String sql = "SELECT COUNT(*) FROM dbo.OrderDetails WHERE OrderID IN(SELECT OrderID FROM dbo.[Order] WHERE ShipVia =?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
    public int TotalOrdersByShipper(int shipperID) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM dbo.[Order] WHERE ShipVia =?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                number = rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
    public int TotalSuppliersByShipper(int shipperID) {
        int number = 0;
        String sql = "SELECT COUNT(DISTINCT(SupplierID)) FROM dbo.Product WHERE ProductID IN(SELECT ProductID FROM dbo.OrderDetails WHERE OrderID IN (SELECT OrderID FROM dbo.[Order] WHERE ShipVia =?))";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, shipperID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
    public Shipments getShipperByShipperID(int sID) {
        Shipments shipper = null;
        String sql = "SELECT * FROM Shipments WHERE shipmentId = ?";
        DAOAdministrator daoAdmin = new DAOAdministrator();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int shipperId = rs.getInt("shipmentID");
                String companyName = rs.getString("companyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                boolean status = rs.getBoolean("Status");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                Administrator admin = daoAdmin.getAdminById(createdBy);
                shipper = new Shipments(shipperId, companyName, email, phone, status, createdDate, admin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return shipper;
    }
    
    public Shipments getShipById(int id){
        Shipments ship = null;
        
        String sql = "SELECT * FROM Shipments where shipmentId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                String companyName = rs.getString("companyName");
                ship = new Shipments(id, companyName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipments.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ship;
    }
    
    
    public Vector<Shipments> getAllShippment(){
        Vector<Shipments> Shipments = new Vector<>();
        String sql = "SELECT * FROM Shipments where status = 1 ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int shipmentID = rs.getInt("shipmentID");
                String companyName = rs.getString("companyName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                boolean status = rs.getBoolean("status");
                String created = rs.getString("createdDate");
                Shipments.add(new Shipments(shipmentID, companyName, email, phone, status, created));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Shipments;
    }
    
    public Shipments getShipperByCompanyName(String s) {
        Shipments x = null;
        String sql = "SELECT * FROM Shipments WHERE CompanyName = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("shipmentID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                x = new Shipments(id, companyName, email, phone, status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }
    
    
    public int UpdateShipments(Shipments s) {
        int number = 0;

        String sql = "UPDATE Shipments SET CompanyName = ?, Phone = ?, Email = ?, Status = ? WHERE shipmentID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setBoolean(4, s.isStatus());
            pre.setInt(5, s.getShipmentId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }

        return number;
    }
    
    public Vector<Shipments> getAllShippersByAdmin() {
        String sql = "SELECT * FROM Shipments";
        DAOAdministrator daoAdmin = new DAOAdministrator();
        Vector<Shipments> vector = new Vector<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("shipmentId");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                boolean status = rs.getBoolean("Status");
                String email = rs.getString("Email");
                String created = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                Administrator admin = daoAdmin.getAdminById(createdBy);
                vector.add(new Shipments(id, companyName, phone, email, status, created, admin));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
}
