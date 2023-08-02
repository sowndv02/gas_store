/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Discount;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Supplier;
import com.se1715.group4.gasstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAODiscount {

    private final Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
        DAODiscount dao = new DAODiscount();
        System.out.println(dao.addNewDiscount(new Discount("Sơn", "alo", true, 0.1, "pppp", "2023/12/12", "2023/12/12", "2023/12/12")));;
    }

    public Discount getDiscountByIDFull(int id) {
        Discount discountobj = null;
        String sql = "SELECT * FROM Discount where discountId = " + id;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                boolean isActive = rs.getBoolean("isActive");
                double discount = rs.getDouble("discount");
                String couponCode = rs.getString("couponCode");
                String startDate = rs.getString("startDate");
                String expirationDate = rs.getString("expirationDate");
                String createdDate = rs.getString("createdDate");
                discountobj = new Discount(id, name, description, isActive, discount, couponCode, startDate, expirationDate, createdDate);
//                vector.add(new Discount(id, name, description, isActive, discount, couponCode, startDate, expirationDate, createdDate, admin));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return discountobj;
    }

    public Vector<Discount> getAllDiscount() {
        Vector<Discount> vector = new Vector<>();
        String sql = "SELECT * FROM Discount";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int discountId = rs.getInt("discountId");
                double discount = rs.getDouble("discount");
                vector.add(new Discount(discountId, discount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODiscount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<Discount> getAllDiscountID() {
        Vector<Discount> vector = new Vector<>();
        String sql = "SELECT * FROM Discount";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int discountId = rs.getInt("discountId");
                vector.add(new Discount(discountId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODiscount.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Discount GetDiscountById(int id) {
        Discount dis = null;

        String sql = "SELECT * FROM Discount WHERE DiscountId =  ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double discount = rs.getDouble("discount");
                boolean status = rs.getBoolean("isActive");
                dis = new Discount(id, name, status, discount);
            }
        } catch (SQLException ex) {
        }

        return dis;
    }

    public Vector<Discount> getAllDiscountFull() {
        Vector<Discount> vector = new Vector<>();
        String sql = "SELECT * FROM Discount";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("discountID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                boolean isActive = rs.getBoolean("isActive");
                double discount = rs.getDouble("discount");
                String couponCode = rs.getString("couponCode");
                String startDate = rs.getString("startDate");
                String expirationDate = rs.getString("expirationDate");
                String createdDate = rs.getString("createdDate");
                vector.add(new Discount(id, name, description, isActive, discount, couponCode, startDate, expirationDate, createdDate));
//                vector.add(new Discount(id, name, description, isActive, discount, couponCode, startDate, expirationDate, createdDate, admin));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int addNewDiscount(Discount d) {
        int number = 0;
        String sql = "INSERT INTO [dbo].[Discount]([name],[description],[isActive],[discount],[couponCode],[startDate],[expirationDate], [createdDate], [createdBy]) VALUES(?,?,?,?,?,?,?,GETDATE(), 1)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, d.getName());
            st.setString(2, d.getDescription());
            st.setBoolean(3, d.isIsActive());
            st.setDouble(4, d.getDiscount());
            st.setString(5, d.getCouponCode());
            st.setString(6, d.getStartDate());
            st.setString(7, d.getExpirationDate());
            number = st.executeUpdate();
        } catch (Exception e) {
        }

        return number;
    }

    public int UpdateDiscountIdInProduct(int discountid, String productid) {
        int number = 0;
        String sql = "update Product set discountId = ? where productID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, discountid);
            pre.setInt(2, Integer.parseInt(productid));
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public int UpdateDiscountIdInProduct(int discountid, String productid, String supplierid) {
        int number = 0;
        String sql = "update Product set discountId = ? where productID = ? and supplierId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, discountid);
            pre.setString(2, productid);
            pre.setString(3, supplierid);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int UpdateDiscountIdWithPid(int discountid, String productid) {
        int number = 0;
        String sql = "update Product set discountId = ? where productID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, discountid);
            pre.setString(2, productid);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public int UpdateDiscountIdWithSid(int discountid, String supplierid) {
        if(supplierid == null || supplierid.isEmpty()) throw new IllegalArgumentException("Cannot null or empty");
        if(discountid <= 0 ) throw new IllegalArgumentException("ID cannot <= 0");
        int number = 0;
        String sql = "update Product set discountId = ? where supplierId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, discountid);
            pre.setString(2, supplierid);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Vector<Product> getProductsBySuppliersByAdmin(int[] id, String cateID) {
        DAOSupplier daoSuppliers = new DAOSupplier();
        DAOCategory daoCategories = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT Product.* FROM Product WHERE 1 = 1 ";
        if (id != null && id.length > 0) {
            sql += "AND Product.SupplierID IN(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ") ";
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String code = rs.getString("code");
                String productName = rs.getString("Name");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                double unitprice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("stockQuantity");
                int unitsOnOrders = rs.getInt("UnitOnOrders");
                int discountId = rs.getInt("DiscountID");
                boolean discontinued = rs.getBoolean("isActive");
                int warranty = rs.getInt("warranty");

                Supplier supplier = daoSuppliers.getSuppliersBySupplierID(supplierID);
                Discount discount = daoDiscount.GetDiscountById(discountId);
                Category category = daoCategories.GetCategoryById(categoryID);
                vector.add(new Product(productID, code, productName, discontinued, unitprice, unitsInStock, unitsOnOrders, discount, warranty, category, supplier));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Product> getAllProductsByDiscountId(int discountid) {
        if(discountid <= 0) throw new IllegalArgumentException("ID cannot <= 0");
        Vector<Product> product = new Vector<>();
        DAODiscount daoDiscount = new DAODiscount();
        DAOCategory daoCategory = new DAOCategory();
        DAOSupplier daoSupplier = new DAOSupplier();
        String sql = "select * from product where discountid = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, discountid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String shortDescription = rs.getString("shortDescription");
                String description = rs.getString("description");
                int categoryID = rs.getInt("categoryID");
                int supplierId = rs.getInt("supplierId");
                int stockQuantity = rs.getInt("stockQuantity");
                int discountId = rs.getInt("discountId");
                String createdDate = rs.getString("createdDate");
                int warranty = rs.getInt("warranty");
                double unitPrice = rs.getDouble("unitPrice");
                String b = rs.getString("Image");
                Discount discount = daoDiscount.GetDiscountById(discountId);
                Category category = daoCategory.GetCategoryById(categoryID);
                Supplier supplier = daoSupplier.getSuppliersBySupplierID(supplierId);
                product.add(new Product(productId, code, name, code, shortDescription, description, true, unitPrice, b, stockQuantity, category, supplier, warranty, discount, createdDate));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public int UpdateDiscount(Discount discount) {
        int number = 0;
        String sql = "update Discount set name = ?, description = ?, isActive = ?, discount = ?, couponCode = ?, startDate = ? , expirationDate = ? where discountID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, discount.getName());
            pre.setString(2, discount.getDescription());
            pre.setBoolean(3, discount.isIsActive());
            pre.setDouble(4, discount.getDiscount());
            pre.setString(5, discount.getCouponCode());
            pre.setString(6, discount.getStartDate());
            pre.setString(7, discount.getExpirationDate());
            pre.setInt(8, discount.getDiscountId());
            number = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public int[] GetProductIds(int supID) {
        if(supID <= 0 ) throw new IllegalArgumentException("SupplierID >= 1");
        String sql = "SELECT ProductId FROM Product Where SupplierID = ?";
        int[] sample = null;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, supID);
            ResultSet rs = pre.executeQuery();
            ArrayList<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            if (supID > 0) {
                sample = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    sample[i] = list.get(i);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAODiscount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sample;
    }

}
