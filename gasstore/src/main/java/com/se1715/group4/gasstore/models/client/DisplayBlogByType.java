/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOAdministrator;
import com.se1715.group4.gasstore.dao.DAOBlog;
import com.se1715.group4.gasstore.dao.DAOComment;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOTypeBlog;
import com.se1715.group4.gasstore.dto.Blog;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.TypeBlog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class DisplayBlogByType extends HttpServlet {

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
            out.println("<title>Servlet DisplayBlogByType</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayBlogByType at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        req.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        req.setAttribute("headers", headers);
        //Get blog type and number in blog type
        DAOTypeBlog daotypeblog = new DAOTypeBlog();
        ArrayList<TypeBlog> typeblogs = daotypeblog.getAllTypeBlogs();
        req.setAttribute("typeblogs", typeblogs);

        DAOBlog daoblog = new DAOBlog();
        Map<Integer, Integer> typeNumber = daoblog.getBlogTypeNumber();
        req.setAttribute("typeNumber", typeNumber);

        //Get blog month and number in blog month
        daoblog = new DAOBlog();
        Map<Integer, Integer> dateNumberBlog = daoblog.getDateNumberBlog();
        req.setAttribute("dateNumberBlog", dateNumberBlog);
        Map<Integer, String> monthNumberText = putMonthUsingMap();
        req.setAttribute("monthNumberText", monthNumberText);
        daoblog = new DAOBlog();
        ArrayList<Integer> months = daoblog.getlistMonth();
        req.setAttribute("months", months);

        // get recent blog
        daoblog = new DAOBlog();
        ArrayList<Blog> recentBlogsRaw = daoblog.getAllBlogs();
        ArrayList<Blog> recentBlogs = new ArrayList<>();
        if (recentBlogsRaw.get(0) != null) {
            recentBlogs.add(recentBlogsRaw.get(0));
        }
        if (recentBlogsRaw.get(1) != null) {
            recentBlogs.add(recentBlogsRaw.get(1));
        }
        if (recentBlogsRaw.get(2) != null) {
            recentBlogs.add(recentBlogsRaw.get(2));
        }

        req.setAttribute("recentBlogs", recentBlogs);

        String pattern = req.getPathInfo();
        String[] parts = pattern.split("-");
        String typeId_raw = parts[parts.length - 1];

        String month_raw = req.getParameter("month");
        ArrayList<Blog> blogs = new ArrayList<>();
        if (typeId_raw == null && month_raw == null) {
            blogs = daoblog.getAllBlogs();
        } else if (typeId_raw != null && month_raw == null) {
            int typeId = Integer.parseInt(typeId_raw);
            blogs = daoblog.getBlogsByType(typeId);
            req.setAttribute("typeId", typeId);
        } else {
            int month = Integer.parseInt(month_raw);
            blogs = daoblog.getBlogsByMonth(month);
            req.setAttribute("month", month);
        }

        int total = blogs.size();
        int blogPerPage = 4;
        int numberOfPage = (total % blogPerPage == 0) ? (total / blogPerPage) : (total / blogPerPage + 1); //Số trang
        int page;
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start = (page - 1) * blogPerPage;
        int end = Math.min((page) * blogPerPage, total);
        daoblog = new DAOBlog();
        ArrayList<Blog> listBlog = daoblog.getListBlogByPage(blogs, start, end);
        req.setAttribute("blogs", listBlog);
        req.setAttribute("page", page);
        req.setAttribute("numberOfPage", numberOfPage);

        //map blog-comment
        DAOComment daoComment = new DAOComment();
        Map<Integer, Integer> mapCommentNumber = daoComment.mapCommentNumber();
        req.setAttribute("mapCommentNumber", mapCommentNumber);

        //map adminid - admin name
        DAOAdministrator daoAdministrator = new DAOAdministrator();
        Map<Integer, String> mapAdministratorName = daoAdministrator.getMapAdminstratorName();
        req.setAttribute("mapAdministratorName", mapAdministratorName);

        req.getRequestDispatcher("/client/blog.jsp").forward(req, resp);
    }

    private Map<Integer, String> putMonthUsingMap() {
        Map<Integer, String> monthNumberText = new HashMap<>();
        monthNumberText.put(1, "Tháng 1");
        monthNumberText.put(2, "Tháng 2");
        monthNumberText.put(3, "Tháng 3");
        monthNumberText.put(4, "Tháng 4");
        monthNumberText.put(5, "Tháng 5");
        monthNumberText.put(6, "Tháng 6");
        monthNumberText.put(7, "Tháng 7");
        monthNumberText.put(8, "Tháng 8");
        monthNumberText.put(9, "Tháng 9");
        monthNumberText.put(10, "Tháng 10");
        monthNumberText.put(11, "Tháng 11");
        monthNumberText.put(12, "Tháng 12");
        return monthNumberText;
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
        processRequest(request, response);
        PrintWriter out = response.getWriter();
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

}
