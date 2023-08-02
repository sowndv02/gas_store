/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dao;

import com.se1715.group4.gasstore.dto.Blog;
import com.se1715.group4.gasstore.dto.Comment;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.TypeBlog;
import com.se1715.group4.gasstore.util.DBUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOBlog extends DBUtil {

    private final Connection connection = DBUtil.makeConnection();

    public static void main(String[] args) {
        DAOBlog dao = new DAOBlog();
        dao.InsertImg("images/blogs/15/1.jpg", 14);
    }
    

    public ArrayList<Blog> getListBlogByPage(ArrayList<Blog> list, int start, int end) {
        ArrayList<Blog> arr = new ArrayList<>();
        for (int i = start; i < end; ++i) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public ArrayList<Blog> getAllBlogs() {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "select * from blog where status = 1 order by [datePost]  Desc";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int blogId = rs.getInt("blogId");
                int adminId = rs.getInt("adminId");
                int typeBlog = rs.getInt("typeBlogId");
                String datePost = rs.getString("datePost");
                String code = rs.getString("code");
                String lastChange = rs.getString("lastChange");
                boolean status = rs.getBoolean("status");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String base64Image = rs.getString("img");
                String description = rs.getString("description");

                blogs.add(new Blog(blogId, code, adminId, typeBlog, datePost, lastChange, status, title, content, base64Image, description));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

    public ArrayList<Blog> getBlogsByType(int typeBlogId) {
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "select * from blog where status = 1 and typeBlogId =" + typeBlogId + "order by [datePost]  Desc";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int blogId = rs.getInt("blogId");
                int adminId = rs.getInt("adminId");
                int typeBlog = rs.getInt("typeBlogId");
                String datePost = rs.getString("datePost");
                String code = rs.getString("code");
                String lastChange = rs.getString("lastChange");
                boolean status = rs.getBoolean("status");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String base64Image = rs.getString("img");
                String description = rs.getString("description");

                blogs.add(new Blog(blogId, code, adminId, typeBlog, datePost, lastChange, status, title, content, base64Image, description));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

    public ArrayList<Blog> getBlogsByMonth(int month) {
        if(month > 12 || month < 1) throw new IllegalArgumentException("The month must be from 1 to 12");
        ArrayList<Blog> blogs = new ArrayList<>();
        String sql = "select * from blog where status = 1 and month(datePost) = " + month + "order by [datePost]  Desc";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int blogId = rs.getInt("blogId");
                int adminId = rs.getInt("adminId");
                int typeBlog = rs.getInt("typeBlogId");
                String datePost = rs.getString("datePost");
                String code = rs.getString("code");
                String lastChange = rs.getString("lastChange");
                boolean status = rs.getBoolean("status");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String base64Image = rs.getString("img");
                String description = rs.getString("description");
                blogs.add(new Blog(blogId, code, adminId, typeBlog, datePost, lastChange, status, title, content, base64Image, description));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

    public Map<Integer, Integer> getBlogTypeNumber() {
        Map<Integer, Integer> categoryNumber = new HashMap<>();
        String sql = "select typeBlogId, count(*) from Blog where status = 1 group by typeBlogId";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int typeBlog = rs.getInt(1);
                int number = rs.getInt(2);
                categoryNumber.put(typeBlog, number);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categoryNumber;
    }

    public Map<Integer, Integer> getDateNumberBlog() {
        Map<Integer, Integer> dateNumberBlog = new HashMap<>();
        String sql = "select MONTH(datePost), COUNT(*) from blog where status = 1 group by MONTH(datePost)";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int month = rs.getInt(1);
                int number = rs.getInt(2);
                dateNumberBlog.put(month, number);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dateNumberBlog;
    }

    public ArrayList<Integer> getlistMonth() {
        ArrayList<Integer> dateNumberBlog = new ArrayList<>();
        String sql = "select distinct MONTH(datePost) as month from blog where status = 1 order by month desc";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int month = rs.getInt(1);;
                dateNumberBlog.add(month);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dateNumberBlog;
    }

    public Blog getBlogById(int id) {
        Blog blog = new Blog();
        String sql = "select * from blog where status = 1 and  BlogId =" + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int blogId = rs.getInt("blogId");
                int adminId = rs.getInt("adminId");
                int typeBlog = rs.getInt("typeBlogId");
                String datePost = rs.getString("datePost");
                String code = rs.getString("code");
                String lastChange = rs.getString("lastChange");
                boolean status = rs.getBoolean("status");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String base64Image = rs.getString("img");
                String description = rs.getString("description");

                blog = new Blog(blogId, code, adminId, typeBlog, datePost, lastChange, status, title, content, base64Image, description);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blog;
    }

    public int getCurrentBlogID() {
        int id = 0;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT IDENT_CURRENT('Blog')");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return id;
    }

    public int InsertImg(String path, int id){
        int number = 0;
        String sql = "UPDATE Blog set img = ? WHERE blogId =?";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1,path);
            pre.setInt(2, id);
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return number;
    }
    
    public int insertBlog(Blog blog) {
        int number = 0;
        String sql = "INSERT INTO dbo.Blog(code, adminId, typeBlogId, datePost, lastChange, status, title, content, description)"
                + "VALUES(?, ?, ?, GETDATE(), GETDATE(), ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, blog.getCode());
            pre.setInt(2, blog.getAdminId());
            pre.setInt(3, blog.getTypeBlogId());
            pre.setBoolean(4, blog.isStatus());
            pre.setString(5, blog.getTitle());
            pre.setString(6, blog.getContent());
            pre.setString(7, blog.getDescription());
            number = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return number;
    }

    public boolean deleteBlog(int id) {
        String sql = "UPDATE Blog SET status = 0 WHERE BlogId=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public void updateBlog(Blog blog) {

        String sql = "UPDATE Blog SET code = ?, adminId = ?, typeBlogId = ?, lastChange = ?, title = ?, content = ?, img = 'images/blogs/12/1.jpg', description = ? WHERE blogId=?";
        int number = 0;
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            // Set parameters
            pre.setString(1, blog.getCode());
            pre.setInt(2, blog.getAdminId());
            pre.setInt(3, blog.getTypeBlogId());
            pre.setString(4, blog.getLastChange());
            pre.setString(5, blog.getTitle());
            pre.setString(6, blog.getContent());
            pre.setString(7, blog.getDescription());
            pre.setInt(8, blog.getBlogId());
            number = pre.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
