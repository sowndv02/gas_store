/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.admin;

import com.se1715.group4.gasstore.dao.DAO;
import com.se1715.group4.gasstore.dao.DAOShipments;
import com.se1715.group4.gasstore.dto.Administrator;
import com.se1715.group4.gasstore.dto.Shipments;
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
public class AddShipperAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AddShipperAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddShipperAdminServlet at " + request.getContextPath() + "</h1>");
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
        DAOShipments daoShippers = new DAOShipments();
        Vector<Shipments> listAllShippers = daoShippers.getAllShippersByAdmin();
        request.setAttribute("listAllShippers", listAllShippers);
        request.getRequestDispatcher("newshipper.jsp").forward(request, response);
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
        String companyname = request.getParameter("companyname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String status_raw = request.getParameter("status");
        DAOShipments daoShipper = new DAOShipments();
        System.out.println(status_raw);
        DAO dao = new DAO();
        try {
            boolean status = status_raw.equals("ON");
            System.out.println(status);

            if (dao.CheckCompanyAndEmail("shipments", companyname, email, phone, "") < 1) {
                Administrator admin = new Administrator();
                admin.setAdministratorId(1);
                Shipments ship = new Shipments(0, companyname, email, phone, status, null, admin);
                if (daoShipper.AddNewShipper(ship) > 0) {
                    request.setAttribute("message", "Thêm đơn vị vận chuyển thành công");
                }
            } else {
                request.setAttribute("message", "Email hoặc tên công ty hoặc số điện thoại đã được sử dụng");
            }

            Vector<Shipments> listAllShippers = daoShipper.getAllShippersByAdmin();
            request.setAttribute("listAllShippers", listAllShippers);
            request.getRequestDispatcher("newshipper.jsp").forward(request, response);
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
