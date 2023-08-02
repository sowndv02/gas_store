/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.*;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Review;
import com.se1715.group4.gasstore.dto.WarrantyWarning;
import com.se1715.group4.gasstore.dto.Warranty;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Year;
import java.util.*;
/**
 *
 * @author ADMIN
 */
public class IndexAdminServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IndexAdminServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexAdminServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        DAOWarranty daoWarranty = new DAOWarranty();
        DAOCustomer daoCustomer = new DAOCustomer();
        DAO dao = new DAO();
        DAOOrder daoOrders  = new DAOOrder();
        DAOReview daoReview = new DAOReview();
        Vector<WarrantyWarning> warning = daoOrders.getAllWarrantyWarning();
        String year_raw= request.getParameter("year");
        int year;
        if(year_raw != null){
            year = Integer.parseInt(year_raw);
        } else year = 2023;
        Vector<Warranty> listWarranty = daoWarranty.getProductTop5();
        
        int totalCustomers = dao.GetNumbers("customer", "");
        double totalMoney = dao.GetTotal("totalMoney", "[Order]", " WHERE status = 1");
        int totalOrders = dao.GetNumbers("[Order]", " WHERE status = 1");
        int totalFeedback = dao.GetNumbers("Feedback", "");
        int totalOrdersProcess = dao.GetNumbers("[Order]", " WHERE  status = 3 OR status = 2");
        
        Vector<Review> top5Review = daoReview.getTop5Review();
        Vector<Customer> newCustomers = daoCustomer.getNewCustomers();
        Vector<Customer> customerVjp = daoCustomer.getTop5Customers();
        Vector<Order> getNewOrders = daoOrders.getNewOrders("process");
        int yearNow = Year.now().getValue();
        Map<Integer, Integer> ordersByMonth = daoOrders.NumberOrdersByMonth(year);
        
        
        request.setAttribute("listWarranty", listWarranty);
        request.setAttribute("ordersByMonth", ordersByMonth);
        request.setAttribute("totalOrdersProcess", totalOrdersProcess);
        request.setAttribute("yearNow", yearNow);
        request.setAttribute("year", year);
        request.setAttribute("getNewOrders", getNewOrders);
        request.setAttribute("customerVjp", customerVjp);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalFeedback", totalFeedback);
        request.setAttribute("top5Review", top5Review);
        request.setAttribute("newCustomers", newCustomers);
        request.setAttribute("warning", warning);
        request.setAttribute("warningList", warning.size());

        
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
