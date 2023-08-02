/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOBlog;
import com.se1715.group4.gasstore.dao.DAOTypeBlog;
import com.se1715.group4.gasstore.dto.Blog;
import com.se1715.group4.gasstore.dto.TypeBlog;
import static com.se1715.group4.gasstore.models.admin.ManageBlog.slugify;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@MultipartConfig
/**
 *
 * @author Admin
 */
public class UpdateBlog extends HttpServlet {
private static final long serialVersionUID = 1L;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateBlog</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBlog at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            String blogId_raw = request.getParameter("blogId");
            if (blogId_raw == null) {
                response.sendRedirect("manageblog");
            } else {
                int blogId = Integer.parseInt(blogId_raw);
                DAOBlog daoBlog = new DAOBlog();
                Blog blog = daoBlog.getBlogById(blogId);
                request.setAttribute("blog", blog);
                DAOTypeBlog daoTypeBlog = new DAOTypeBlog();
                ArrayList<TypeBlog> typeblogs = daoTypeBlog.getAllTypeBlogs();
                request.setAttribute("typeblogs", typeblogs);
                request.getRequestDispatcher("updateblog.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String blogId_raw = request.getParameter("blogId");
            int blogId = Integer.parseInt(blogId_raw);
            int adminId = 1;
            String typeBlogId_raw = request.getParameter("typeBlogId");
            int typeBlogId = Integer.parseInt(typeBlogId_raw);
            boolean status = true;
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String description = request.getParameter("description");
            DAOBlog daoBlog = new DAOBlog();
            String code = slugify(title, daoBlog.getCurrentBlogID() + 1);
            DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime ldt = LocalDateTime.now();
            String lastChange = ldt.format(CUSTOM_FORMATTER);
            daoBlog.updateBlog(new Blog(blogId, code, adminId, typeBlogId, content, lastChange, status, title, content, title, description));
            try {
                String uploadDirectory = getServletContext().getRealPath("/") + "images/";
                String parentDirectory1 = new File(uploadDirectory).getParent();
                String parentDirectory2 = new File(parentDirectory1).getParent();
                String parentDirectory3 = new File(parentDirectory2).getParent();
                String newFileName = "1.jpg";
                int newId = blogId;
                checkFolder(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId);
                if (request.getPart("add") != null && request.getPart("add").getSize() > 0) {
                    checkFile(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId + "\\" + newFileName);
                    request.getPart("add").write(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId + "\\" + newFileName);
                    daoBlog.InsertImg(covertPath(parentDirectory3 + "\\src\\main\\webapp\\images\\blogs\\" + newId + "\\" + newFileName), newId);
                }
            } catch (Exception e) {
            }
            response.sendRedirect("manageblog");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
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
