/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.*;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dto.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Year;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class ChartsAdminServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ChartsAdminServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChartsAdminServlet at " + request.getContextPath () + "</h1>");
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
        
        DAOCustomer daoCustomer = new DAOCustomer();
        DAOProduct daoProducts = new DAOProduct();
        DAOOrder daoOrders = new DAOOrder();
        DAOCategory daoCategories = new DAOCategory();
        DAOSupplier daoSuppliers = new DAOSupplier();
        String year_raw = request.getParameter("year");
        int year;
        if (year_raw != null) {
            year = Integer.parseInt(year_raw);
        } else {
            year = 2023;
        }
        String yearM_raw = request.getParameter("yearM");
        int yearM;
        if (yearM_raw != null) {
            yearM = Integer.parseInt(yearM_raw);
        } else {
            yearM = 2023;
        }
        Vector<IntPair> totalMoneyByMonth = daoOrders.TotalMoneyByMonth(yearM);
        Vector<IntPair> ordersByMonth = daoOrders.NumberOrdersByMonth(year, "");
        Vector<Category> numberProductsOfCategory = daoCategories.NumberProductsByCategories();
        int yearNow = Year.now().getValue();
        Vector<Supplier> productsBySupplier = daoSuppliers.getNumberProductsBySupplier();
        int newAccount = daoCustomer.newUserInMonth();
        double rateNewAccount = daoCustomer.rateNewAccount();
        double rateOrders = daoOrders.rateNewOrders();
        int newOrders = daoOrders.newOrdersInMonth();
        int active = daoCustomer.AccountActive();
        double rateActive = daoCustomer.rateAccountActive();
        int totalOrdersSuccess = daoOrders.TotalOrders();
        int totalOrders = daoOrders.TotalOrder();
        int totalOrderFail = daoOrders.TotalOrdersFail();
        int totalProcess = daoOrders.TotalOrdersProcess();
        int totalProducts = daoProducts.TotalProducts();

        request.setAttribute("totalMoneyByMonth", totalMoneyByMonth);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalProcess", totalProcess);
        request.setAttribute("totalOrderFail", totalOrderFail);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalOrdersSuccess", totalOrdersSuccess);
        request.setAttribute("rateActive", rateActive);
        request.setAttribute("active", active);
        request.setAttribute("newOrders", newOrders);
        request.setAttribute("rateOrders", rateOrders);
        request.setAttribute("rateNewAccount", rateNewAccount);
        request.setAttribute("newAccount", newAccount);
        request.setAttribute("productsBySupplier", productsBySupplier);
        request.setAttribute("numberProductsOfCategory", numberProductsOfCategory);
        request.setAttribute("yearNow", yearNow);
        request.setAttribute("year", year);
        request.setAttribute("yearM", yearM);
        request.setAttribute("ordersByMonth", ordersByMonth);

        request.getRequestDispatcher("charts.jsp").forward(request, response);
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
        processRequest(request, response);
        PrintWriter out = response.getWriter();
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
