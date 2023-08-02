/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOBlog;
import com.se1715.group4.gasstore.dao.DAOTypeBlog;
import com.se1715.group4.gasstore.dto.Blog;
import com.se1715.group4.gasstore.dto.TypeBlog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

@MultipartConfig
/**
 *
 * @author Admin
 */
public class ManageBlog extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String slugify(String input, int id) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.toLowerCase(Locale.ENGLISH);
        return slug + "-" + id;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOBlog daoBlog = new DAOBlog();
        ArrayList<Blog> blogs = daoBlog.getAllBlogs();
        req.setAttribute("blogs", blogs);
        DAOTypeBlog daoTypeBlog = new DAOTypeBlog();
        ArrayList<TypeBlog> typeblogs = daoTypeBlog.getAllTypeBlogs();
        req.setAttribute("typeblogs", typeblogs);
        req.getRequestDispatcher("manageblog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("insert")) {
            int adminId = 1;
            String typeBlogId_raw = req.getParameter("typeBlogId");
            int typeBlogId = Integer.parseInt(typeBlogId_raw);
            boolean status = true;
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String description = req.getParameter("description");
            DAOBlog daoBlog = new DAOBlog();
            String code = slugify(title, daoBlog.getCurrentBlogID() + 1);
            int newId = daoBlog.getCurrentBlogID() + 1;
            daoBlog.insertBlog(new Blog(typeBlogId, code, adminId, typeBlogId, content, action, status, title, content, title, description));
            try {
                String uploadDirectory = getServletContext().getRealPath("/") + "images/";
                String parentDirectory1 = new File(uploadDirectory).getParent();
                String parentDirectory2 = new File(parentDirectory1).getParent();
                String parentDirectory3 = new File(parentDirectory2).getParent();
               
                checkFolder(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId);
                if (req.getPart("add") != null && req.getPart("add").getSize() > 0) {
                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId + "\\" + req.getPart("add").getSubmittedFileName());
                    req.getPart("add").write(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId + "\\" + req.getPart("add").getSubmittedFileName());
                    daoBlog.InsertImg(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId + "\\" + req.getPart("add").getSubmittedFileName()), newId);
                }
            } catch (Exception e) {
            }

        } else if (action.equals("delete")) {
            String blogId_raw = req.getParameter("blogId");
            int blogId = Integer.parseInt(blogId_raw);
            DAOBlog daoBlog = new DAOBlog();
            daoBlog.deleteBlog(blogId);
        }
        doGet(req, resp);
    }
    
    private void checkFolder(String name) {
        File uploadDir = new File(name);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    private void checkFile(String name) {
        File existingFile = new File(name);
        if (existingFile.exists()) {
            existingFile.delete();
        }
    }

    private String covertPath(String url) {
        String updatedPath1 = url.replace("\\", "/");
        String updatedPath2 = updatedPath1.substring(updatedPath1.indexOf("webapp/") + "images/".length());
        return updatedPath2;
    }

}
