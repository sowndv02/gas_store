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
import com.se1715.group4.gasstore.util.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class RegisterClientServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterClientServlet at " + request.getContextPath() + "</h1>");
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
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String user = request.getParameter("userName");
        String pass = request.getParameter("password");
        String cfpass = request.getParameter("cfPassword");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        DAOCustomer daoCustomer = new DAOCustomer();
DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
        if (user == null || pass == null || cfpass == null || email == null || firstName == null || lastName == null) {
            request.setAttribute("error", "Không được bỏ trống thông tin vui lòng nhập lại!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
        int checkUserName = daoCustomer.checkCustomer(user, "username");
        int checkEmail = daoCustomer.checkCustomer(email, "email");
        if ((checkEmail >= 1) || (checkUserName >= 1)) {
            request.setAttribute("error", "Email hoặc username đã tồn tại vui lòng nhập Email hoặc username khác!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            if (!pass.equals(cfpass)) {
                request.setAttribute("error", "Mật khẩu không khớp. Vui lòng nhập lại!!!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                Customer customer = new Customer(user, pass, Validation.getNowDateTime(), null, true, false, firstName, lastName, null, null, email, null);
                int n = daoCustomer.InsertNewCustomer(customer);
                if (n == 1) {
                    request.setAttribute("error", "Đăng ký thành công giờ bạn có thể đăng nhập!!!");
                } else {
                    request.setAttribute("error", "Error!!!");
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
