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
import com.se1715.group4.gasstore.dto.Item;
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
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class ChangepasswordClientServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangepasswordClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangepasswordClientServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if(o!= null){
            cart = (Cart)o;
        } else{
            cart= new Cart();
        }
        DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);

        Vector<Item> vector = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", vector.size());
        
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String cfNewPassword = request.getParameter("cfNewPassword");
        Customer customer = (Customer) session.getAttribute("account");

        if(oldPassword == null || newPassword == null || cfNewPassword == null){
            request.setAttribute("error", "Please fill in all the required fields.");
            request.getRequestDispatcher("changepassword2.jsp").forward(request, response);
        }
        
        DAOCustomer daoCustomers = new DAOCustomer();
        if (!customer.getPassword().equals(oldPassword)) {
            request.setAttribute("error", "Mật khẩu cũ không chính xác!!!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } else {
            if (!newPassword.equals(cfNewPassword)) {
                request.setAttribute("error", "Hai mật khẩu mới không giống nhau!!!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else {
                if (newPassword.equals(oldPassword)) {
                    request.setAttribute("error", "Mật khẩu mới và mật khẩu cũ giống nhau!!!");
                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                } else {
                    customer.setPassword(newPassword);
                    int number = daoCustomers.ChangePassword(customer);
                    if (number > 0) {
                        request.setAttribute("error", "Thay đổi mật khẩu thành công!!!");
                    }
                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
