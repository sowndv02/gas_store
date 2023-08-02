
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Discount;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Review;
import com.se1715.group4.gasstore.dto.Supplier;
import com.se1715.group4.gasstore.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOProduct {

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        Vector<Product> vector = dao.getProductsBySuppliers(new int[]{1,2,3}, 1, "1", 10000, 2000000, "");
        for (Product product : vector) {
            System.out.println(product);
        }

    }

    private final Connection connection = DBUtil.makeConnection();

    
    public int TotalProducts() {
        int number = 0;
        String sql = "SELECT COUNT(*) FROM Product";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }
    
    public int UpdateProduct(Product product) {
        int number = 0;

        String sql = "UPDATE Product SET Name = ?, SupplierID = ?, CategoryID = ?, UnitPrice = ?, stockQuantity = ?, discountId = ?,isActive = ?, keywords =?, shortDescription = ?, description =?, warranty =? WHERE ProductID = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, product.getName());
            pre.setInt(2, product.getSupplier().getSupplierId());
            pre.setInt(3, product.getCategory().getCategoryId());
            pre.setDouble(4, product.getUnitPrice());
            pre.setInt(5, product.getStockQuantity());
            pre.setDouble(6, product.getDiscountId());
            pre.setBoolean(7, product.isIsActive());
            pre.setString(8, product.getKeyword());
            pre.setString(9, product.getShortDescription());
            pre.setString(10, product.getDescription());
            pre.setInt(11, product.getWarranty());
            pre.setInt(12, product.getProductId());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;
    }

    public Vector<Product> SearchProduct(String key) {
        DAOSupplier daoSupplier = new DAOSupplier();
        DAOCategory daoCategory = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT * FROM Product WHERE name like ? OR code like ? ";
        key = "%" + key + "%";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, key);
            pre.setString(2, key);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productID");
                String code = rs.getString("code");
                String name = rs.getString("name");
                double unitPrice = rs.getDouble("unitPrice");
                int disId = rs.getInt("discountId");
                int warranty = rs.getInt("warranty");
                int cateId = rs.getInt("categoryId");
                String base64Image = rs.getString("Image");
                Discount discount = daoDiscount.GetDiscountById(disId);
                Category category = daoCategory.GetCategoryById(cateId);

                vector.add(new Product(productId, code, name, unitPrice, base64Image, discount, category));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;

    }

    public Product getInformationProduct(int id) {
        String sql = "SELECT * FROM PRODUCT WHERE PRODUCTID = ?";
        Product p = null;
        DAOCategory daoCategory = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        DAOSupplier daoSupplier = new DAOSupplier();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String shortDescription = rs.getString("shortDescription");
                String description = rs.getString("description");
                int categoryID = rs.getInt("categoryID");
                int supplierId = rs.getInt("supplierId");
                int stockQuantity = rs.getInt("stockQuantity");
                int discountId = rs.getInt("discountId");
                int warranty = rs.getInt("warranty");
                boolean isActive = rs.getBoolean("isActive");
                String createdDate = rs.getString("createdDate");
                double unitPrice = rs.getDouble("unitPrice");
                String base64Image = rs.getString("Image");
                Discount discount = daoDiscount.GetDiscountById(discountId);
                Category category = daoCategory.GetCategoryById(categoryID);
                Supplier supplier = daoSupplier.getSuppliersBySupplierID(supplierId);

                p = new Product(id, code, name, code, shortDescription, description, isActive, unitPrice, base64Image, stockQuantity, category, supplier, warranty, discount, createdDate);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    public static String convertToUnsignedString(String vietnameseString) {
        // Convert the Vietnamese string to an unsigned string
        String unsignedString = java.text.Normalizer.normalize(vietnameseString, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D")
                .toLowerCase();
        return unsignedString;
    }

    public static String replaceSpacesWithDash(String inputString) {
        // Replace spaces with "-"
        String replacedString = inputString.replaceAll(" ", "-");
        return replacedString;
    }

    /**
     * I want to insert a new product into the database, and then insert the
     * product's information and images into the database
     *
     * @param product a product object
     * @return The number of rows affected by the SQL statement.
     */
    public int AddNewProduct(Product product, Administrator admin) {
        int number = 0;
        DAOProductImage daoImg = new DAOProductImage();
        DAOProduct daoProducts = new DAOProduct();
        String sql = "INSERT INTO dbo.Product( code, Name, keywords, shortDescription, description, CategoryID, SupplierID, isActive, unitPrice, stockQuantity, createdDate, createdBy, discountId, warranty, unitOnOrders) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, 0)";
        String unsignedString = convertToUnsignedString(product.getName());
        String replacedString = replaceSpacesWithDash(unsignedString);
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, replacedString);
            pre.setString(2, product.getName());
            pre.setString(3, product.getKeyword());
            pre.setString(4, product.getShortDescription());
            pre.setString(5, product.getDescription());
            pre.setInt(6, product.getCategoryId());
            pre.setInt(7, product.getSupplierId());
            pre.setBoolean(8, product.isIsActive());
            pre.setDouble(9, product.getUnitPrice());
            pre.setInt(10, product.getStockQuantity());
            pre.setInt(11, admin.getAdministratorId());
            pre.setInt(12, product.getDiscountId());
            pre.setInt(13, product.getWarranty());
            pre.executeUpdate();

            Product proNew = daoProducts.getProductNew(admin);
            number += proNew.getProductId();
            updateCode(proNew);
        } catch (SQLException ex) {
        }

        return number;
    }

    public int InsertImgProduct(String img, int pid) {
        int number = 0;
        String sql = "UPDATE Product Set Image = ? WHERE productId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, img);
            pre.setInt(2, pid);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return number;
    }

    public int updateCode(Product p) {
        int number = 0;
        String newCode = p.getCode() + "-" + p.getProductId();
        String sql = "Update product set code = ? where productId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, newCode);
            pre.setInt(2, p.getProductId());
            pre.executeUpdate();
        } catch (SQLException ex) {
        }

        return number;

    }

    public Product getProductNew(Administrator admin) {
        Product product = null;

        String sql = "SELECT TOP 1 * FROM Product WHERE createdBy = ? ORDER BY ProductID DESC";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, admin.getAdministratorId());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String code = rs.getString("code");
                String name = rs.getString("name");
                String keyword = rs.getString("keywords");
                String shortDescription = rs.getString("shortDescription");
                String description = rs.getString("description");
                int categoryID = rs.getInt("categoryID");
                int supplierId = rs.getInt("supplierId");
                int stockQuantity = rs.getInt("stockQuantity");
                int discountId = rs.getInt("discountId");
                int warranty = rs.getInt("warranty");
                boolean isActive = rs.getBoolean("isActive");
                String createdDate = rs.getString("createdDate");
                double unitPrice = rs.getDouble("unitPrice");
                String base64Image = rs.getString("Image");

                product = new Product(productID, code, name, keyword, shortDescription, description, categoryID, supplierId, isActive, unitPrice, base64Image, stockQuantity, discountId, warranty, createdDate);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return product;

    }

    public int getProductIdByCode(String code) {
        int id = 0;
        String sql = "SELECT productId from PRoduct where code = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, code);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                id = rs.getInt("productId");
            }
        } catch (SQLException ex) {
        }

        return id;
    }

    public Vector<Product> getProductsBySuppliers(int[] id, int cId, String idorder, double from, double to, String statusDiscount) {
        if(cId <= 0 ) throw new IllegalArgumentException("CategoryId cannot <= 0");
        if(from < 0 || to < 0) throw new IllegalArgumentException("Search price cannot be negative");
        DAOCategory daoCategory = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        DAOSupplier daoSupplier = new DAOSupplier();
        Vector<Product> vector = new Vector<>();
        String sql;
        if (statusDiscount.isEmpty()) {
            sql = "SELECT p.*, discount FROM Product p inner join discount on discount.discountId = p.discountId WHERE p.isActive = 1 AND CategoryID = ? ";
        }
        else
        {
            sql = "SELECT p.*, discount FROM Product p inner join discount on discount.discountId = p.discountId WHERE p.isActive = 1 AND discount != 0 ";
        }
        if (id != null && id.length > 0) {
            sql += "AND p.SupplierID IN(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ") ";
        }
        if (from >= 0 || to <= getTopPrice("DESC")) {
            sql = sql + "AND UnitPrice BETWEEN " + from + " AND " + to;
        }
        String orderby = "Name";
        if (idorder == null || idorder.equals("1")) {
            orderby = " Name ASC";
        }
        if (idorder == null || idorder.equals("2")) {
            orderby = " Name DESC";
        }
        if (idorder.equals("3")) {
            orderby = " (unitPrice-unitPrice*discount) ASC";
        }
        if (idorder.equals("4")) {
            orderby = " (unitPrice-unitPrice*discount) DESC";
        }
        if (idorder.equals("5")) {
            orderby = " discount DESC";
        }
        if (idorder.equals("6")) {
            orderby = " discount ASC";
        }
        sql = sql + " ORDER BY " + orderby;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            if(statusDiscount.isEmpty()) pre.setInt(1, cId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String shortDescription = rs.getString("shortDescription");
                String description = rs.getString("description");
                int categoryID = rs.getInt("categoryID");
                int productId = rs.getInt("productID");
                int supplierId = rs.getInt("supplierId");
                int stockQuantity = rs.getInt("stockQuantity");
                int discountId = rs.getInt("discountId");
                int warranty = rs.getInt("warranty");
                double unitPrice = rs.getDouble("unitPrice");
                String createdDate = rs.getString("createdDate");
                String base64Image = rs.getString("Image");
                Discount discount = daoDiscount.GetDiscountById(discountId);
                Category category = daoCategory.GetCategoryById(categoryID);
                Supplier supplier = daoSupplier.getSuppliersBySupplierID(supplierId);
                vector.add(new Product(productId, code, name, code, shortDescription, description, true, unitPrice, base64Image, stockQuantity, category, supplier, warranty, discount, createdDate));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    
    public double getTopPrice(String type) {
        double maxPrice = 0;
        String sql = "SELECT TOP 1 UnitPrice FROM Product Order by Unitprice " + type;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                maxPrice = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return maxPrice;
    }

    public Vector<Product> getListByPage(Vector<Product> vector,
            int start, int end) {
        Vector<Product> arr = new Vector<>();
        for (int i = start; i < end; i++) {
            arr.add(vector.get(i));
        }
        return arr;
    }

    public Vector<Product> getProductByCategory(int cateId, String status) {
        DAOSupplier daoSupplier = new DAOSupplier();
        DAOCategory daoCategory = new DAOCategory();
        DAODiscount daoDiscount = new DAODiscount();
        Vector<Product> vector = new Vector<>();
        String sql = "SELECT * FROM Product WHERE CategoryId = ? ";
        if (!status.isEmpty()) {
            sql += status;
        }
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cateId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productID");
                String code = rs.getString("code");
                String name = rs.getString("name");
                double unitPrice = rs.getDouble("unitPrice");
                int disId = rs.getInt("discountId");
                int warranty = rs.getInt("warranty");
                String base64Image = rs.getString("Image");
                Discount discount = daoDiscount.GetDiscountById(disId);
                Category category = daoCategory.GetCategoryById(cateId);

                vector.add(new Product(productId, code, name, unitPrice, base64Image, discount, category));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Product getProductById(int id) {
        Product p = new Product();
        String sql = "SELECT code, name, unitPrice from product where productId = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                p.setProductId(id);
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                p.setUnitPrice(rs.getDouble("unitPrice"));
            }
        } catch (SQLException ex) {
        }
        return p;
    }

    public Vector<Product> getAllProducts() {
        Vector<Product> product = new Vector<>();
        DAODiscount daoDiscount = new DAODiscount();
        DAOCategory daoCategory = new DAOCategory();
        DAOSupplier daoSupplier = new DAOSupplier();
        String sql = "SELECT * FROM Product";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
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

    public Product getProduct(int ID) {
        Product p = null;
        String sql = "SELECT * FROM Product WHERE productId = ?";
        DAOSupplier daoSup = new DAOSupplier();
        DAOCategory daoCate = new DAOCategory();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int cateId = rs.getInt("CategoryId");
                int supId = rs.getInt("SupplierID");
                int disId = rs.getInt("discountId");
                Supplier s = daoSup.getSuppliersBySupplierID(supId);
                Category c = daoCate.GetCategoryById(cateId);
                p = new Product(supId, name, c, s);
            }
        } catch (SQLException ex) {
        }

        return p;
    }

    public Vector<Product> getProductsBySuppliersByAdmin(int[] id, String cateID, String idorder, String idDiscontinued) {
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
        String orderby = " Name ";
        if (idorder == null || idorder.equalsIgnoreCase("select") || idorder.equals("ProductName")) {
            orderby = " Name ";
        }
        if (cateID != null && !cateID.equalsIgnoreCase("Select")) {
            sql = sql + " AND CategoryID = " + cateID;
        }
        if (idDiscontinued != null && idDiscontinued.equalsIgnoreCase("ON")) {
            sql = sql + " AND isActive = 1 ";
        }
        if (idDiscontinued != null && idDiscontinued.equalsIgnoreCase("OFF")) {
            sql = sql + " AND isActive = 0 ";
        }

        if (idorder != null && idorder.equals("ProductID1")) {
            orderby = " Product.ProductID ASC ";
        }
        if (idorder != null && idorder.equals("ProductID2")) {
            orderby = " Product.ProductID DESC ";
        }

        if (idorder != null && idorder.equals("UnitPrice1")) {
            orderby = " UnitPrice ASC ";
        }
        if (idorder != null && idorder.equals("UnitPrice2")) {
            orderby = " UnitPrice DESC ";
        }

        if (idorder != null && idorder.equals("DateCreated1")) {
            orderby = " createdDate ASC ";
        }

        if (idorder != null && idorder.equals("DateCreated2")) {
            orderby = "createdDate DESC";
        }

        if (idorder != null && idorder.equals("UnitsOnOrder1")) {
            orderby = "UnitOnOrders ASC";
        }

        if (idorder != null && idorder.equals("UnitsOnOrder2")) {
            orderby = "UnitOnOrders DESC";
        }

        if (idorder != null && idorder.equals("UnitsInStock1")) {
            orderby = "stockQuantity ASC";
        }

        if (idorder != null && idorder.equals("UnitsInStock2")) {
            orderby = "stockQuantity DESC";
        }

        sql = sql + " ORDER BY " + orderby;

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

   public Product CheckProductInList(int index, ArrayList<Product> products, int id){
       if(index >= products.size() || index < 0) throw  new IndexOutOfBoundsException("Index cannot greater size of array or < 0");
       if(id <= 0) throw new IllegalArgumentException("ID cannot <=0");
       if(products == null || products.size() == 0 ) throw new IllegalArgumentException("Cannot null or empty or size < 1");
       for (int i = 0; i < products.size(); i++) {
           if(products.get(i).getProductId() == id){
               if(i == index) return products.get(i);
           }
       }
       return null;
   }
    
    
}
