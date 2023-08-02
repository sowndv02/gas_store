/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Category;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOSupplier {

    private Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
    }
    
    
    public Vector<Supplier> getNumberProductsBySupplier() {
        String sql = "SELECT Product.SupplierID, COUNT(ProductID) AS Number FROM dbo.Supplier INNER JOIN dbo.Product ON Product.SupplierID = Supplier.SupplierID\n"
                + "GROUP BY Product.SupplierID";
        Vector<Supplier> vector = new Vector<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                int sid = rs.getInt("SupplierID");
                int number = rs.getInt("Number");
                Supplier s = getSuppliersBySupplierID(sid);
                s.setNumber(number);
                vector.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    
    public Vector<Supplier> GetSupplierByType(int cid, String status){
        Vector<Supplier> vector = new Vector<>();
        String sql = "SELECT DISTINCT(Supplier.supplierId), CompanyName FROM dbo.Supplier INNER JOIN dbo.Product ON Product.supplierId = Supplier.supplierId WHERE categoryID = ? ";
        if(cid == 0) sql = "SELECT DISTINCT(Supplier.supplierId), CompanyName FROM dbo.Supplier INNER JOIN dbo.Product ON Product.supplierId = Supplier.supplierId ";
        if(!status.isEmpty()) sql += status;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            if(cid!= 0) pre.setInt(1, cid);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                int sid = rs.getInt("supplierId");
                String name = rs.getString("CompanyName");
                vector.add(new Supplier(sid, name, cid));
            }
        } catch (SQLException ex) {
        }
        return vector;
    }
    
     public Supplier GetSupplierByEmail(String name) {
        Supplier sup = null;
        String sql = "SELECT * FROM dbo.Supplier WHERE Email = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String homePage = rs.getString("HomePage");
                boolean status = rs.getBoolean("Status");
                sup = new Supplier(supplierID, companyName, status, email, phone, homePage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sup;
    }
    
    public Vector<Category> NumberOfProductsBySupplier(int id) {
        DAOCategory daoCategories = new DAOCategory();
        Vector<Category> vector = new Vector<>();
        String sql = "SELECT CategoryID,COUNT(*) AS Number FROM dbo.Product WHERE SupplierID = ? GROUP BY CategoryID";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("CategoryID");
                int number = rs.getInt("Number");
                Category category = daoCategories.GetCategoryById(cid);
                category.setNumber(number);
                vector.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public Vector<Product> getProductBestSaleBySupplier(int id) {
        DAOCategory daoCategories = new DAOCategory();
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT TOP 5 productId, Name, Code, categoryId, unitPrice, stockQuantity, unitOnOrders FROM dbo.Product\n"
                + "WHERE SupplierID =?\n"
                + "ORDER BY UnitOnOrders DESC";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("Name");
                String code = rs.getString("code");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("stockQuantity");
                int unitsOnOrders = rs.getInt("unitOnOrders");
                Category category = daoCategories.GetCategoryById(categoryID);
                vector.add(new Product(productID, code,productName, unitprice, unitsInStock, unitsOnOrders, category));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Order> getOrderBySupplier(int sid) throws IOException {
        Vector<Order> vector = new Vector<>();
        DAOShipments daoShipper = new DAOShipments();
        DAOCustomer daoCustomers = new DAOCustomer();
        String sql = "SELECT DISTINCT O.OrderID, O.CustomerID, O.OrderDate, O.RequiredDate, O.ShippedDate, O.ShipVia, O.ShipAddress, O.Status, O.TotalMoney "
                + "FROM dbo.[ORDER] AS O "
                + "INNER JOIN ( "
                + "    SELECT DISTINCT OD.OrderID "
                + "    FROM dbo.OrderDetails AS OD "
                + "    INNER JOIN dbo.Product AS P ON P.ProductID = OD.ProductID "
                + "    WHERE P.SupplierID = ?"
                + ") AS SubQueryAlias ON O.OrderID = SubQueryAlias.OrderID;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
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
                Shipments shipper = daoShipper.getShipById(shipVia);
                Customer cus = daoCustomers.GetCustomerById(customerID);
                double totalMoney = rs.getDouble("TotalMoney");
                vector.add(new Order(oid, totalMoney, orderDate, shippedDate, requiredDate, shipAddress, status, cus, shipper));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public double TotalMoneyBySupplier(int supplierID) {
        double money = 0;
        String sql = "SELECT SUM([Order].totalMoney) "
                + "FROM dbo.Product "
                + "INNER JOIN dbo.Supplier ON Supplier.supplierId = Product.supplierId "
                + "INNER JOIN dbo.OrderDetails ON OrderDetails.productID = Product.productID  "
                + "INNER JOIN dbo.[Order] ON [Order].orderID = OrderDetails.orderID "
                + "WHERE Product.supplierId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supplierID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                money = rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return money;
    }

    public int TotalProductBySupplier(int sid) {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Product WHERE SupplierID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Supplier getSuppliersBySupplierID(int sID) {
        Supplier supplier = null;
        DAOAdministrator daoAdmin = new DAOAdministrator();
        String sql = "SELECT * FROM Supplier WHERE SupplierID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, sID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String homePage = rs.getString("HomePage");
                boolean status = rs.getBoolean("status");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                Administrator admin = daoAdmin.getAdminById(createdBy);
                supplier = new Supplier(supplierID, companyName, status, createdDate, createdBy, email, phone, homePage, admin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return supplier;
    }
    
    public Supplier GetSupplierByCompanyName(String name) {
        Supplier sup = null;
        String sql = "SELECT * FROM dbo.Supplier WHERE CompanyName = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String companyName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String homePage = rs.getString("HomePage");
                boolean status = rs.getBoolean("Status");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                sup = new Supplier(supplierID, companyName, status, createdDate, createdBy, email, phone, homePage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sup;
    }
    
    public int UpdateSupplier(Supplier s) {
        int number = 0;
        String sql = "UPDATE Supplier SET Status = ?, CompanyName = ?, Email = ?, Phone = ?, HomePage = ? WHERE SupplierID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setBoolean(1, s.isStatus());
            pre.setString(2, s.getCompanyName());
            pre.setString(3, s.getEmail());
            pre.setString(4, s.getPhone());
            pre.setString(5, s.getHomePage());
            pre.setInt(6, s.getSupplierId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public Vector<Supplier> getListByPage(Vector<Supplier> vector,
            int start, int end) {
        Vector<Supplier> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }
    
    public int AddNewSupplier(Supplier s) {
        int number = 0;
        String sql = "INSERT INTO Supplier(CompanyName, Phone, Email, homePage, Status, createdDate, CreatedBy) VALUES(?,?,?,?,?, GETDATE(), ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setString(4, s.getHomePage());
            pre.setBoolean(5, s.isStatus());
            pre.setInt(6, s.getAdmin().getAdministratorId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }
    
    public Vector<Supplier> getAllSuppliers(String s) {
        Vector<Supplier> vector = new Vector<>();
        String sql = "SELECT * FROM Supplier";
        if(!s.isEmpty()) sql += s;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String supplierName = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                String homePage = rs.getString("HomePage");
                String email = rs.getString("Email");
                boolean status = rs.getBoolean("Status");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                vector.add(new Supplier(supplierID, supplierName, status, createdDate, createdBy, email, phone, homePage));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }
}
