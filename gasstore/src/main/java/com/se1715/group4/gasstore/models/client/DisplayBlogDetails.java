/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOAdministrator;
import com.se1715.group4.gasstore.dao.DAOBlog;
import com.se1715.group4.gasstore.dao.DAOComment;
import com.se1715.group4.gasstore.dao.DAOCustomer;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dao.DAOReview;
import com.se1715.group4.gasstore.dao.DAOTypeBlog;
import com.se1715.group4.gasstore.dto.Blog;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Comment;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.Review;
import com.se1715.group4.gasstore.dto.TypeBlog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DisplayBlogDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();

        String pattern = req.getPathInfo();
        String[] parts = pattern.split("-");
        try {
            int id = Integer.parseInt(parts[parts.length - 1]);
            DAOBlog daoblog = new DAOBlog();
            Blog blog = daoblog.getBlogById(id);
            req.setAttribute("blog", blog);
DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        req.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        req.setAttribute("headers", headers);
            //Get blog type and number in blog type
            DAOTypeBlog daotypeblog = new DAOTypeBlog();
            ArrayList<TypeBlog> typeblogs = daotypeblog.getAllTypeBlogs();
            req.setAttribute("typeblogs", typeblogs);

            daoblog = new DAOBlog();
            Map<Integer, Integer> typeNumber = daoblog.getBlogTypeNumber();
            req.setAttribute("typeNumber", typeNumber);

            //Get blog month and number in blog month
            daoblog = new DAOBlog();
            Map<Integer, Integer> dateNumberBlog = daoblog.getDateNumberBlog();
            req.setAttribute("dateNumberBlog", dateNumberBlog);
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
            //map adminid - admin name
            DAOAdministrator daoAdministrator = new DAOAdministrator();
            Map<Integer, String> mapAdministratorName = daoAdministrator.getMapAdminstratorName();
            req.setAttribute("mapAdministratorName", mapAdministratorName);

            //map blog-comment
            DAOComment daoComment = new DAOComment();
            Map<Integer, Integer> mapCommentNumber = daoComment.mapCommentNumber();
            req.setAttribute("mapCommentNumber", mapCommentNumber);

            //get comment by id
            daoComment = new DAOComment();

            Object a = session.getAttribute("account");
            Customer b = (Customer) session.getAttribute("customer");
            if (b != null) {
                ArrayList<Comment> commentByCustomer = daoComment.getCommentsByBlogId(blog.getBlogId(), b.getCustomerID());
                req.setAttribute("commentByCustomer", commentByCustomer);
            } else if (a != null) {
                ArrayList<Comment> commentByCustomer = daoComment.getCommentsByBlogId(blog.getBlogId(), ((Customer) a).getCustomerID());
                req.setAttribute("commentByCustomer", commentByCustomer);
            }
            ArrayList<Comment> comments = daoComment.getCommentsByBlogId(blog.getBlogId(), 0);
            req.setAttribute("comments", comments);

            //get map customer ID -user name
            DAOCustomer daoCustomer = new DAOCustomer();
            Map<Integer, String> mapCustomerUsername = daoCustomer.getMapCustomerUsername();
            req.setAttribute("mapCustomerUsername", mapCustomerUsername);

            req.getRequestDispatcher("/client/blogdetails.jsp").forward(req, resp);

        } catch (Exception e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        

    }

}
