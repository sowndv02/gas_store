/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOCustomer;
import com.se1715.group4.gasstore.dto.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
/**
 *
 * @author ADMIN
 */
public class UserManagerAdminServlet extends HttpServlet {
   
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
            out.println("<title>Servlet UserManagerAdminServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserManagerAdminServlet at " + request.getContextPath () + "</h1>");
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
        
        DAOCustomer daoCustomers = new DAOCustomer();
        Vector<Customer> listAllCustomers = daoCustomers.getAllCustomersByAdmin();
        
        int numPs = listAllCustomers.size();
        int numperPage = 10;
        int numpage = numPs / numperPage + (numPs % numperPage == 0 ? 0 : 1);
        int start, end;
        String tpage = request.getParameter("page");
        int page;
        try {
            page = Integer.parseInt(tpage);
        } catch (NumberFormatException e) {
            page = 1;
        }
        start = (page - 1) * numperPage;
        if (page * numperPage > numPs) {
            end = numPs;
        } else {
            end = page * numperPage;
        }
        
        Vector<Customer> vector1 = daoCustomers.getListByPage(listAllCustomers, start, end);
        request.setAttribute("listAllCustomers", vector1);
        request.setAttribute("page", page);
        request.setAttribute("num", numpage);
        request.getRequestDispatcher("accounts.jsp").forward(request, response);
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
        String email = request.getParameter("key");
        DAOCustomer daoCustomer = new DAOCustomer();
        Customer cus = daoCustomer.getCustomerByEmail(email);
        Vector<Customer> listAllCustomers = new Vector<>();
        listAllCustomers.add(cus);
        if(listAllCustomers.isEmpty()) request.setAttribute("error", "Không tìm thấy!");
        request.setAttribute("listAllCustomers", listAllCustomers);
        request.getRequestDispatcher("accounts.jsp").forward(request, response);
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
