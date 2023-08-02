/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOWarranty;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Warranty;
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
public class WarrantyManageAdminServlet extends HttpServlet {
   
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
            out.println("<title>Servlet WarrantyManageAdminServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WarrantyManageAdminServlet at " + request.getContextPath () + "</h1>");
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
        DAO dao = new DAO();
        DAOWarranty daoWarranty = new DAOWarranty();
        try {
            int sizeNew = dao.GetNumbers("[Warranty]", " WHERE Status = 2");
            int sizeProcess = dao.GetNumbers("[Warranty]", " WHERE Status = 3");
            int sizeDone = dao.GetNumbers("[Warranty]", " WHERE Status = 1 OR Status = 0");
            Vector<Warranty> getNewWarranty = daoWarranty.getWarrantyByStatus("process");
            Vector<Warranty> getProcessWarranty = daoWarranty.getWarrantyByStatus("ship");
            Vector<Warranty> getDoneWarranty = daoWarranty.getWarrantyByStatus("done");
            request.setAttribute("sizeDone", sizeDone);
            request.setAttribute("sizeProcess", sizeProcess);
            request.setAttribute("sizeNew", sizeNew);
            request.setAttribute("getDoneWarranty", getDoneWarranty);
            request.setAttribute("getProcessWarranty", getProcessWarranty);
            request.setAttribute("getNewWarranty", getNewWarranty);
            request.getRequestDispatcher("warranties.jsp").forward(request, response);
        } catch (Exception e) {
        }
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
