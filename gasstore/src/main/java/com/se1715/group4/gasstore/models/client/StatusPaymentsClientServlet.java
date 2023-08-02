/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.se1715.group4.gasstore.dao.DAOHeaderFooter;
import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dto.Footer;
import com.se1715.group4.gasstore.dto.Header;
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
public class StatusPaymentsClientServlet extends HttpServlet {

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
            out.println("<title>Servlet StatusPaymentsClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatusPaymentsClientServlet at " + request.getContextPath() + "</h1>");
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
        try {
            DAOHeaderFooter daoHeaderFooter = new DAOHeaderFooter();
        ArrayList<Footer> footers = daoHeaderFooter.getFooters();
        request.setAttribute("footers", footers);
        ArrayList<Header> headers = daoHeaderFooter.getHeaders();
        request.setAttribute("headers", headers);
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            DAOOrder od = new DAOOrder();
            String status = request.getParameter("vnp_TransactionStatus");
            int orderId = Integer.parseInt(request.getParameter("vnp_OrderInfo"));
            if (status.equalsIgnoreCase("00")) {
                od.updateStatusPayment(orderId, 1);
                
                request.setAttribute("msg", "Thanh toán bằng VNPAY thành công.");

            } else {
                request.setAttribute("status", 0);
                request.setAttribute("msg", "Thanh toán bằng VNPAY thất bại.");
            }
            session.removeAttribute("cart");
            session.removeAttribute("size");
            request.getRequestDispatcher("paymentNotification.jsp").forward(request, response);
        } catch (Exception e) {
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        processRequest(request, response);
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
