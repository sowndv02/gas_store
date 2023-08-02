/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAOCategory;
import com.se1715.group4.gasstore.dao.DAOWarranty;
import com.se1715.group4.gasstore.dto.Category;
import com.se1715.group4.gasstore.dto.WarrantyPolicy;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author CC
 */
@WebServlet(name = "UpdateWarrantyPolicy", urlPatterns = {"/admin/updatewp"})
public class UpdateWarrantyPolicy extends HttpServlet {

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
            out.println("<title>Servlet UpdateWarrantyPolicy</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateWarrantyPolicy at " + request.getContextPath() + "</h1>");
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
        DAOWarranty DAOWarranty = new DAOWarranty();
        String categoryID_raw = request.getParameter("cid");
        try {
            int categoryID = Integer.parseInt(categoryID_raw);
            WarrantyPolicy category = DAOWarranty.getWarrantyPolicy(categoryID);
            Vector<WarrantyPolicy> listAllCat = DAOWarranty.getAllWarrantyPolicy();
            request.setAttribute("listAllWp", listAllCat);
            request.setAttribute("category", category);
            request.getRequestDispatcher("updatewp.jsp").forward(request, response);
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
        try {
            String policy1 = request.getParameter("policy1");
            String policy2 = request.getParameter("policy2");
            String policy3 = request.getParameter("policy3");
            String categoryID_raw = request.getParameter("categoryID");
            int categoryID = Integer.parseInt(categoryID_raw);
            DAOWarranty DAOWarranty = new DAOWarranty();
            WarrantyPolicy categoryNew = DAOWarranty.getWarrantyPolicy(categoryID);
            if (policy1.isEmpty() || policy2.isEmpty() || policy3.isEmpty()) {
                request.setAttribute("message", "Không được để trống code và tên!");
            } else {
                    categoryNew.setPolicy1(policy1);
                    categoryNew.setPolicy2(policy2);
                    categoryNew.setPolicy3(policy3);
                    int n = DAOWarranty.UpdateWarrantyPolicy(categoryNew);
                    if (n > 0) {
                        request.setAttribute("message", "Cập nhật thành công!");
                    }
                }
            Vector<WarrantyPolicy> listAllCat = DAOWarranty.getAllWarrantyPolicy();
            request.setAttribute("listAllWp", listAllCat);
            request.setAttribute("category", categoryNew);
            request.getRequestDispatcher("updatewp.jsp").forward(request, response);
        } catch (Exception e) {
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
