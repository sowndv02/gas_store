/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOSupplier;
import com.se1715.group4.gasstore.dto.Supplier;
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
public class SuppliersAdminServlet extends HttpServlet {
   
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
            out.println("<title>Servlet SuppliersAdminServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SuppliersAdminServlet at " + request.getContextPath () + "</h1>");
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
        DAOSupplier daoSuppliers = new DAOSupplier();
        Vector<Supplier> listAllSuppliers = daoSuppliers.getAllSuppliers("");
        request.setAttribute("listAllSuppliers", listAllSuppliers);
        
        int numPs = listAllSuppliers.size();
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
        
        Vector<Supplier> vector1 = daoSuppliers.getListByPage(listAllSuppliers, start, end);
        request.setAttribute("listAllSuppliers", vector1);
        request.setAttribute("page", page);
        request.setAttribute("num", numpage);
        
        
        request.getRequestDispatcher("suppliers.jsp").forward(request, response);
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
        String name = request.getParameter("key");
        DAOSupplier daoSuppliers = new DAOSupplier();
        Supplier s = daoSuppliers.GetSupplierByCompanyName(name);
        Vector<Supplier> listAllSuppliers = new Vector<>();
        listAllSuppliers.add(s);
        if(listAllSuppliers.isEmpty()) request.setAttribute("error", "Không tìm thấy!");
        request.setAttribute("listAllSuppliers", listAllSuppliers);
        
        request.getRequestDispatcher("suppliers.jsp").forward(request, response);
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
