/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.Product;
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
public class DAOCategory {

    private Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        System.out.println(dao.GetInfoCategory(1));
    }
    
    public Vector<Category> NumberProductsByCategories() {
        Vector<Category> vector = new Vector<>();

        String sql = "SELECT CategoryID,COUNT(CategoryID) AS Number FROM dbo.Product GROUP BY CategoryID ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cateID = rs.getInt("CategoryID");
                int number = rs.getInt("Number");
                Category cate = GetCategoryById(cateID);
                cate.setNumber(number);
                vector.add(cate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }
    
    public int UpdateCategory(Category category) {
        int number = 0;
        String sql = "UPDATE Category SET Name = ?, Code = ?, keyword = ?, description=? WHERE CategoryID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, category.getName());
            pre.setString(2, category.getCode());
            pre.setString(3, category.getKeyword());
            pre.setString(4, category.getDescription());
            pre.setInt(5, category.getCategoryId());
            number = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return number;

    }
    
    public int AddNewCategory(Category cate) {
        int number = 0;
        String sql = "INSERT INTO Category(Name, Code, keyword, description, createdDate, createdBy) VALUES(?, ?, ?, ?, GETDATE(), ?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cate.getName());
            pre.setString(2, cate.getCode());
            pre.setString(3, cate.getKeyword());
            pre.setString(4, cate.getDescription());
            pre.setInt(5, cate.getAdmin().getAdministratorId());
            
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public Category getCategoryByCol(String col, String name) {
        Category c = null;
        String sql = "SELECT * FROM Category WHERE " + col + " = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("Name");
                String code = rs.getString("code");
                c = new Category(categoryID, categoryName, code);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    public Vector<Category> getAllCategories() {
        Vector<Category> vector = new Vector<>();
        String sql = "SELECT * FROM Category ORDER BY CategoryID";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String code = rs.getString("code");
                String categoryName = rs.getString("Name");
                vector.add(new Category(categoryID, code, categoryName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public Category GetCategoryById(int id) {
        Category cate = null;
        String sql = "SELECT name FROM Category WHERE categoryId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                cate = new Category(id, rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cate;
    }

    public Vector<Category> getAllCategoriesAndNumberProducts() {
        Vector<Category> vector = new Vector<>();
        String sql = "SELECT Category.CategoryID, Category.Name, COUNT(ProductID) AS NumberOfCategory FROM dbo.Product RIGHT JOIN dbo.Category ON Category.CategoryID = Product.CategoryID\n"
                + "GROUP BY Category.CategoryID, Category.Name";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("Name");
                int number = rs.getInt("NumberOfCategory");
                vector.add(new Category(categoryID, categoryName, number));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }
    
    public Vector<Category> GetAllCategories() {
        Vector<Category> vector = new Vector<>();
        Category category = null;
        DAOProduct daoProduct = new DAOProduct();
        DAOAdministrator daoAdmin = new DAOAdministrator();
        String sql = "SELECT * FROM Category";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String code = rs.getString("code");
                String categoryName = rs.getString("Name");
                String keyword = rs.getString("keyword");
                String des = rs.getString("description");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                Administrator admin = daoAdmin.getAdminById(createdBy);
                Vector<Product> product = daoProduct.getProductByCategory(categoryID, "");
                vector.add(new Category(categoryID, code, categoryName, keyword, des, createdDate, admin, product));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }
    

    public Category GetInfoCategory(int cID) {
        Category category = null;
        DAOAdministrator daoAdmin = new DAOAdministrator();
        String sql = "SELECT * FROM Category WHERE CategoryID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String code = rs.getString("code");
                String categoryName = rs.getString("Name");
                String keyword = rs.getString("keyword");
                String des = rs.getString("description");
                String createdDate = rs.getString("createdDate");
                int createdBy = rs.getInt("createdBy");
                Administrator admin = daoAdmin.getAdminById(createdBy);
                category = new Category(categoryID, code, categoryName, keyword, des, createdDate, admin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return category;
    }
}
