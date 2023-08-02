/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOCustomer;
import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
import com.se1715.group4.gasstore.dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class LoginClientServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginUserServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        DAOCustomer daoCustomer = new DAOCustomer();
        String uname = request.getParameter("username");
        String pword = request.getParameter("password");
        String remember = request.getParameter("remember");
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        Customer customer = daoCustomer.LoginCustomer(uname, pword);
        if (uname.isEmpty() || pword.isEmpty()) {
            request.setAttribute("error", "Tài khoản, mật khẩu không được bỏ trống!!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (customer == null) {
                request.setAttribute("error", "Tài khoản không tồn tại hoặc mật khẩu chưa chính xác!!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                if (!customer.isStatus()) {
                    request.setAttribute("error", "Tài khoản của bạn bị khoá vui lòng liên hệ Admin để xử lý!!!!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    session.setAttribute("account", customer);
                    Cookie cuser = new Cookie("user", uname);
                    Cookie cpass = new Cookie("pass", pword);
                    Cookie cr = new Cookie("cr", remember);
                    if (remember == null) {
                        cuser.setMaxAge(0);
                        cpass.setMaxAge(0);
                        cr.setMaxAge(0);
                    } else {
                        cuser.setMaxAge(60 * 60);
                        cpass.setMaxAge(60 * 60);
                        cr.setMaxAge(60 * 60);
                    }
                    response.addCookie(cr);
                    response.addCookie(cuser);
                    response.addCookie(cpass);
                    response.sendRedirect("index");
                }
            }

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

}
